package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface Library extends IdNamed {

    /** Project owner of this library */
    Project getProject();

    /**
     * Module owner of this library.
     * It is null for runtime libraries and maven libraries because
     * these libraries are shared between multiple modules
     */
    String getModuleId();
    Module getModule();

    // /**
    //  * If the library is defined locally (in a local jar)
    //  */
    // boolean isLocal();

    /** Library tpe */
    LibraryType getLibraryType();

    /** File path where the library is defined */
    String getPath();

    /** Local library file. For MAVEN libraries, it is the ".pom" file */
    File getFile();

    String getDigest();

    /** Dependency libraries (MAVEN) */
    List<Library> getDependencies();

    /**
     * List of file composing this library.
     * MAVEN libraries with "pom" packaging and "runtime" libraries
     * are composed by 1+ files
     */
    List<File> getFiles();

    // -- type

    /**
     * List of types defined in the library
     * (not efficient)
     */
    Set<? extends RefType> getTypes();

    /**
     * Check if the type is defined in the  library
     *
     * @param typeName type name to check
     * @return
     */
    boolean contains(Name typeName);

    // -----------------------------------------------------------
    // Versions

    String getVersion();

    // String getLatestVersion();

    String getLatest();

}
