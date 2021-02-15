package jext.sourcecode.project.simple;

import jext.sourcecode.project.ProjectType;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.Module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SimpleProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final ProjectType TYPE = ProjectType.SIMPLE;
    public static final String UNKNOWN = "unknown";

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static boolean isProject(File projectHome) {
        return true;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SimpleProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();

        findModulesByJavaSourceRoots();
        addRootModule();

        return modules;
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new SimpleModule(moduleHome, this);
    }

}
