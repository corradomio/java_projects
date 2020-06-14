package org.hls.check;

import jext.logging.Logger;
import jext.maven.MavenProject;

import java.io.File;

public class MainProject {

    public static void main(String[] args) {
        Logger.configure();

        MavenProject project = new MavenProject(new File("D:\\Projects.github\\other_projects\\commons-jcs"));

        project.dump();
    }
}
