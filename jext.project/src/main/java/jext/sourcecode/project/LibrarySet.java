package jext.sourcecode.project;

import java.util.Set;

public interface LibrarySet extends Set<Library> {
    boolean add(Library library);

    Library get(String nameOrId);

    Set<Library> getUsedLibraries();
    Set<Library> getUnusedLibraries();
    Set<Library> getLibraries(LibraryType libraryType);

    Library resolve(Library library);
    Set<Library> resolveAll(Set<Library> libraries);
}