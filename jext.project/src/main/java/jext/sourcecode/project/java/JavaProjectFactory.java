package jext.sourcecode.project.java;

import jext.logging.Logger;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectFactory;
import jext.sourcecode.project.java.ant.AntProject;
import jext.sourcecode.project.java.eclipse.EclipseProject;
import jext.sourcecode.project.java.gradle.GradleProject;
import jext.sourcecode.project.java.maven.MavenProject;
import jext.sourcecode.project.java.simple.SimpleProject;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.java.GuessProjectType.guessProjectType;

public class JavaProjectFactory extends ProjectFactory {

    public Project newProject(String projectName, File projectHome, Properties properties) {

        // If the project type is not specified, we try to understand it based on
        // presence of 'building system configuration files' (for example 'pom.xml',
        // 'build.gradle', etc) following the SPECIFIC order:
        //
        //      1) Gradle
        //      2) Maven
        //      3) Eclipse
        //      4) Ant
        //      5) Simple
        //

        String projectType = guessProjectType(projectHome, properties);
        Project project;

        if (AntProject.TYPE.equals(projectType))
            project = new AntProject(projectName, projectHome, properties);
        else if (MavenProject.TYPE.equals(projectType))
            project = new MavenProject(projectName, projectHome, properties);
        else if (GradleProject.TYPE.equals(projectType))
            project = new GradleProject(projectName, projectHome, properties);
        else if (EclipseProject.TYPE.equals(projectType))
            project = new EclipseProject(projectName, projectHome, properties);
        else if (SimpleProject.TYPE.equals(projectType))
            project = new SimpleProject(projectName, projectHome, properties);
        // else if (InfoProject.TYPE.equals(projectType))
        //     project = new InfoProject(projectName, projectHome, properties);
        else if (SimpleProject.UNKNOWN.equals(projectType))
            project = new SimpleProject(projectName, projectHome, properties);
        else {
            Logger.getLogger(JavaProjectFactory.class).errorf("Project type %s unknown", projectType);
            project = new SimpleProject(projectName, projectHome, properties);
        }

        //
        // Check the runtimeLibrary to use
        // In theory it is possible to identify the correct version of Java or Android
        // But this can be expensive.
        //

        LibraryDownloader ld = new JavaLibraryDownloader();
        LibraryFinder lfinder = new JavaLibraryFinder().setDownloader(ld);
        project.setLibraryFinder(lfinder);

        return project;
    }
}
