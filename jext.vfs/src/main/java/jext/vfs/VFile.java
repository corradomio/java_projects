package jext.vfs;

import java.util.List;

public interface VFile {

    /**
     * Filesystem owner of this virtual file
     */
    VFileSystem getFileSystem();

    /**
     * Retrieve the object used to handle the file's name
     */
    VFileName getFileName();

    /**
     * Parent file
     */
    VFile getParentFile();

    /**
     * List the files inside the current directory.
     * If the virtual file is a FILE, the list is empty
     */
    List<VFile> listFiles();

    /**
     * List the files accepted by the selector
     */
    List<VFile> listFiles(VFileSelector selector);

    // "/"    -> get root
    // "."    -> get this
    // ".."   -> get parent
    // "..."  -> get by relative path

    /**
     * Create a new file descriptor.
     *
     * Note: this DOESN'T create a PHYSICAL file,
     *       but only the descriptor!
     */
    VFile newFile(String path);

    /**
     * Check if the virtual file exists on the phisical storage
     */
    boolean exists();

    /**
     * Retrieve the file type:
     *
     *  - FILE
     *  - FOLDER
     *  - UNKNOWN (if the file doesnt' exist)
     *
     *  Note: hard/symbolik links doesnt' supported yet
     *
     */
    VFileType getType();

    /**
     * Check if the file is a phisical file
     */
    boolean isFile();

    /**
     * Chekc if the file is a directory
     */
    boolean isFolder();

    /**
     * Create a EMPTY file of the specified type
     * @param type
     */
    boolean create(VFileType type);

    /**
     * Delete the file of the directory.
     * Note, the directory MUST BE empty
     */
    boolean delete();

    /**
     * Delete the file or the directory.
     * If 'recursively' is true, delete recursively.
     *
     * @param recursively
     */
    boolean delete(boolean recursively);

    /**
     * Create a EMPTY file
     */
    boolean createFile();

    /**
     * Create a EMPTY directory
     */
    boolean createFolder();

    /**
     * Retrieve the object used to access the content of the file
     */
    VFileContent getContent();

    // ----------------------------------------------------------------------
    // Compatibility
    // ----------------------------------------------------------------------

    String getName();

    long length();
}
