package org.hls.check;

import jext.sourcecode.project.csharp.util.PackagesConfig;

import java.io.File;

public class CheckPackagesConfig {

    public static void main(String[] args) {
        File packagesConfigFile = new File("packages.config");

        PackagesConfig pconfig = new PackagesConfig(packagesConfigFile);

        pconfig.getLibraries().forEach(coords -> {
            System.out.println(coords);
        });

    }
}
