package jext.sourcecode.project;

public enum LibraryStatus {
    /**  library declared but not used because the same library is
     *   present with a higher version number */
    UNUSED,
    /** library with the latest version number */
    VALID,
    /** library with version A.B.C but there exists a new library with
     *  version A.B.D or A.C */
    UPGRADEABLE,
    /** library with version A.B.C but there exists a new library with
     *  version B.X.Y */
    OBSOLETE,
    /** the latest library version has a version number lower than the
     *  current library */
    INCONSISTENT,
    /** the library doesn't exist in the external repository */
    NOTEXISTENT,
    /** it is not possible to retrieve the library latest version */
    LATEST_VERSION_NOT_AVAILABLE
}
