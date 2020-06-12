package jext.vfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface VFileContent {

    VFile getFile();

    /**
     * Check if the file (or the folder) is empty
     */
    boolean isEmpty();

    /**
     * Retrieve the last modified timestamp of the file
     */
    long lastModified();

    /**
     * Retrieve the length (in byte) of the file or the number of files inside the folder
     * @return
     */
    long length();

    InputStream  openInputStream() throws IOException;

    OutputStream openOutputStream() throws IOException;

    void copyInto(OutputStream out) throws IOException;
}
