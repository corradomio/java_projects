package jext.buildtools;

import jext.buildtools.project.ant.AntProject;
import jext.buildtools.project.eclipse.EclipseProject;
import jext.buildtools.project.gradle.GradleProject;
import jext.buildtools.project.maven.MavenProject;

import java.io.File;
import java.util.Properties;

public class ProjectFactory {

    public static Project newProject(File projectDir, Properties properties) {
        String projectType = properties.getProperty(Project.PROJECT_TYPE, null);
        if (projectType == null) {
            if (GradleProject.isProject(projectDir))
                projectType =  GradleProject.TYPE;
            else if (MavenProject.isProject(projectDir))
                projectType =  MavenProject.TYPE;
            else if (EclipseProject.isProject(projectDir))
                projectType =  EclipseProject.TYPE;
            else
                projectType = AntProject.TYPE;
        }
        if (AntProject.TYPE.equals(projectType))
            return new AntProject(projectDir, properties);
        if (MavenProject.TYPE.equals(projectType))
            return new MavenProject(projectDir, properties);
        if (GradleProject.TYPE.equals(projectType))
            return new GradleProject(projectDir, properties);
        if (EclipseProject.TYPE.equals(projectType))
            return new EclipseProject(projectDir, properties);
        else
            return new AntProject(projectDir, properties);
    }
}
