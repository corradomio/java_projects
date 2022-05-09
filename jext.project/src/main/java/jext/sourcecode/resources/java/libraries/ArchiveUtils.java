package jext.sourcecode.resources.java.libraries;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.resources.java.libraries.AarLibrary;
import jext.sourcecode.resources.java.libraries.JarLibrary;

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
