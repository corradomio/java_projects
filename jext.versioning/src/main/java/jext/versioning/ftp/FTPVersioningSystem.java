package jext.versioning.ftp;

import jext.util.PathUtils;
import jext.versioning.AbstractVersioningSystem;
import jext.versioning.util.Authentication;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class FTPVersioningSystem extends AbstractVersioningSystem {

    private FTPClient client;
    private FTPClientConfig config;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public FTPVersioningSystem(String surl, Properties properties) {
        super(surl, properties);
    }

    @Override
    public boolean exists(File localDirectory) {
        return super.exists(localDirectory);
    }

    @Override
    public void checkout(File localDirectory) {
        try {
            RemoteFile root = connect();
            root.copyInto(localDirectory);
        }
        catch (IOException e) {
            logger.error(e, e);
        }
        finally {
            close();
        }
    }

    @Override
    public void update(File localDirectory) {
        try {
            RemoteFile root = connect();
            root.alignWith(localDirectory);
        }
        catch (IOException e) {
            logger.error(e, e);
        }
        finally {
            close();
        }
    }

    private RemoteFile connect() throws IOException {
        client = new FTPClient();
        config = new FTPClientConfig();

        client.configure(config);

        String hostPort = this.url.getHostPort();
        client.connect(hostPort);
        checkop();

        Authentication auth = getAuthentication();
        if (auth.isAuthenticated()) {
            String username = auth.getUsername();
            String password = auth.getPassword();

            client.login(username, password);
            checkop();
        }

        String path = PathUtils.concat(client.printWorkingDirectory(), url.getPath());
        return new RemoteFile(client, path);
    }

    private void close() {
        try {
            if (client != null && client.isConnected())
                client.disconnect();
            client = null;
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private void checkop() throws IOException {
        int reply = client.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)) {
            client.disconnect();
            throw new IOException("Unable to connect to " + url.toString() + ": " + client.getReplyString());
        }
    }

}
