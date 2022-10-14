package jext.vfs.svn;

import jext.net.URL;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemException;
import jext.vfs.VProgressMonitor;
import jext.vfs.exceptions.NotConnectedException;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SVNFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private SVNRepository repository;
    private boolean connected;
    private SVNFile root;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SVNFileSystem(URL url, java.util.Properties props) {
        super(url, props);
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
        if (isConnected())
            return this;

        try {
            connectTo();
            createRoot();

            connected = true;

            logger.infof("Connected to %s", url);
        }
        catch (SVNException e) {
            throw new IOException(e);
        }

        return this;
    }

    @Override
    public void close() {
        if (connected) {
            logger.infof("Disconnect from %s", url);

            repository.closeSession();
            connected = false;
            repository = null;
        }
    }

    // ----------------------------------------------------------------------
    // Global Operations
    // ----------------------------------------------------------------------

    @Override
    public void deleteAll(VFile rroot, VProgressMonitor pm) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    SVNRepository getrepo() { return repository; }

    private void connectTo() throws SVNException, IOException {
        SVNURL svnurl = SVNUtils.setup(url);
        repository = SVNRepositoryFactory.create(svnurl);

        if (props.containsKey(URL.USERNAME)) {
            String username = props.getProperty(URL.USERNAME);
            String password = props.getProperty(URL.PASSWORD);
            ISVNAuthenticationManager authManager =
                SVNWCUtil.createDefaultAuthenticationManager(username, password);
            repository.setAuthenticationManager(authManager);
        }

        repository.testConnection();

        SVNNodeKind nodeKind = repository.checkPath( "" ,  -1 );
        if (nodeKind != SVNNodeKind.DIR) {
            throw new IOException("The SVN root is not a directory");
        }

    }

    private void createRoot() throws SVNException {
        List entries = new ArrayList();
        SVNDirEntry entry = repository.getDir("", -1, false, entries);
        root = new SVNFile(this, "", entry);
    }

}
