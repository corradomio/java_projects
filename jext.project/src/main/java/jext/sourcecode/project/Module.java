package jext.sourcecode.project;

import jext.name.RefIdNamed;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public interface Module extends RefIdNamed {

    /** Owner project */
    Project getProject();

    /** Module properties */
    Properties getProperties();

    /** Module path directory (relative to projectHome) */
    String getPath();

    /** Module home directory (physical path) */
    File getModuleHome();

    // -- modules

    /** Module dependencies */
    List<Module> getDependencies();

    // -- sources

    Sources getSources();

    // -- libraries

    /** Maven repository urls defined in the module */
    Set<String> getMavenRepositories();
    Set<LibraryRepository> getLibraryRepositories();

    /**
     * Module runtime library.
     * The library is retrieved in the following way:
     *
     *  1) check the configuration file for 'java source compatibility mode'
     *  2) check if the project has a 'runtime.library' property defined (NOT 'auto' or '')
     *  3) uses a 'default runtime library' embedded in the code ('jdk11')
     *
     */
    Library getRuntimeLibrary();

    /**
     * Libraries used by the module (local & remote) with the version specified in the module
     * That is:
     *      if this module uses a library with version v1 but there is another module that
     *      uses the same library with version v2
     *
     * this function return the library with v1 !
     */
    Set<Library> getDeclaredLibraries();
    Set<Library> getLocalLibraries();

    /** Retrieve the library by id/fullname/name */
    Library getLibrary(String nameOrId);

    // -- resources
    // 01/06/2021: not necessary

    Resources getResources();

    // -- types

    /** check if the module contains types */
    boolean isEmpty();

    /** List of types defined inside the module */
    Set<RefType> getTypes();

    /** List of types used (in imports) in the implementations */
    Set<RefType> getUsedTypes();

    /** Module digest as composition of source's digests */
    String getDigest();
}
