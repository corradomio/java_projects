package org.hls.check;

import jext.buildtools.Project;
import jext.buildtools.ProjectFactory;
import jext.buildtools.project.ant.AntProject;
import jext.buildtools.project.eclipse.EclipseProject;
import jext.buildtools.project.gradle.GradleProject;
import jext.buildtools.util.ProjectDump;
import jext.logging.Logger;
import jext.util.PropertiesUtils;
import jext.util.StringUtils;

import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Logger.configure();
        Properties props = PropertiesUtils.load("buildtools.properties");

        // props.setProperty(Project.PROJECT_TYPE, AntProject.TYPE);
        // props.setProperty(Project.PROJECT_MODULE, "build/build.xml");
        props.setProperty(Project.PROJECT_TYPE, EclipseProject.TYPE);
        props.setProperty(Project.MODULE_EXCLUDE, "test*,examples");
        // props.setProperty(GradleProject.GRADLE_VERSION, "4.10.3");
        // props.setProperty(GradleProject.GRADLE_URI, "file:///Projects.github/java_projects/jext.buildtools/gradle_distribution/gradle-6.4.1-bin.zip");

        File projectDir = new File(
            // "D:\\Projects\\java\\apache-ant-1.10.8"
            // "D:\\Projects\\java\\mallet-2.0.8"

            // "D:\\Projects.github\\other_projects\\gradle"
            // "D:\\Projects.github\\other_projects\\deeplearning4j"
            // "D:\\Projects.github\\other_projects\\eclipse.platform.releng.aggregator"
            // "D:\\Projects.github\\other_projects\\jgit"
            // "D:\\Projects.github\\other_projects\\spring-framework"
            // "D:\\Projects.github\\other_projects\\hibernate-orm"

            // "D:\\splproject\\example_repo\\hibernate-orm-master"
            // "D:\\splproject\\example_repo\\spring-framework-5.2.6.RELEASE"
            // "D:\\splproject\\example_repo\\deeplearning4j"

            // "D:\\SPLGroup\\BTProjects\\FieldPlan"
            // "D:\\SPLGroup\\BTProjects\\DEUM"
            // "D:\\Projects.test\\BTProjects\\ForSalwa"
            // "D:\\Projects.test\\BTProjects\\bookstore"
            // "D:\\Projects.test\\BTProjects\\jpcap-x64-master"
            // "D:\\Projects.test\\BTProjects\\Image-Detection-Samples"
            // "D:\\Projects.test\\BTProjects\\__Project_Packet-Sniffer-Project-Java"

            "D:\\Projects.github\\eclipse_projects\\org.eclipse.bpel"
        );

        Project p = ProjectFactory.newProject(projectDir, props);
        ProjectDump.dump(p);
    }
}
