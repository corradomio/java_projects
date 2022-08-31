package jext.sourcecode.project.python.libraries;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.java.maven.MavenName;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class PyPiLibrary extends PythonLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private MavenCoords coords;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PyPiLibrary(MavenCoords coords, File libraryDirectory) {
        super(MavenName.of(coords));
        this.version = coords.version;
        this.libraryFile = libraryDirectory;
        this.libraryType = LibraryType.REMOTE;
        this.coords = coords;
    }

    @Override
    public List<File> getFiles() {
        if (libraryFiles == null)
            populate();
        return libraryFiles;
    }

    private void populate() {
        String relativePath = String.format(
                "%1$s-%2$s",
                coords.artifactId,
                coords.version);

        File libraryHome = new File(libraryFile, relativePath);
        if (libraryHome.exists())
            libraryFiles = Collections.singletonList(libraryHome);
        else
            libraryFiles = Collections.emptyList();
    }

}
