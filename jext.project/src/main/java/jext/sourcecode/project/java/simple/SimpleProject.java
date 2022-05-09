package jext.sourcecode.project.java.simple;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.java.JavaBaseProject;
import jext.sourcecode.project.util.ModulesImpl;

import java.io.File;
import java.util.Properties;

public class SimpleProject extends JavaBaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "simple";
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
    public Modules getModules() {
        if (modules != null)
            return modules;

        modules = new ModulesImpl();

        findModulesByJavaSourceRoots();
        addRootModule();
        addParentModules();
        sortModules();

        return modules;
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new SimpleModule(moduleHome, this);
    }

}
