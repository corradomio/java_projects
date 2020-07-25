package jext.buildtools;

import jext.buildtools.simple.SimpleProject;

import java.io.File;
import java.util.Properties;

public class ProjectFactory {

    public static Project newProject(File projectDir, Properties properties) {
        String type = properties.getProperty(Project.PROJECT_TYPE, null);
        if (type == null)
            type = ProjectType.guessType(projectDir);
        if (type == null)
            type = "simple";
        return new SimpleProject(projectDir, properties);
    }
}
