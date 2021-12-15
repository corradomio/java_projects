package jext.sourcecode.project;

import jext.maven.MavenDownloader;

import java.util.Set;

public interface LibrarySet extends Set<Library> {
    // boolean add(Library library);

    Library get(String nameOrId);

    Library getLibrary(String nameOrId);
    Library getLibrary(Library library);

    Set<Library> getUsedLibraries();
    Set<Library> getUnusedLibraries();
    Set<Library> getLibraries(LibraryType libraryType);

    Library resolve(Library library);
    Set<Library> resolveAll(Set<Library> libraries);

    void checkArtifacts(MavenDownloader md, boolean parallel);
}
