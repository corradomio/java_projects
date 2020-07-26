package jext.buildtools;

import java.io.File;
import java.util.List;

public interface Module extends Named {

    File getDirectory();

    Project getProject();

    Sources getSources();

    Libraries getLibraries();

    Resources getResources();

    Types getTypes();

    List<Module> getDependencies();
}
