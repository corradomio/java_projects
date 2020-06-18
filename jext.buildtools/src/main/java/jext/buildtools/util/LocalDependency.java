package jext.buildtools.util;

import jext.buildtools.Dependency;

import java.io.File;

public class LocalDependency implements Dependency {

    private File jarFile;

    public LocalDependency(File jarFile) {
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
