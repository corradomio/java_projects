package jext.sourcecode.project.java.maven;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.java.JavaBaseProject;
import jext.sourcecode.project.java.maven.util.MavenModulesImpl;

import java.io.File;
import java.util.Properties;

public class MavenProject extends JavaBaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "maven";
    public static final String MODULE_FILE = "pom.xml";

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

    public MavenProject(String projectName, File projectHome, Properties properties) {
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

        modules = new MavenModulesImpl();

        findModulesByScan();
        findModulesByJavaSourceRoots();
        addRootModule();
        addParentModules();
        sortModules();

        return modules;
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new MavenModule(moduleHome, this);
    }

    // @Override
    // public Module getModule(String nameOrId) {
    //     for (Module module : getModules()) {
    //         if (((MavenModule) module).getMavenCoords().equals(nameOrId))
    //             return module;
    //     }
    //
    //     return super.getModule(nameOrId);
    // }
}