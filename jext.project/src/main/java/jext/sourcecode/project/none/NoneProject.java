package jext.sourcecode.project.none;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.ModulesImpl;

import java.io.File;
import java.util.Properties;

public class NoneProject  extends BaseProject {

    private static NoneProject instance = new NoneProject();

    public static Project project() {
        return instance;
    }

    public static Module module() {
        return instance.getModules().getModule();
    }

    protected NoneProject() {
        super("none", new File("."), new Properties(), "none");
    }

    @Override
    public Modules getModules() {
        return new ModulesImpl();
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new NoneModule(moduleHome, this);
    }
}
