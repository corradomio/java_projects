package jext.sourcecode.project.mini;

import jext.sourcecode.project.Project;
import jext.sourcecode.project.util.BaseProject;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class MiniProject extends BaseProject {

    public static Project newProject(
        String projectName,
        File projectHome,
        File moduleHome,
        File sourceFile) {

        MiniProject project = new MiniProject(projectName, projectHome, PropertiesUtils.empty());
        MiniModule module = project.addModule(moduleHome);
        module.addSource(sourceFile);

        return project;
    }

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "simple";
    protected MiniProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);
    }

    MiniModule addModule(File moduleHome) {
        modules = new ArrayList<>();

        modules.add(new MiniModule(projectHome, this));

        MiniModule module = new MiniModule(moduleHome, this);
        modules.add(module);

        return module;
    }

}
