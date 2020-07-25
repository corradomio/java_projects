package jext.buildtools;

import java.io.File;
import java.util.List;
import java.util.Properties;

public interface Project {

    String PROJECT_TYPE = "project.type";

    String getName();

    File getDirectory();

    Properties getProperties();

    Module getModule(Name moduleName);
    Module findModule(String name);

    List<Module> getModules();
}
