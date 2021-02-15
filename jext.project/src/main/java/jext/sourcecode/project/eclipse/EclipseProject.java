package jext.sourcecode.project.eclipse;

import jext.sourcecode.project.ProjectType;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.Module;

import java.io.File;
import java.util.Properties;

public class EclipseProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final ProjectType TYPE = ProjectType.ECLIPSE;
    public static final String MODULE_FILE = ".project";

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static boolean isProject(File projectHome, Properties props) {
        File projectFile = new File(projectHome, MODULE_FILE);
        if (projectFile.exists())
            return true;
        // if (FileUtils.listFiles(projectHome, file -> file.getName().equals(MODULE_FILE)).size() > 0)
        //     return true;
        return false;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public EclipseProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    protected Module newModule(File moduleHome) {
        return new EclipseModule(moduleHome, this);
    }

}