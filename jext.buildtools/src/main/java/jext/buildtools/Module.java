package jext.buildtools;

import java.io.File;

public interface Module extends Named {

    File getDirectory();

    Project getProject();

    Sources getSources();

    Libraries getLibraries();

    Resources getResources();
}
