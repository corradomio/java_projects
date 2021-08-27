package org.hls.check;

import jext.maven.Version;

public class CheckVersions {

    public static void main(String[] args) {

        Version.dump(Version.of("1.2"));
        Version.dump(Version.of("1.2.3"));
        Version.dump(Version.of("1.2.3-4"));
        Version.dump(Version.of("1.2.3-4-5"));
        Version.dump(Version.of("1.2-LABEL"));
        Version.dump(Version.of("1.2.LABEL"));
        Version.dump(Version.of("1.2.3-LABEL"));
        Version.dump(Version.of("1.2.3.LABEL"));
        Version.dump(Version.of("1.2-LABEL-4"));
        Version.dump(Version.of("1.2.LABEL.4"));
        Version.dump(Version.of("1.2.3-LABEL-4"));
        Version.dump(Version.of("1.2.3.LABEL.4"));
        Version.dump(Version.of("1.2.3.LABEL.4"));

        Version v1 = Version.of("1.2.3-alpha3");
        Version v2 = Version.of("1.2.3-beta1");

        System.out.println(v1.compareTo(v2));
        System.out.println(v1.differOn(v2));

        System.out.println(Version.of("1.2").compareTo(Version.of("1.2.0")));

    }
}
