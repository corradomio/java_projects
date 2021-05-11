package jext.versioning.svn;

import jext.io.filters.FileFilters;
import jext.util.FileUtils;
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

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

public class SVNVersioningSystem  extends AbstractVersioningSystem {

    private static final String SVN = ".svn";

    private final FileFilter svnExclude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SVNVersioningSystem(String surl, Properties properties) {
        super(surl, properties);
        this.svnExclude = FileFilters.or(
            this.localExclude,
            FileFilters.wildcards(SVN));
    }

    @Override
    public boolean exists(File localDirectory) {
        File svnDir = new File(localDirectory, SVN);
        return localDirectory.exists() && svnDir.exists();
    }

    @Override
    public void checkout(File localDirectory) {
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

            clientManager.getUpdateClient().doCheckout(svnUrl, localDirectory,
                SVNRevision.HEAD,
                SVNRevision.HEAD,
                SVNDepth.UNKNOWN,
                true);
        }
        catch (Exception e) {
            throw new VersioningSystemException(e);
        }
    }

    @Override
    public void update(File localDirectory) {
        super.update(localDirectory);
    }

    @Override
    public void commit(File localDirectory) {
        super.commit(localDirectory);
    }

    @Override
    public void copy(File localDirectory, File savedDirectory) {
        FileUtils.copy(localDirectory, savedDirectory, svnExclude);
    }

    @Override
    public void delete(File localDirectory) {
        FileUtils.delete(localDirectory, localExclude);
    }

}
