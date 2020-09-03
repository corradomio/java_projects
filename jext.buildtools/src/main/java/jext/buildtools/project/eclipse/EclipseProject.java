package jext.buildtools.project.eclipse;

import jext.buildtools.Module;
import jext.buildtools.project.BaseProject;
import jext.util.FileUtils;

import java.io.File;
import java.util.Properties;

public class EclipseProject extends BaseProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static final String TYPE = "eclipse";
    private static final String MODULE_FILE = ".project";

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static boolean isProject(File projectDir) {
        File projectFile = new File(projectDir, MODULE_FILE);
        if (projectFile.exists())
            return true;
        if (FileUtils.listFiles(projectDir, file -> file.getName().equals(MODULE_FILE)).size() > 0)
            return true;
        return false;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public EclipseProject(File projectDir, Properties properties) {
        super(projectDir, properties, TYPE);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
        this.properties.setProperty(PROJECT_MODULE_RESOURCES, ".project,.classpath");
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Module newModule(File moduleDir) {
        return new EclipseModule(moduleDir, this);
    }

}
