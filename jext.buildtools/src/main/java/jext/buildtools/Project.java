package jext.buildtools;


import java.util.List;
import java.util.Properties;

public interface Project {

    /** Project properties */
    Properties getProperties();

    /** Path of the project on the filesystem, if it exists */
    String getPath();

    /** List of the top level project's modules */
    List<Module> getModules();

    Module getModule(String moduleName);

    // ----------------------------------------------------------------------
    // Library resolver
    // ----------------------------------------------------------------------

    // /** Set the library resolver */
    // Project setLibraryFinder(LibraryFinder lfinder);
    //
    // /** Retrieve the library resolver */
    // LibraryFinder getLibraryFinder();
}
