package jext.buildtools.project.sgradle;

import jext.buildtools.Module;
import jext.buildtools.util.BaseProject;
import jext.util.FileUtils;

import java.io.File;
import java.util.Properties;

public class SGradleProject extends BaseProject {

    public static final String TYPE = "sgradle";
    public static final String MODULE_FILE = "build.gradle";

    public static boolean isProject(File projectDir) {
        File projectFile = new File(projectDir, MODULE_FILE);
        if (projectFile.exists())
            return true;
        if (FileUtils.listFiles(projectDir, file -> file.getName().equals(MODULE_FILE)).size() > 0)
            return true;
        return false;
    }

    public SGradleProject(File projectDir, Properties properties) {
        super(projectDir, properties);
        this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
    }

    @Override
    protected Module newModule(File moduleDir) {
        return new SGradleModule(moduleDir, this);
    }
}