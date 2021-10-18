package jext.versioning.git;

import jext.io.filters.FileFilters;
import jext.util.PropertiesUtils;
import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;
import jext.versioning.git.util.GitProgressMonitor;
import jext.versioning.util.Authentication;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Properties;

public class GitVersioningSystem  extends AbstractVersioningSystem {

    private static final String GIT = ".git";
    private static final String BRANCH = "branch";
    private static final String NOCHECKOUT = "nocheckout";

    private final FileFilter gitExclude;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GitVersioningSystem(String surl, Properties properties, File localDirectory) {
        super(surl, properties, localDirectory);
        this.gitExclude = FileFilters.or(
            this.localExclude,
            FileFilters.wildcards(GIT));
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean exists() {
        File gitDir = new File(localDirectory, GIT);
        return super.exists() && gitDir.exists();
    }

    @Override
    public void checkout() throws VersioningSystemException {
        File gitDir = new File(localDirectory, GIT);
        Authentication auth = getAuthentication();
        Git result = null;

        try {
            CloneCommand clone = Git.cloneRepository();
            clone.setURI(url.getUrl(true))
                .setDirectory(localDirectory)
                .setGitDir(gitDir)
                .setProgressMonitor(new GitProgressMonitor());

            if (auth.isAuthenticated()) {
                String username = auth.getUsername();
                String password = auth.getPassword();

                clone.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            }

            if (properties.containsKey(BRANCH)) {
                String branch = properties.getProperty(BRANCH);
                clone.setBranch(branch);
            }

            if (properties.containsKey(NOCHECKOUT)) {
                boolean nocheckout = PropertiesUtils.getBoolean(properties, NOCHECKOUT, false);
                clone.setNoCheckout(nocheckout);
            }

            result = clone.call();
        } catch (GitAPIException e) {
            throw new VersioningSystemException(e);
        }
        finally {
            if (result != null)
                result.close();
        }

    }

    @Override
    public void update() throws VersioningSystemException {
        Authentication auth = getAuthentication();

        try(Git git = Git.open(localDirectory)) {
            PullCommand update = git.pull();

            if (auth.isAuthenticated()) {
                String username = auth.getUsername();
                String password = auth.getPassword();

                update.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            }

            PullResult result = update.call();
        } catch (IOException | GitAPIException e) {
            throw new VersioningSystemException(e);
        }
        finally {

        }
    }

    // ----------------------------------------------------------------------

    @Override
    protected FileFilter copyExclude() {
        return gitExclude;
    }

    // ----------------------------------------------------------------------
    // Ignore support
    // ----------------------------------------------------------------------

    @Override
    protected File getIgnoreFile() {
        return new File(localDirectory, ".gitignore");
    }
}
