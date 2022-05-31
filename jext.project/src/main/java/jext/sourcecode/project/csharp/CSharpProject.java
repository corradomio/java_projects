package jext.sourcecode.project.csharp;

import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.util.BaseProject;

import java.io.File;
import java.util.Properties;

public class CSharpProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "simple";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        return null;
    }

    @Override
    public Modules getModules() {
        return null;
    }
}
