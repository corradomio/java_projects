package jext.sourcecode.project.java;

import jext.logging.Logger;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.info.InfoProject;
import jext.sourcecode.project.java.ant.AntProject;
import jext.sourcecode.project.java.eclipse.EclipseProject;
import jext.sourcecode.project.java.gradle.GradleProject;
import jext.sourcecode.project.java.maven.MavenProject;
import jext.sourcecode.project.java.simple.SimpleProject;
import jext.sourcecode.resources.java.JavaLibraryDownloader;
import jext.sourcecode.resources.java.JavaLibraryFinder;

import java.io.File;
import java.util.Properties;

import static jext.sourcecode.project.java.GuessProjectType.guessProjectType;

public class JavaProjectFactory {

    public static Project newProject(String projectName, File projectHome, Properties properties) {

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
        else if (InfoProject.TYPE.equals(projectType))
            project = new InfoProject(projectName, projectHome, properties);
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

        JavaLibraryDownloader md = new JavaLibraryDownloader();
        LibraryFinder lfinder = new JavaLibraryFinder().setDownloader(md);
        project.setLibraryFinder(lfinder);

        return project;
    }
}
