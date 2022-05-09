package jext.sourcecode.resources.libraries;

import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
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
    public LibraryFinder setProject(Project project) {
        return this;
    }

    @Override
    public LibraryFinder configure(Parameters params) {
        return this;
    }

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
    public LibraryDownloader getLibraryDownloader() {
        return null;
    }
}
