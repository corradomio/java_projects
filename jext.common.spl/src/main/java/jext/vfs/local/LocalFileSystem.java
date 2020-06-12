package jext.vfs.local;

import jext.net.URL;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSystem;

import java.io.File;

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
    public VFileSystem connect() {
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
