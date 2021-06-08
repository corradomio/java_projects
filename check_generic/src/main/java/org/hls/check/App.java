package org.hls.check;

import java.util.Optional;

public class App {

    public static void main(String[] args) {
        System.out.println(Math.acos(-10));
        System.out.println(Optional.ofNullable(null).orElse("none"));
    }
}
