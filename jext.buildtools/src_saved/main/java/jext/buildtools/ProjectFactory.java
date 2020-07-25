package jext.buildtools;

import jext.buildtools.gradle.GradleProject;
import jext.buildtools.maven.MavenProject;
import jext.buildtools.scan.ScanProject;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.Properties;

public class ProjectFactory {

    // ----------------------------------------------------------------------
    // Static Methods
    // ----------------------------------------------------------------------

    public static final String PROJECT_NAME = "project.name";
    public static final String PROJECT_URL = "project.url";
    public static final String PROJECT_ROOT = "project.root";


    public static Project newProject(Properties props) {
        String projectName = props.getProperty(PROJECT_NAME);
        String projectRoot = props.getProperty(PROJECT_ROOT, null);
        if (projectRoot == null)
            projectRoot = props.getProperty(PROJECT_URL);
        return newProject(projectName, new File(projectRoot), props);
    }

    public static Project newProject(File projectRoot, Properties props) {
        return newProject(projectRoot.getName(), projectRoot, props);
    }

    // public static Project newProject(File projectRoot, Parameters params) {
    //     return newProject(projectRoot, params.toProperties());
    // }

    public static Project newProject(String projectName, File projectRoot, Properties props) {
        SourceProject project = new SourceProject(projectName, projectRoot, props);

        project.addAnalyzer(new ScanProject(projectRoot));

        // is a Maven project
        if (MavenProject.isValid(projectRoot))
            project.addAnalyzer(new MavenProject(projectRoot));

        // is a Gradle project
        if (GradleProject.isValid(projectRoot))
            project.addAnalyzer(new GradleProject(projectRoot));

        //
        // ...
        //

        return project.initialize();
    }

    // ----------------------------------------------------------------------
    // DEBUG

    public static Project newProject(String projectRoot) {
        return newProject(new File(projectRoot), PropertiesUtils.empty());
    }

}
