package jext.sourcecode.project;

import jext.maven.MavenDownloader;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface LibrarySet extends Set<Library> {

    Library get(String nameOrId);

    Library getLibrary(String nameOrId);
    Library getLibrary(Library library);

    Set<Library> getUsedLibraries();
    Set<Library> getUnusedLibraries();
    Set<Library> getLibraries(LibraryType libraryType);

    Set<File> getLibraryFiles();

    Library resolve(Library library);
    Set<Library> resolveAll(Set<Library> libraries);

    void checkArtifacts(MavenDownloader md, boolean parallel);
}
