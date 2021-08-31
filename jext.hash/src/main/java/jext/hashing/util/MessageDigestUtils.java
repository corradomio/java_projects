package jext.hashing.util;

import java.security.MessageDigest;

public class MessageDigestUtils {

    public static String getDigest(MessageDigest md, String text) {
        byte[] digest = md.digest(text.getBytes());
        return toHex(digest);
    }

    private static String toHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
