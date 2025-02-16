package jext.util;

import jext.util.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FileDigest {

    private static final Logger logger = Logger.getLogger(FileDigest.class);

    // ----------------------------------------------------------------------
    // Digest
    // ----------------------------------------------------------------------

    public static long digestAsLong(File file) {
        if (!file.exists() || !file.isFile())
            return 0;

        try {
            MessageDigest md = algorithm();
            update(md, file);
            return toLong(md);
        }
        catch (Exception e) {
            logger.error(e, e);
            return 0;
        }
    }

    public static String digest(File file) {
        return LongHash.toString(digestAsLong(file));
    }

    public static String digest(List<File> files) {
        long[] digest = new long[1];
        files.forEach(file -> {
            long fdigest = digestAsLong(file);
            digest[0] = digest[0]*31 + fdigest;
        });
        return LongHash.toString(digest[0]);
    }

    // ----------------------------------------------------------------------

    private static MessageDigest algorithm() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5");
    }

    private static void update(MessageDigest md, File file) throws IOException {
        try(InputStream in = new FileInputStream(file)) {
            update(md, in);
        }
    }

    private static void update(MessageDigest md, InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0)
            md.update(buffer, 0, length);
    }

    private static long toLong(MessageDigest md) {
        byte[] digest = md.digest();
        return ((long) digest[0]) +
            (((long)digest[1]) <<  8) +
            (((long)digest[2]) << 16) +
            (((long)digest[3]) << 24) +
            (((long)digest[4]) << 32) +
            (((long)digest[5]) << 40) +
            (((long)digest[6]) << 48) +
            (((long)digest[7]) << 56);
    }

}
