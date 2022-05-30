package jext.sourcecode.project;

public interface LibraryFinderManager {

    /** Create a NEW LibraryFinder for each call */
    LibraryFinder newLibraryFinder(String language);
}
