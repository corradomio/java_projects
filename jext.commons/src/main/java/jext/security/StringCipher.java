package jext.security;

import jext.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class StringCipher {

    private static Logger logger = Logger.getLogger(StringCipher.class);

    private static final String initVector = "encryptionIntVec";
    private static final String secretKey = "encryptionIntVec";
    private static final String AES = "AES";
    private static final String AES_ = "AES:";

    public static SecretKeySpec getSecretKey(String password) throws Exception {
        byte[] key = password.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        return new SecretKeySpec(key, AES);
    }

    private static Cipher getInstance(String password, boolean encrypt) throws Exception {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = getSecretKey(password);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            if (encrypt)
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            else
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            return cipher;
    }

    public static String encrypt(String password, String value) {
        if (value.startsWith(AES_))
            return value;
        try {
            Cipher cipher = getInstance(password, true);
            byte[] encryptedData = cipher.doFinal(value.getBytes());
            return AES_ + new String(Base64.getEncoder().encode(encryptedData)).trim();
        }
        catch (Exception e) {
            return value;
        }
    }

    public static String decrypt(String password, String encrypted) {
        if (!encrypted.startsWith(AES_))
            return encrypted;
        try {
            byte[] encryptedData = encrypted.substring(AES_.length()).getBytes();
            Cipher cipher = getInstance(password, false);
            byte[] value = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(value);
        }
        catch (Exception e) {
            return encrypted;
        }
    }
}
