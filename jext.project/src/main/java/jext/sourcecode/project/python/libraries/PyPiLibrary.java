package jext.sourcecode.project.python.libraries;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.java.maven.MavenName;
import jext.sourcecode.project.python.PythonConstants;

import java.io.File;
import java.util.Arrays;

public class PyPiLibrary extends PythonLibrary {

    public PyPiLibrary(MavenCoords coords, File libraryDirectory) {
        super(MavenName.of(coords));
        this.version = coords.version;
        this.libraryFile = libraryDirectory;
        this.libraryType = LibraryType.REMOTE;

        this.libraryFiles = Arrays.asList(libraryDirectory);
    }

}
