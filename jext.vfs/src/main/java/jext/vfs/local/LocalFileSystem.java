package jext.vfs.local;

import jext.net.URL;
import jext.util.FileUtils;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemException;
import org.apache.commons.vfs2.FileNotFolderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LocalFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private LocalFile root;
    private boolean connected;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public LocalFileSystem(URL url, java.util.Properties props) {
        super(url, props);
        File local = new File(url.getPath());
        this.root = new LocalFile(this, local, "/");
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public VFile getRoot() {
        checkfs();
        return root;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public VFileSystem connect() throws IOException {
        // check if the local directory there exists
        File rootFile = root.getf();
        if (!rootFile.exists())
            throw new FileNotFoundException(rootFile.getAbsolutePath());
        if (!rootFile.isDirectory())
            throw new FileNotFolderException(rootFile.getAbsolutePath());

        connected = true;
        return this;
    }

    @Override
    public void close() {
        connected = false;
    }


    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public File getLocalRoot() {
        return root.getf();
    }

}
