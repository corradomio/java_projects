package org.hls.check;

import jext.text.TokensPredicate;

import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Predicate<List<String>> p = TokensPredicate.parse("a*");

        p = TokensPredicate.parse("A&B");

    }
}
