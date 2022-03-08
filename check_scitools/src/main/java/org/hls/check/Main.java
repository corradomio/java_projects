package org.hls.check;

import jext.logging.Logger;
import jext.scitools.SciTools;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Logger.configure();
        Logger.getLogger("Main").infof("Main");
        SciTools.setInstallationDirectory("C:\\Program Files\\SciTools");
        SciTools.setPythonAppDirectory("D:\\SPLGroup\\SPLDevelopment3.0\\splserver3.3.py\\spl-python");
        System.out.println(SciTools.isAvailable());

        SciTools.upython("%s/main.py", "f127d92c", "0",
                "D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project",
                "bolt://localhost:7787",
                "neo4j", "password");
    }
}
