package jext.buildtools.util;

import jext.buildtools.Library;

import java.io.File;

public class JarLibrary implements Library {

    private File jarFile;

    public JarLibrary(File jarFile) {
        this.jarFile = jarFile;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }
}
