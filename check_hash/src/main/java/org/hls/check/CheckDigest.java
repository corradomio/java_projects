package org.hls.check;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckDigest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest digest;
        digest = MessageDigest.getInstance("MD2");
        System.out.println(System.identityHashCode(digest));
        digest = MessageDigest.getInstance("MD2");
        System.out.println(System.identityHashCode(digest));
    }
}
