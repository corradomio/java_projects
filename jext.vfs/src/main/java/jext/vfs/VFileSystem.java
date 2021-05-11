package jext.vfs;

import jext.vfs.util.Authentication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public interface VFileSystem {

    String getURL();

    /**
     * Connect to the filesystem.
     * Available for the filesystems where it is necessary to create a FTP connection
     */
    VFileSystem connect() throws IOException;

    /**
     * Check if the filesystem is connected
     */
    boolean isConnected();

    /**
     * Retrieve the properties
     */
    Properties getProperties();

    /**
     * Retrieve the information about username/password
     */
    Authentication getAuthentication();

    /**
     * Retrieve the root file of the filesystem
     */
    VFile getRoot();

    /**
     * Close the connection with the filesystem
     */
    void close();

    /**
     * Delete recursively the specified directory in the remote filesystem
     *
     * @param root directory to delete
     * @param pm progress monitor
     */
    void deleteAll(VFile root, VProgressMonitor pm);

    //
    // Support for Versioning Systems
    //

    List<Branch> getVersions();

    /**
     * Clone the remote filesystem into local
     * @param lroot where to copy the remote files
     * @param root remote root to copy
     * @param exclude predicate to exclude remote files/resources
     * @param pm progress monitor
     */
    void copyInto(File lroot, VFile root, VFileSelector exclude, VProgressMonitor pm);
    default void copyInto(File lroot, VFile root, VProgressMonitor pm) {
        copyInto(lroot, root, null, pm);
    }

    void copyLocally(VProgressMonitor pm);

    /**
     * Update the local model with the content of the remote repository
     * @param lroot where to copy the remote files
     * @param root remote root to copy
     * @param pm progress monitor
     */
    void update(File lroot, VFile root, VProgressMonitor pm);

    /**
     * Update the remote repository with the content of the local model
     *
     * @param lroot where to copy the remote files
     * @param root remote root to copy
     * @param pm progress monitor
     */
    void commit(File lroot, VFile root, VProgressMonitor pm);
}
