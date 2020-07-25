package jext.buildtools;

import jext.buildtools.java.JavaSource;

import java.io.File;

public class SourceFactory {

    public static Source newSource(File sourceFile, Module module) {
        if (sourceFile.getName().endsWith(".java"))
            return new JavaSource(sourceFile, module);
        else
            throw new IllegalArgumentException(sourceFile.toString());
    }
}
