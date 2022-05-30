package jext.sourcecode.resources.libraries;

import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.util.Parameters;

public class NullLibraryFinder implements LibraryFinder {

    private static final LibraryFinder INSTANCE = new NullLibraryFinder();

    public static LibraryFinder instance() {
        return INSTANCE;
    }

    @Override
    public NullLibraryFinder setProject(Project project) {
        return this;
    }

    @Override
    public NullLibraryFinder configure(Parameters params) {
        return this;
    }

    // @Override
    // public NullLibraryFinder initialize() {
    //     return this;
    // }

    @Override
    public Library getLibrary(MavenCoords coords) {
        return null;
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return coords.getVersion().get();
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        return null;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return null;
    }

    @Override
    public NullLibraryFinder setDownloader(LibraryDownloader ld) {
        return this;
    }
}
