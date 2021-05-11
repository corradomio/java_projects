package jext.versioning;

import java.io.File;

public interface VersioningSystem {

    /**
     * Check if a local snapshot is present
     */
    boolean exists(File localDirectory);

    /**
     * Copy the selected snapshot locally
     */
    void checkout(File localDirectory) throws VersioningSystemException;

    /**
     * Update the selected snapshot locally
     */
    void update(File localDirectory) throws VersioningSystemException;

    /**
     * Update the remote snapshot with the local copy
     */
    void commit(File localDirectory) throws VersioningSystemException;

    /**
     * Save the current local snapshot in another directory
     */
    void copy(File localDirectory, File savedDirectory);

    /**
     * Delete the local snapshot
     */
    void delete(File localDirectory);

}
