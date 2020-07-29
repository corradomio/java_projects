package jext.buildtools;

import java.io.File;
import java.util.List;
import java.util.Properties;

public interface Project {

    String PROJECT_TYPE = "project.type";
    String PROJECT_MODULE = "project.module";
    String PROJECT_RESOURCES = "project.resources";

    String getName();
    String getType();

    File getDirectory();

    Properties getProperties();

    Module getModule(Name moduleName);
    Module findModule(String name);

    List<Module> getModules();
}
