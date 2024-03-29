package jext.sourcecode.project.java.libraries;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;

import java.io.File;

public class ArchiveUtils {

    public static Library newLibrary(File archiveFile, Module module) {
        String name = archiveFile.getName();
        if (name.endsWith(".aar"))
            return new AarLibrary(archiveFile, module);
        else
            return new JarLibrary(archiveFile, module);
    }
}
