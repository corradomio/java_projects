package jext.buildtools;

import jext.buildtools.project.ant.AntProject;
import jext.buildtools.project.eclipse.EclipseProject;
import jext.buildtools.project.gradle.GradleProject;
import jext.buildtools.project.maven.MavenProject;
import jext.buildtools.project.sgradle.SGradleProject;
import jext.buildtools.project.simple.SimpleProject;

import java.io.File;
import java.util.Properties;

public class ProjectFactory {

    public static Project newProject(File projectDir, Properties properties) {
        String projectType = properties.getProperty(Project.PROJECT_TYPE, null);
        if (projectType == null) {
            if (MavenProject.isProject(projectDir))
                projectType =  MavenProject.TYPE;
            else if (GradleProject.isProject(projectDir))
                projectType =  GradleProject.TYPE;
            else if (SGradleProject.isProject(projectDir))
                projectType =  SGradleProject.TYPE;
            else if (AntProject.isProject(projectDir))
                projectType =  AntProject.TYPE;
            else if (EclipseProject.isProject(projectDir))
                projectType =  EclipseProject.TYPE;
            else
                projectType =  SimpleProject.TYPE;
        }
        if (AntProject.TYPE.equals(projectType))
            return new AntProject(projectDir, properties);
        if (MavenProject.TYPE.equals(projectType))
            return new MavenProject(projectDir, properties);
        if (GradleProject.TYPE.equals(projectType))
            return new GradleProject(projectDir, properties);
        if (SGradleProject.TYPE.equals(projectType))
            return new SGradleProject(projectDir, properties);
        if (EclipseProject.TYPE.equals(projectType))
            return new EclipseProject(projectDir, properties);
        if (SimpleProject.TYPE.equals(projectType))
            return new SimpleProject(projectDir, properties);
        else
            return new SimpleProject(projectDir, properties);
    }
}
