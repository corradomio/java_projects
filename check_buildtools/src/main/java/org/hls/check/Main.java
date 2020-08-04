package org.hls.check;

import jext.buildtools.Project;
import jext.buildtools.ProjectFactory;
import jext.buildtools.project.eclipse.EclipseProject;
import jext.buildtools.util.ProjectDump;
import jext.logging.Logger;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Logger.configure();

        Properties props = PropertiesUtils.load("buildtools.properties");

        // props.setProperty(Project.PROJECT_TYPE, AntProject.TYPE);
        // props.setProperty(Project.PROJECT_TYPE, EclipseProject.TYPE);
        // props.setProperty(Project.PROJECT_MODULE, "build/build.xml");

        File projectDir = new File(
                // "D:\\Projects\\java\\apache-ant-1.10.8"
                // "D:\\Projects\\java\\mallet-2.0.8"
                // "D:\\Projects.github\\other_projects\\eclipse.platform.releng.aggregator"
                // "D:\\Projects.github\\other_projects\\gradle"
                "D:\\Projects.test\\BTProjects\\FieldPlan"
                // "D:\\Bahar\\keycloak-quickstarts-9.0.3"
                // "D:\\Projects.test\\BTProjects\\ForSalwa"
                // "D:\\Projects.test\\BTProjects\\bookstore"
                // "D:\\Projects.test\\BTProjects\\jpcap-x64-master"
                // "D:\\Projects.test\\BTProjects\\Image-Detection-Samples"
                // "D:\\Projects.test\\BTProjects\\__Project_Packet-Sniffer-Project-Java"
        );

        Project p = ProjectFactory.newProject(projectDir, props);
        ProjectDump.dump(p);
    }
}
