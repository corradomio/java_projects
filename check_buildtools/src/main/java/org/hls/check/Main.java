package org.hls.check;

import jext.buildtools.maven.Version;

public class Main {

    public static void main(String[] args) {
        System.out.println(Version.of("1.2.1"));
        System.out.println(Version.of("1.2"));
        System.out.println(Version.of("1.2.1-1"));
        System.out.println(Version.of("1.2.1-1-1"));
        System.out.println(Version.of("1.2-alpha"));
        System.out.println(Version.of("1.2-beta-1"));
        System.out.println(Version.of("1.2.3.4"));

    }
}
