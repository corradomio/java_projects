package jext.sourcecode.project;

public enum LibraryType {
    /** the library is invalid for some reason
     * (for example doesn't exists in the remote repository) */
    INVALID,
    /** is a local library, present in the local filesystem */
    LOCAL,
    /** used ONLY for back-compatibility, replaced by 'REMOTE' */
    MAVEN,
    /** is a library downloaded from an external library manager */
    REMOTE,
    /** is a configured runtime library */
    RUNTIME,
}
