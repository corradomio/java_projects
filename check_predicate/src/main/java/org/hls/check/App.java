package org.hls.check;

import jext.text.TokensPredicate;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        System.out.println(
        TokensPredicate.parse("A,B")
            .test(Arrays.asList("A", "B", "C"))
        );

    }
}
