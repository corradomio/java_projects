package jext.vfs.ftp;

import jext.net.URL;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

public class FTPFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private FTPClient client;
    private FTPClientConfig config;
    private FTPVFile root;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public FTPFileSystem(URL url, java.util.Properties props) {
        super(url, props);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // @Override
    // public VFileSystem setProperty(String key, String value) {
    //     props.setProperty(key, value);
    //     return this;
    // }

    @Override
    public boolean isConnected() {
        return this.client != null && this.client.isConnected();
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

        connectTo();
        createRoot();

        logger.infof("Connected to %s", url.toString());

        return this;
    }

    @Override
    public void close() {
        if (client != null)
        try {
            logger.infof("Disconnect from %s", url.toString());

            client.logout();
            if (client.isConnected())
                client.disconnect();

        }
        catch(IOException e) {
            logger.error(e, e);
        }
        finally {
            client = null;
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    FTPClient getcli() { return this.client; }

    String getrootpath() {
        return root.getftproot();
    }

    private void checkop() throws IOException {
        int reply = client.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)) {
            client.disconnect();
            throw new VFileSystemException("Unable to connect to " + url.toString() + ": " + client.getReplyString());
        }
    }

    private void connectTo() throws IOException {
        client = new FTPClient();
        config = new FTPClientConfig();



        client.configure(config);

        String hostPort = this.url.getHostPort();
        client.connect(hostPort);
        checkop();

        if (!props.containsKey(URL.USERNAME))
            return;

        String username = props.getProperty(URL.USERNAME);
        String password = props.getProperty(URL.PASSWORD);

        client.login(username, password);
        checkop();
    }

    private void createRoot() throws IOException {
        String rootPath = client.printWorkingDirectory();
        this.root = new FTPVFile(this, rootPath, true);
    }

}
