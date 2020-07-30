package jext.buildtools.project.ant;

import jext.buildtools.Module;
import jext.buildtools.util.BaseProject;
import jext.util.FileUtils;

import java.io.File;
import java.util.Properties;

public class AntProject extends BaseProject {

    public static final String TYPE = "ant";
    public static final String MODULE_FILE = "build.xml";

    public static boolean isProject(File projectDir) {
        File projectFile = new File(projectDir, MODULE_FILE);
        if (projectFile.exists())
            return true;
        if (FileUtils.listFiles(projectDir, file -> file.getName().equals(MODULE_FILE)).size() > 0)
            return true;
        return false;
    }

    public AntProject(File projectDir, Properties properties) {
        super(projectDir, properties);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
    }

    @Override
    protected Module newModule(File moduleDir) {
        return new AntModule(moduleDir, this);
    }
}
