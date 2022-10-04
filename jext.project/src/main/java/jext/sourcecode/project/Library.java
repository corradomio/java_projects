package jext.sourcecode.project;

import jext.name.IdNamed;
import jext.name.Name;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface Library extends IdNamed {

    // String getId();
    // Name   getName();

    /** Library version defined in module configuration */
    String getVersion();

    /** Project owner of this library */
    Project getProject();

    /**
     * Check if the library is valid, that is the list of files
     * returned by 'getFiles()' are present locally
     */
    boolean isValid();

    /**
     * Library type:
     *
     *      INVALID     the library is invalid for some reason
     *      RUNTIME     is a configured runtime library
     *      LOCAL       is a local library, present in the local filesystem
     *      REMOTE      is a library downloaded from an external library manager
     *
     */
    LibraryType getLibraryType();

    /**
     * Library status
     *
     *      UNUSED      library declared but not used because the same library is
     *                  present with a higher version number
     *      VALID       library with the latest version number
     *      UPGRADEABLE library with version A.B.C but there exists a new library with
     *                  version A.B.D or A.C
     *      OBSOLETE    library with version A.B.C but there exists a new library with
     *                  version B.X.Y
     *      INCONSISTENT  the latest library version has a version number lower than the
     *                  current library
     *      NOTEXISTENT the library doesn't exist in the external repository
     *
     *      LATEST_VERSION_NOT_AVAILABLE
     *                  it is not possible to retrieve the library latest version
     */
    LibraryStatus getLibraryStatus();

    /** Programming language where the library can be used */
    String getLanguage();

    /** File path where the library is saved */
    String getPath();

    /**
     * Local library file.
     * For MAVEN libraries, it is the ".pom" file
     * For other libraries, it is the directory containing the compressed &
     * expanded version of the library file
     */
    File getFile();

    /**
     * List of file composing this library.
     * In general it is composed by a single file but for
     * MAVEN libraries with "pom" packaging or C#/Python libraries
     * it can be a list of files
     * are composed by 1+ files
     */
    List<File> getFiles();

    // -----------------------------------------------------------
    // Library dependencies
    // -----------------------------------------------------------

    /**
     * Retrieve the list of dependencies
     * @return list of other libraries necessary to use this library
     */
    Set<Library> getDependencies();

    // -----------------------------------------------------------
    // Type support for library dependency resolution
    // -----------------------------------------------------------

    /**
     * List of types defined in the library
     * (not efficient)
     */
    Set<RefType> getTypes();

    /**
     * Check if the type is defined in the library.
     * This could be more efficient than
     *
     *      getTypes().contains('type')
     *
     * @param typeName type name to check
     * @return true if the type is present in the library
     */
    boolean contains(Name typeName);

}
