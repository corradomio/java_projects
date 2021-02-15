package jext.sourcecode.project;

import jext.sourcecode.project.ant.AntProject;
import jext.sourcecode.project.eclipse.EclipseProject;
import jext.sourcecode.project.gradle.GradleProject;
import jext.sourcecode.project.maven.MavenProject;
import jext.sourcecode.project.simple.SimpleProject;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class GuessProjectType {

    private static final FileFilter IS_VALID_DIRECTORY =
        (file) -> file.isDirectory() && !file.getName().startsWith(".");

    // ----------------------------------------------------------------------
    // Static Methods
    // ----------------------------------------------------------------------
    // 1) check the root for configuration files
    //      if there are no configuration file goto 2)
    //      otherwise: OK
    //
    // 2) check the level 1 directories and count the number of
    //      configuration files.
    //      the project type will be the type with the highest number of
    //      configuration files.
    //      if there are no configuration files goto 3)
    //
    // 3) check the level 2 directories and count the number of
    //      configuration files.
    //      the project type will be the type with the highest number of
    //      configuration files.
    //      if there are no configuration files goto 4)
    //
    // 4) it is a simple type
    //

    public static ProjectType guessProjectType(File projectHome, Properties props) {
        List<File> directories;
        ProjectType projectType;

        {
            // directories = Collections.singletonList(projectHome);
            // projectType = findMostFrequentType(directories, props);
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

        // if (GradleProject.isProject(projectHome, props))
        //     return GradleProject.TYPE;
        // else if (MavenProject.isProject(projectHome, props))
        //     return  MavenProject.TYPE;
        // else if (EclipseProject.isProject(projectHome, props))
        //     return  EclipseProject.TYPE;
        // else if (AntProject.isProject(projectHome, props))
        //     return AntProject.TYPE;
        // else // project type not defined
        //     return SimpleProject.TYPE;
    }

    private static class CountNames extends HashMap<ProjectType, Integer> {
        void add(ProjectType name) {
            this.put(name, 1 + this.getOrDefault(name, 0));
        }

        ProjectType getMostFrequentName() {
            ProjectType selected = null;
            int selectedCount = 0;
            for (ProjectType name : keySet()) {
                int count = get(name);
                if (count > selectedCount) {
                    selected = name;
                    selectedCount = count;
                }
            }
            return selected;
        }
    }

    private static ProjectType findBetterType(File directory, Properties props) {
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

    private static ProjectType findMostFrequentType(List<File> directories, Properties props) {
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
