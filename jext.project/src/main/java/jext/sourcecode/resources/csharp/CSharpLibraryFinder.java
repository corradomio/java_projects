package jext.sourcecode.resources.csharp;

import jext.maven.MavenCoords;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.resources.csharp.libraries.CSharpRuntimeLibrary;
import jext.util.HashMap;
import jext.util.Parameters;

import java.io.File;
import java.util.Map;

public class CSharpLibraryFinder implements LibraryFinder {

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private LibraryDownloader downloader = new CSharpLibraryDownloader();

    @Override
    public LibraryFinder setProject(Project project) {
        this.project =  project;
        return this;
    }

    @Override
    public LibraryFinder setDownloader(LibraryDownloader ld) {
        this.downloader = ld;
        return this;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    @Override
    public LibraryFinder configure(Parameters params) {
        return this;
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        return new CSharpRuntimeLibrary(PathName.of(libraryName), project);
    }

    @Override
    public Library getLibrary(MavenCoords coords) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        throw new UnsupportedOperationException();
    }
}
