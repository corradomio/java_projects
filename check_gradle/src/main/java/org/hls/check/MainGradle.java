package org.hls.check;


import jext.gradle.GradleProject;
import jext.logging.Logger;

import java.io.File;
import java.net.URISyntaxException;

public class MainGradle {

    public static void main(String[] args) throws Exception {
        Logger.configure();

        GradleProject p = new GradleProject(new File("D:\\Projects.github\\other_projects\\hibernate-orm"));

        p.connect();

        p.getRootModule().getModules();

        p.close();

    }
}
