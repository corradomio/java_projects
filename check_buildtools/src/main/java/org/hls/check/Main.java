package org.hls.check;


import jext.buildtools.Project;
import jext.buildtools.ProjectFactory;
import jext.buildtools.util.ProjectDump;

import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties props = new Properties();
        File projectDir = new File(
                // "D:\\Projects\\java\\apache-ant-1.10.8"
                "D:\\Projects\\java\\mallet-2.0.8"
        );

        Project p = ProjectFactory.newProject(projectDir, props);
        ProjectDump.dump(p);
    }
}
