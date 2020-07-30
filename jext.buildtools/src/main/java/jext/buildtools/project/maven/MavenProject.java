package jext.buildtools.project.maven;

import jext.buildtools.Module;
import jext.buildtools.util.BaseProject;
import jext.util.FileUtils;

import java.io.File;
import java.util.Properties;

public class MavenProject extends BaseProject {

    public static final String TYPE = "maven";
    public static final String MODULE_FILE = "pom.xml";

    public static boolean isProject(File projectDir) {
        File projectFile = new File(projectDir, MODULE_FILE);
        if (projectFile.exists())
            return true;
        if (FileUtils.listFiles(projectDir, file -> file.getName().equals(MODULE_FILE)).size() > 0)
            return true;
        return false;
    }

    public MavenProject(File projectDir, Properties properties) {
        super(projectDir, properties);
        this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
    }

    @Override
    protected Module newModule(File moduleDir) {
        return new MavenModule(moduleDir, this);
    }
}
