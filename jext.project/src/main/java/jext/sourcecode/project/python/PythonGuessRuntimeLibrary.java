package jext.sourcecode.project.python;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import org.gradle.internal.impldep.org.apache.commons.lang.StringUtils;

public class PythonGuessRuntimeLibrary {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String NO_RUNTIME_LIBRARY = "";
    public static final String DEFAULT_PYTHON_RUNTIME_LIBRARY = "py38";

    public static String guessRuntimeLibrary(Module module) {
        return NO_RUNTIME_LIBRARY;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Module module;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private PythonGuessRuntimeLibrary(Module module) {
        this.module = module;
    }

    private String checkConfiguration() {
        String runtimeLibrary = module.getProperties().getProperty(Project.RUNTIME_LIBRARY, NO_RUNTIME_LIBRARY);
        if (StringUtils.isEmpty(runtimeLibrary))
            runtimeLibrary = module.getProject().getProperties().getProperty(Project.RUNTIME_LIBRARY, NO_RUNTIME_LIBRARY);
        return runtimeLibrary;
    }

    private String checkPythonProject() {
        return NO_RUNTIME_LIBRARY;
    }
}

