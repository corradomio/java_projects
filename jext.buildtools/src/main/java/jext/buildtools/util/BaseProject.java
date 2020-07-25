package jext.buildtools.util;

import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Module;

import java.io.File;
import java.util.List;
import java.util.Properties;

public abstract class BaseProject implements Project {

    protected File projectDir;
    protected Properties properties;
    protected List<Module> modules;

    protected BaseProject(File projectDir, Properties properties){
        this.projectDir = projectDir;
        this.properties = properties;
    }

    @Override
    public String getName() {
        return projectDir.getName();
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public File getDirectory() {
        return projectDir;
    }

    @Override
    public Module getModule(Name moduleName) {
        for (Module module : getModules())
            if (module.getName().equals(moduleName))
                return module;
        return null;
    }

    @Override
    public Module findModule(String name) {
        for (Module module : getModules()) {
            Name moduleName = module.getName();
            if (moduleName.getFullname().equals(name) || moduleName.getName().equals(name))
                return module;
        }
        return null;
    }


}
