package jext.buildtools;

import java.io.File;
import java.util.List;

public interface Project {

    String getName();

    File getProjectDir();

    List<? extends Module> getModules();

    Module getModule(String name);

    default Module getModule(Name name) {
        return getModule(name.toString());
    }
}
