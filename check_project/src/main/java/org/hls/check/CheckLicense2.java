package org.hls.check;

import jext.sourcecode.project.lfm.LicenseFinder;

import java.io.File;
import java.io.IOException;

public class CheckLicense2 {

    public static void main(String[] args) throws IOException {

        File file = new File(
                // from pom
                // "D:\\SPLGroup\\repos\\.maven\\.m2\\com\\azure\\azure-client-sdk-parent\\1.7.0\\azure-client-sdk-parent-1.7.0.pom"
                // from XML header
                // "D:\\SPLGroup\\repos\\.maven\\.m2\\commons-beanutils\\commons-beanutils-core\\1.8.0\\commons-beanutils-core-1.8.0.pom"
                "D:/SPLGroup/repos/.maven/.m2/avalon-framework/avalon-framework-api/4.3/avalon-framework-api-4.3.jar"
        );

        LicenseFinder.findLicense(file);

    }

}
