package jext.sourcecode.project.python.simple;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.python.PythonBaseProject;
import jext.sourcecode.project.util.ModulesImpl;

import java.io.File;
import java.util.Properties;

public class PythonProject extends PythonBaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "simple";

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PythonProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);
    }

    @Override
    public Modules getModules() {
        if (modules != null)
            return modules;

        modules = new ModulesImpl();

        addRootModule();

        return modules;
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new PythonModule(moduleHome, this);
    }

}
