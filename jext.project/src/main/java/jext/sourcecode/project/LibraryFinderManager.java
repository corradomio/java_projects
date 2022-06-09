package jext.sourcecode.project;

import java.util.Collection;

public interface LibraryFinderManager {

    Collection<LibraryFinder> getLibraryFinders();

    /** Retrieve the DEFAULT library finder */
    LibraryFinder getLibraryFinder(String language);

    /** Create a NEW LibraryFinder for each call, based on the DEFAULT library finder */
    LibraryFinder newLibraryFinder(Project project);
}
