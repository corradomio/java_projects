package org.hls.check;

import jext.text.TokensPredicate;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        System.out.println(
        TokensPredicate.parse("A?C;C,(!B(")
            .test(Arrays.asList("ABC"))
        );

    }
}
