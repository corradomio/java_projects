package org.hls.check;

import jext.buildtools.scan.ScanProject;
import jext.buildtools.util.Dump;
import jext.logging.Logger;

import java.io.File;

public class MainScan {

    public static void main(String[] args) {
        Logger.configure();

        ScanProject p = new ScanProject(new File(
                "D:\\Projects.github\\other_projects\\eclipse.platform.releng.aggregator"
        ));

        p.setConfiguration(new File("project-scan.xml"));

        Dump.project(p);
    }
}
