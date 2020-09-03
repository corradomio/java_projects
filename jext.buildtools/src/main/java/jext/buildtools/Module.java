package jext.buildtools;

import java.io.File;
import java.util.List;

public interface Module extends Named {

    Project getProject();

    File getDirectory();

    List<Module> getDependencies(boolean recursive);

    Sources getSources();

    Libraries getLibraries();

    List<Resource> getResources();

    Types getTypes();

}
