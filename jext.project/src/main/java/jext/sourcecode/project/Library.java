package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface Library extends IdNamed {

    /**
     * Check if the library is valid, that is the list of files
     * returned by 'getFiles()' are present locally
     *
     * @return
     */
    boolean isValid();

    /** Project owner of this library */
    Project getProject();

    /**
     * Module owner of this library.
     * It is null for runtime libraries and maven libraries because
     * these libraries are shared between multiple modules
     */
    Module getModule();
    String getModuleId();

    /** Library tpe */
    LibraryType getLibraryType();

    /** File path where the library is saved */
    String getPath();

    /** An hash code of the file */
    long getDigest();

    /** Local library file. For MAVEN libraries, it is the ".pom" file */
    File getFile();

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
    Set<RefType> getTypes();

    /**
     * Check if the type is defined in the  library
     *
     * @param typeName type name to check
     * @return
     */
    boolean contains(Name typeName);

    // -----------------------------------------------------------
    // Versions

    /** Library version defined in module configuration */
    String getVersion();

}
