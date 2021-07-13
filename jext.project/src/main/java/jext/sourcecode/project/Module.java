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

    /** Source root directories */
    List<File> getSourceRootDirectories();

    // -- modules

    /** Module dependencies */
    List<Module> getDependencies();

    // -- sources

    /** Sources available inside the module */
    List<Source> getSources();

    /** Source roots (relative paths respect moduleHome) */
    Set<String> getSourceRoots();

    /** Retrieve a source by id/fullname/name */
    Source getSource(String name);

    // -- libraries

    /** Maven repository urls defined in the module */
    Set<String> getMavenRepositories();

    /** Module runtime library */
    Library getRuntimeLibrary();

    /**
     * Libraries used by the module (local & remote) but with the highest version
     * That is:
     *      if this module uses a library with v1 but there is another module that
     *      uses the same library with version v2
     *
     * this function return the library with v2 !
     */
    Set<Library> getLibraries();

    /**
     * Libraries used by the module (local & remote) with the version specified in the module
     * That is:
     *      if this module uses a library with version v1 but there is another module that
     *      uses the same library with version v2
     *
     * this function return the library with v1 !
     */
    Set<Library> getDeclaredLibraries();

    /** Retrieve the library by id/fullname/name */
    Library getLibrary(String nameOrId);

    // -- resources
    // 01/06/2021: not necessary

    /** Resources used by the module */
    List<Resource> getResources();

    /** Retrieve the resource by name or id/fullname/name */
    Resource getResource(String nameOrId);

    // -- types

    /** check if the module contains types */
    boolean isEmpty();

    /** List of types defined inside the module */
    Set<Type> getTypes();

    /** List of types used (in imports) in the implementations */
    Set<RefType> getUsedTypes();

    /** Module digest as composition of source's digests */
    long getDigest();
}
