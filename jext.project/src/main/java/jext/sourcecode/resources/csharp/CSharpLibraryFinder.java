package jext.sourcecode.resources.csharp;

import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.util.Parameters;

public class CSharpLibraryFinder implements LibraryFinder {

    @Override
    public <LF extends LibraryFinder> LF setProject(Project project) {
        return null;
    }

    @Override
    public <LF extends LibraryFinder> LF setDownloader(LibraryDownloader ld) {
        return null;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return null;
    }

    @Override
    public <LF extends LibraryFinder> LF configure(Parameters params) {
        return null;
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        return null;
    }

    @Override
    public Library getLibrary(MavenCoords coords) {
        return null;
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return null;
    }
}
