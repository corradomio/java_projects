package jext.versioning.svn;

import jext.io.filters.FileFilters;
import jext.net.URL;
import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;
import jext.versioning.svn.util.SVNLogging;
import jext.versioning.svn.util.SVNUtils;
import jext.versioning.util.Authentication;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.auth.SVNAuthentication;
import org.tmatesoft.svn.core.auth.SVNPasswordAuthentication;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

public class SVNVersioningSystem  extends AbstractVersioningSystem {

    private static final String SVN = ".svn";
    private static final String BRANCH = "branch";
    private static final String TRUNK = "trunk";
    private static final String BRANCHES = "branches";

    private final FileFilter svnExclude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SVNVersioningSystem(String surl, Properties properties) {
        super(surl, properties);
        this.svnExclude = FileFilters.or(
            this.localExclude,
            FileFilters.wildcards(SVN));
        updateUrl();
    }

    private void updateUrl() {
        if (!properties.containsKey(BRANCH))
            return;

        // Default layout
        //
        //      [project_url]
        //          trunk
        //          branches
        //              [branch1]
        //              ...
        //          tags
        //              [tag1]
        //              ...

        String surl;
        String branch = properties.getProperty(BRANCH);

        if (TRUNK.equals(branch))
            surl = String.format("%s/%s", url.getUrl(), branch);
        else if (!branch.startsWith(BRANCHES))
            surl = String.format("%s/branches/%s", url.getUrl(), branch);
        else
            surl = String.format("%s/%s", url.getUrl(), branch);
        this.url = new URL(surl);
    }

    @Override
    public boolean exists() {
        File svnDir = new File(localDirectory, SVN);
        return super.exists() && svnDir.exists();
    }

    @Override
    public void checkout() {
        internalUpdate(localDirectory);
    }

    @Override
    public void update() {
        internalUpdate(localDirectory);
    }

    private void internalUpdate(File localDirectory) {
        try {
            SVNURL svnUrl = SVNUtils.setup(this.url);

            Authentication auth = getAuthentication();

            // ISVNOptions options = new DefaultSVNOptions();
            SVNClientManager clientManager = SVNClientManager.newInstance(/*options*/);
            clientManager.setDebugLog(new SVNLogging(this.logger));

            if (auth.isAuthenticated()) {
                String username = auth.getUsername();
                String password = auth.getPassword();

                SVNPasswordAuthentication svnpa = SVNPasswordAuthentication.newInstance(
                    username,
                    password.toCharArray(),
                    false,
                    null,
                    false
                );

                ISVNAuthenticationManager authManager = new BasicAuthenticationManager(new SVNAuthentication[]{svnpa});
                clientManager.setAuthenticationManager(authManager);
            }

            SVNUpdateClient client = clientManager.getUpdateClient();
            client.doCheckout(
                svnUrl,
                localDirectory,
                SVNRevision.UNDEFINED,
                SVNRevision.HEAD,
                SVNDepth.UNKNOWN,
                false);
        }
        catch (Exception e) {
            throw new VersioningSystemException(e);
        }
    }

    // ----------------------------------------------------------------------

    @Override
    protected FileFilter copyExclude() {
        return svnExclude;
    }

    // ----------------------------------------------------------------------
    // Ignore support
    // ----------------------------------------------------------------------

    @Override
    protected File getIgnoreFile() {
        return new File(localDirectory, ".svnignore");
    }
}
