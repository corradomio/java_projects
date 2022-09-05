package org.hls.check;

import jext.maven.Version;
import jext.maven.Versions;

public class CheckVersions {

    public static void main(String[] args) {
        Versions versions = new Versions();

        System.out.println(Version.of("1"));
        System.out.println(Version.of("1.0"));
        System.out.println(Version.of("1.0.0"));

        versions.add("1.0.0");
        versions.add("2.0.0");
        versions.add("2.2.0");
        versions.add("2.2.2");
        versions.add("3.0.0");

        System.out.println(versions.select("2.2", ">"));

    }
}
