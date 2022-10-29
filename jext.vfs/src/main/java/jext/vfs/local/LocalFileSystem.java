package jext.vfs.local;

import jext.net.URL;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSystem;
import jext.vfs.exceptions.NotConnectedException;
import org.apache.commons.vfs2.FileNotFolderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LocalFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private LocalFile root;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public LocalFileSystem(URL url, java.util.Properties props) {
        super(url, props);

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isConnected() {
        return root != null;
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
        try {
            File rootFile = new File(url.getPath());
            // check if the local directory there exists
            if (!rootFile.exists())
                throw new FileNotFoundException(rootFile.getAbsolutePath());
            if (!rootFile.isDirectory())
                throw new FileNotFolderException(rootFile.getAbsolutePath());

            this.root = new LocalFile(this, rootFile, "/");
            return this;
        }
        catch (IOException e) {
            throw new NotConnectedException(e);
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public File getLocalRoot() {
        return root.getf();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
