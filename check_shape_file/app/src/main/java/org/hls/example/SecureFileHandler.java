package org.hls.example;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;


public class SecureFileHandler {

    // Secret password used for key derivation (should ideally not be hardcoded)
    private static final String SECRET_KEY = "mySuperSecretPassword";

    // Salt value used to strengthen key derivation (should be unique per deployment)
    private static final String SALT = "mySaltValue";

    private static boolean persistentFile = false;

    /**
     * Generates a SecretKey using PBKDF2 (Password-Based Key Derivation Function 2)
     * to make brute-force attacks more difficult.
     */
    private static SecretKey getSecretKey(char[] password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * Encrypts the provided content and saves it to the specified file path.
     * The Initialization Vector (IV) is stored at the beginning of the file.
     * add a string like "ENCRYPTEDv1\n" at the start of the file � before the IV and the ciphertext.
     */
    public static void saveEncryptedFile(String content, String filePath) throws Exception {
        System.out.println("Saving Encrypted content into: "+filePath);
        byte[] salt = SALT.getBytes();
        SecretKey secretKey = getSecretKey(SECRET_KEY.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        try (FileOutputStream fos = new FileOutputStream(filePath);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            fos.write("ENCRYPTEDv1\n".getBytes());  //Magic header
            fos.write(iv);                          // Then write IV
            cos.write(content.getBytes());          // Then encrypted content
        }
    }

    public static boolean verifyAndDecryptFile(String encryptedFilePath) throws Exception {
        File inputFile = new File(encryptedFilePath);

        try (FileInputStream fis = new FileInputStream(inputFile);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            bis.mark(1024);

            // Step 1: Read header
            ByteArrayOutputStream headerBytes = new ByteArrayOutputStream();
            int c;
            while ((c = bis.read()) != -1 && c != '\n') {
                headerBytes.write(c);
            }
            String header = new String(headerBytes.toByteArray(), StandardCharsets.UTF_8);

            if (!"ENCRYPTEDv1".equals(header)) {
                // Not encrypted
                return false;
            }

            // Step 2: Read IV
            byte[] iv = new byte[16];
            if (bis.read(iv) != 16) {
                throw new IOException("Invalid or missing IV");
            }
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Step 3: Prepare cipher
            SecretKey secretKey = getSecretKey(SECRET_KEY.toCharArray(), SALT.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // Step 4: Determine output file path (same dir, _decrypted suffix)
            String originalName = inputFile.getName();
            int dotIndex = originalName.lastIndexOf('.');
            String baseName = (dotIndex > 0) ? originalName.substring(0, dotIndex) : originalName;
            String extension = (dotIndex > 0) ? originalName.substring(dotIndex) : "";
            String outputFileName = baseName + "_decrypted" + extension;

            File outputFile = new File(inputFile.getParentFile(), outputFileName);

            // Step 5: Decrypt and write
            try (CipherInputStream cis = new CipherInputStream(bis, cipher);
                 FileOutputStream fos = new FileOutputStream(outputFile)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            return true;
        }
    }


    /**
     * Reads the encrypted file, extracts the IV, and decrypts the content.
     * check if the file starts with "ENCRYPTEDv1\n" � if yes, proceed with decryption. Otherwise, treat it as plain text.
     */
    public static String readEncryptedFile(String filePath) throws Exception {
        File file = new File(filePath);
        // System.out.println("Reading Encrypted file: " + filePath);

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            bis.mark(1024);  // allow fallback reset

            // === Step 1: Read header byte-by-byte (safe) ===
            ByteArrayOutputStream headerBytes = new ByteArrayOutputStream();
            int c;
            while ((c = bis.read()) != -1 && c != '\n') {
                headerBytes.write(c);
            }
            String header = new String(headerBytes.toByteArray(), StandardCharsets.UTF_8);

            // === Step 2: If not encrypted, fallback ===
            if (!"ENCRYPTEDv1".equals(header)) {
                bis.reset();
                ByteArrayOutputStream plainBuffer = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    plainBuffer.write(buffer, 0, bytesRead);
                }
                return new String(plainBuffer.toByteArray(), StandardCharsets.UTF_8);
            }

            // === Step 3: Read IV ===
            byte[] iv = new byte[16];
            if (bis.read(iv) != 16) {
                throw new IOException("Missing or corrupted IV");
            }
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // === Step 4: Decrypt content ===
            SecretKey secretKey = getSecretKey(SECRET_KEY.toCharArray(), SALT.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            try (CipherInputStream cis = new CipherInputStream(bis, cipher);
                 ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = cis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }


                return new String(bos.toByteArray(), StandardCharsets.UTF_8);
            }
        }
    }


    public static File generateTempFile(File encFile) throws Exception {
        System.out.println("[decrypt] " + encFile.getAbsolutePath());

        String decrypted = SecureFileHandler.readEncryptedFile(encFile.getAbsolutePath());

        File tempFile = File.createTempFile("decrypted-", ".xml");
        if(!persistentFile)
            tempFile.deleteOnExit();
        System.out.println("[temp] " + tempFile.getAbsolutePath());

        try (Writer w = new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.UTF_8)) {
            w.write(decrypted);
        }

        System.out.println("[done] bytes=" + tempFile.length());
        return tempFile;
    }


}
