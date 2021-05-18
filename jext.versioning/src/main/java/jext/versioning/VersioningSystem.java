package jext.versioning;

import java.io.File;

public interface VersioningSystem {

    // VersioningSystem setLocalDirectory(File localDirectory);

    // -----------------------------------------------------------------------

    /**
     * Check if a local snapshot is present
     */
    boolean exists();

    /**
     * Copy the selected snapshot locally
     */
    void checkout() throws VersioningSystemException;

    /**
     * Update the selected snapshot locally
     */
    void update() throws VersioningSystemException;

    /**
     * Update the remote snapshot with the local copy
     */
    void commit() throws VersioningSystemException;

    /**
     * Delete the local snapshot
     */
    void delete();

    // -----------------------------------------------------------------------

    // /**
    //  * Save the current local snapshot in another directory
    //  */
    // void copy(File savedDirectory);

    // -----------------------------------------------------------------------

    /**
     * Add an 'ignore' entry in the 'ignore file'
     * @param pattern pattern to ignore
     */
    void addIgnore(String pattern);
}
