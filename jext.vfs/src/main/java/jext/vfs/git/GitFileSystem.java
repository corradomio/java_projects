package jext.vfs.git;

import jext.net.URL;
import jext.util.PropertiesUtils;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSelector;
import jext.vfs.VFileSystem;
import jext.vfs.VFileSystemException;
import jext.vfs.VProgressMonitor;
import jext.vfs.util.Authentication;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

class GitProgressMonitor implements ProgressMonitor {

    private VProgressMonitor pm;

    GitProgressMonitor(VProgressMonitor pm) {
        this.pm = pm;
    }

    @Override
    public void start(int totalTasks) {
        pm.onStart(totalTasks);
    }

    @Override
    public void beginTask(String title, int totalWork) {
        pm.onStartTask(title, totalWork);
    }

    @Override
    public void update(int completed) {
        pm.onUpdate(completed);
    }

    @Override
    public void endTask() {
        pm.endTask();
    }

    @Override
    public boolean isCancelled() {
        return pm.isAborted();
    }
}

public class GitFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String BRANCH = "branch";
    public static final String BARE = "bare";
    public static final String SUBMODULES = "submodules";
    public static final String NOCHECKOUT = "nocheckout";

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private boolean connected;
    private GitFile root;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GitFileSystem(URL url, Properties props) {
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
    public VFileSystem connect() {
        connected = true;
        this.root = new GitFile(this);
        return this;
    }

    @Override
    public void close() {
        connected = false;
    }

    // ----------------------------------------------------------------------
    // Global operations
    // ----------------------------------------------------------------------

    @Override
    public void copyInto(File lroot, VFile rroot, VFileSelector exclude, VProgressMonitor pm) {
        checkfs();

        if (!rroot.getName().equals(""))
            throw new VFileSystemException("Unable to copy NOT ROOT folders");

        if (pm == null)
            pm = new jext.vfs.util.ProgressMonitor();

        /*
1140 [main] DEBUG jext.vfs.git.GitFileSystem  - beging task remote: Enumerating objects: 0
1141 [main] DEBUG jext.vfs.git.GitFileSystem  - beging task remote: Counting objects: 257
1331 [main] DEBUG jext.vfs.git.GitFileSystem  - beging task remote: Compressing objects: 138
1355 [main] DEBUG jext.vfs.git.GitFileSystem  - beging task Receiving objects: 32483
...
4283 [main] DEBUG jext.vfs.git.GitFileSystem  - onUpdate 1 (3114) ...
...
73907 [main] DEBUG jext.vfs.git.GitFileSystem  - end task
74072 [main] DEBUG jext.vfs.git.GitFileSystem  - beging task Resolving deltas: 14836
74779 [main] DEBUG jext.vfs.git.GitFileSystem  - onUpdate 1 (9772) ...
75044 [main] DEBUG jext.vfs.git.GitFileSystem  - end task
75406 [main] DEBUG jext.vfs.git.GitFileSystem  - beging task Checking out files: 536
75897 [main] DEBUG jext.vfs.git.GitFileSystem  - end task
         */

        // check if it is necessary to use User&Password
        Properties props = rroot.getFileSystem().getProperties();
        Authentication auth = rroot.getFileSystem().getAuthentication();

        Git result = null;

        try {
            CloneCommand clone = Git.cloneRepository();
            clone.setURI(url.getUrl(true))
                .setDirectory(lroot)
                .setGitDir(new File(lroot, ".git"))
                .setProgressMonitor(new GitProgressMonitor(pm));

            if (auth.isAuthenticated())
            {
                String username = auth.getUsername();
                String password = auth.getPassword();

                clone.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            }

            if (props.containsKey(BRANCH)) {
                String branch = props.getProperty(BRANCH);
                clone.setBranch(branch);
            }

            if (props.containsKey(BARE)) {
                boolean bare = PropertiesUtils.getBoolean(props, BARE, false);
                clone.setBare(bare);
            }

            if (props.containsKey(SUBMODULES)) {
                boolean submodules = PropertiesUtils.getBoolean(props, SUBMODULES, false);
                clone.setCloneSubmodules(submodules);
            }

            if (props.containsKey(NOCHECKOUT)) {
                boolean nocheckout = PropertiesUtils.getBoolean(props, NOCHECKOUT, false);
                clone.setNoCheckout(nocheckout);
            }

            result = clone.call();

            pm.onSuccess();
        }
        catch (Exception e) {
            pm.onFailed(e);
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if (result != null)
                result.close();
        }
    }

    @Override
    public void update(File lroot, VFile rroot, VProgressMonitor pm) {

        if (!rroot.getName().equals(""))
            throw new VFileSystemException("Unable to commit NOT ROOT folders");

        if (pm == null)
            pm = new jext.vfs.util.ProgressMonitor();

        Authentication auth = rroot.getFileSystem().getAuthentication();

        Git git = null;
        try {
            pm.onStart(1);
            pm.onStartTask("Update", 1);

            git = Git.open(lroot);

            PullCommand update = git.pull();

            if (auth.isAuthenticated())
            {
                String username = auth.getUsername();
                String password = auth.getPassword();

                update.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            }

            PullResult r = update.call();

            pm.endTask();
            pm.onSuccess();
        }
        catch (Exception e) {
            pm.onFailed(e);
        }
        finally {
            if (git != null)
                git.close();
        }

    }

    @Override
    public void commit(File lroot, VFile rroot, VProgressMonitor pm) {

        if (!rroot.getName().equals(""))
            throw new VFileSystemException("Unable to commit NOT ROOT folders");

        if (pm == null)
            pm = new jext.vfs.util.ProgressMonitor();

        Authentication auth = rroot.getFileSystem().getAuthentication();

        Git git = null;
        try {
            pm.onStart(1);
            pm.onStartTask("Commit", 1);

            git = Git.open(lroot);

            git.add().addFilepattern(".").call();

            CommitCommand commit = git.commit();

            if (auth.isAuthenticated())
            {
                String username = auth.getUsername();
                String password = auth.getPassword();

                commit.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            }

            String message = String.format("SPL commit %s", new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").format(new Date()));
            commit.setMessage(message).call();

            pm.endTask();
            pm.onSuccess();
        }
        catch (Exception e) {
            pm.onFailed(e);
        }
        finally {
            if (git != null)
                git.close();
        }

    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
