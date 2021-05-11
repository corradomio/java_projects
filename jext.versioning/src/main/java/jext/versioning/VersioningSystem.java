package jext.versioning;

import java.io.File;

public interface VersioningSystem {

    /**
     * Check if the local versioning system there exists
     */
    boolean exists(File localDirectory);

    /**
     * Delete the local versioning system
     */
    void delete(File localDirectory);

    /**
     * Copy the selected snapshot locally
     */
    void checkout(File localDirectory);

    /**
     * Update the local copy with the latest snapshot
     */
    void update(File localDirectory);

    /**
     * Update the remote copy with the local copy
     */
    void commit(File localDirectory);

    /**
     * Save the current snapshot in another directory
     */
    void save(File savedDirectory);
}
