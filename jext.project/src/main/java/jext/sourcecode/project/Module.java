package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;
import jext.sourcecode.project.util.SourceRoot;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public interface Module extends IdNamed {

    String getId();

    Name getName();

    /** Owner project */
    Project getProject();

    /** Module properties */
    Properties getProperties();

    /** Module home directory (relative to projectHome) */
    String getPath();

    /** Module root directory (physical path) */
    File getModuleHome();

    // -- modules

    /** Module dependencies */
    List<Module> getDependencies(boolean recursive);

    // -- sources


    /** Sources defined inside the module */
    List<Source> getSources();
    Set<File> getSourceRoots();

    /** Retrieve a source by id/full name/name */
    Source getSource(String name);

    // -- resources/libraries

    Library getRuntimeLibrary();

    /** Libraries used by the module (local & remote) */
    List<Library> getLibraries();

    /** Retrieve a library by id/full name/name */
    Library getLibrary(String nameOrId);

    /** Resources used by the module */
    List<Resource> getResources();

    Resource getResource(String nameOrId);

    // -- types

    /**
     * List of types defined inside the module
     * It is possible to specify if to include the types
     * defined inside included external libraries
     */
    Set<RefType> getTypes();

    /** List of types used (in imports) in the implementations */
    Set<RefType> getUsedTypes();

}
