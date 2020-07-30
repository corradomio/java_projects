package org.hls.check;


import jext.buildtools.Project;
import jext.buildtools.ProjectFactory;
import jext.buildtools.project.ant.AntProject;
import jext.buildtools.util.ProjectDump;
import jext.logging.Logger;

import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Logger.configure();

        Properties props = new Properties();
        // props.setProperty(Project.PROJECT_TYPE, AntProject.TYPE);
        // props.setProperty(Project.PROJECT_MODULE, "build/build.xml");

        File projectDir = new File(
                // "D:\\Projects\\java\\apache-ant-1.10.8"
                // "D:\\Projects\\java\\mallet-2.0.8"
                // "D:\\Projects.github\\other_projects\\eclipse.platform.releng.aggregator"
                // "D:\\Projects.github\\other_projects\\gradle"
                // "D:\\Projects.test\\BTProjects\\FieldPlan"
                // "D:\\Projects.test\\BTProjects\\Image-Detection-Samples"
                // "D:\\Bahar\\keycloak-quickstarts-9.0.3"
                "D:\\Projects.test\\BTProjects\\ForSalwa"
        );

        Project p = ProjectFactory.newProject(projectDir, props);
        ProjectDump.dump(p);
    }
}
