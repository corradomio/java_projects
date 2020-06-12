package jext.vfs;

public interface VFileName {

    /**
     * File name
     */
    String getName();

    /**
     * File extension
     */
    String getExt();

    /**
     * File name WITHOUT extension
     */
    String getNameWithoutExt();

    /**
     * Full path (relative to the root of the filesystem)
     */
    String getPath();

    /**
     * Full parent path (relative to the root of the filesystem)
     */
    String getParentPath();

    /**
     * Mimetype of the file, based on the extension
     */
    String getMimeType();

    /**
     * Check if the file is the root of the filesystem
     */
    boolean isRoot();
}
