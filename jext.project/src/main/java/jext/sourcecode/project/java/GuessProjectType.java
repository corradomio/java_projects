package jext.sourcecode.project.java;

import jext.sourcecode.project.java.ant.AntProject;
import jext.sourcecode.project.java.eclipse.EclipseProject;
import jext.sourcecode.project.java.gradle.GradleProject;
import jext.sourcecode.project.java.maven.MavenProject;
import jext.sourcecode.project.java.simple.SimpleProject;
import jext.sourcecode.project.util.CountNames;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Properties;

public class GuessProjectType {

    private static final FileFilter IS_VALID_DIRECTORY =
        (file) -> file.isDirectory() && !file.getName().startsWith(".");

    public static String guessProjectType(File projectHome, Properties props) {
        String projectType;
        List<File> directories;

        {
            projectType = findBetterType(projectHome, props);
        }
        if (projectType == null) {
            directories = FileUtils.listFiles(projectHome, 0, IS_VALID_DIRECTORY, IS_VALID_DIRECTORY);
            projectType = findMostFrequentType(directories, props);
        }
        if (projectType == null) {
            directories = FileUtils.listFiles(projectHome, 1, IS_VALID_DIRECTORY, IS_VALID_DIRECTORY);
            projectType = findMostFrequentType(directories, props);
        }
        if (projectType == null)
            projectType = SimpleProject.TYPE;

        return projectType;
    }

    private static String findBetterType(File directory, Properties props) {
        if (GradleProject.isProject(directory, props))
            return GradleProject.TYPE;
        if (MavenProject.isProject(directory, props))
            return MavenProject.TYPE;
        if (EclipseProject.isProject(directory, props))
            return EclipseProject.TYPE;
        if (AntProject.isProject(directory, props))
            return AntProject.TYPE;
        return null;
    }

    private static String findMostFrequentType(List<File> directories, Properties props) {
        CountNames counts = new CountNames();

        directories.forEach(directory -> {
            if (GradleProject.isProject(directory, props))
                counts.add(GradleProject.TYPE);
            if (MavenProject.isProject(directory, props))
                counts.add(MavenProject.TYPE);
            if (EclipseProject.isProject(directory, props))
                counts.add(EclipseProject.TYPE);
            if (AntProject.isProject(directory, props))
                counts.add(AntProject.TYPE);
        });

        return counts.getMostFrequentName();
    }

}
