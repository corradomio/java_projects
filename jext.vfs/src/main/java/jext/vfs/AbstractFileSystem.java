package jext.vfs;

import jext.logging.Logger;
import jext.net.URL;
import jext.vfs.exceptions.NotConnectedException;
import jext.vfs.util.Authentication;
import jext.vfs.util.FileCount;
import jext.vfs.util.ProgressMonitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractFileSystem implements VFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());
    protected Properties props = new Properties();
    protected URL url;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected AbstractFileSystem(URL url, Properties props) {
        this.url = url;
        this.props.putAll(props);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getURL() {
        return url.toString();
    }

    @Override
    public Properties getProperties() {
        return props;
    }

    @Override
    public Authentication getAuthentication() {
        return new Authentication(props);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // Global Operations
    // ----------------------------------------------------------------------

    public void copyInto(File lroot, VFile rroot, VFileSelector exclude, VProgressMonitor pm) {
        FileCount fc = new FileCount();
        fc.buffer = new byte[4096];

        if (pm == null)
            pm = new ProgressMonitor();

        try {
            pm.onStart(2);

            {
                pm.onStartTask("Count files to copy", 1);
                countFiles(rroot, exclude, fc, pm);
                pm.endTask();
            }
            {
                pm.onStartTask("Copy files", fc.nFiles);
                copyFiles(rroot, exclude, lroot, fc, pm);
                pm.endTask();
            }

            fc.validate(true);

            if (pm.isAborted())
                pm.onAborted();
            else
                pm.onSuccess();
        }
        catch (Exception e) {
            logger.error(e, e);
            pm.onFailed(e);
        }
    }

    public void deleteAll(VFile rroot, VProgressMonitor pm) {
        FileCount fc = new FileCount();
        fc.buffer = new byte[4096];

        try {
            pm.onStart(2);

            {
                pm.onStartTask("Count files to delete", 0);
                countFiles(rroot, null, fc, pm);
                pm.endTask();
            }
            {
                pm.onStartTask("Delete files", fc.nFiles + fc.nFolders);
                deleteFiles(rroot, fc, pm);
                pm.endTask();
            }

            fc.validate(false);

            pm.onSuccess();
        }
        catch (Exception e) {
            logger.error(e, e);
            pm.onFailed(e);
        }
    }

    private void countFiles(VFile file, VFileSelector exclude, FileCount fc, VProgressMonitor pm) {
        if (pm.isAborted()) return;

        if (exclude != null && exclude.accept(file))
            return;

        if (file.isFolder()) {
            fc.addFolder();

            for(VFile f : file.listFiles())
                countFiles(f, exclude, fc, pm);
        }
        else {
            fc.addFile(file.length());
        }
    }

    private boolean copyFiles(VFile rfile, VFileSelector exclude, File lfile, FileCount fc, VProgressMonitor pm) throws IOException {
        boolean valid = true;

        if (pm.isAborted())
            return false;

        if (exclude != null && exclude.accept(rfile))
            return true;

        if (rfile.isFolder()) {
            fc.processFolder();

            boolean created = lfile.mkdirs();
            logger.debugf("Created directory %s with result %b", lfile.getAbsolutePath(), created);

            for(VFile rchild : rfile.listFiles()) {
                File lchild = new File(lfile, rchild.getName());
                valid &= copyFiles(rchild, exclude, lchild, fc, pm);
            }

            return valid;
        }

        try {
            long length = rfile.length();
            lfile.getParentFile().mkdirs();
            try (FileOutputStream out = new FileOutputStream(lfile)) {
                rfile.getContent().copyInto(out);
            }

            pm.onUpdate(1);
            fc.processFile(length);
            return true;
        }
        catch (IOException t) {
            logger.errorf("Unable to copy %s into %s: %s", rfile.getFileName().getPath(), lfile.getAbsolutePath(), t.toString());
            pm.onUpdate(1);
            return false;
        }

    }

    private boolean deleteFiles(VFile file, FileCount fc, VProgressMonitor pm) {
        boolean valid = true;

        if (pm.isAborted())
            return false;

        boolean isFolder = file.isFolder();
        long length = file.length();

        if (isFolder) {
            for(VFile f : file.listFiles())
                valid &= deleteFiles(f, fc, pm);
        }

        // try {
            if (!file.delete()) {
                logger.errorf("Unable to delete %s", file.getFileName().getPath());
                valid = false;
            }

            pm.onUpdate(1);

            if (isFolder)
                fc.processFolder();
            else
                fc.processFile(length);
        // }
        // catch (Throwable t) {
        //     logger.errorf("Unable to delete %s: %s", file.getFileName().getPath(), t.toString());
        //     pm.onUpdate(1);
        //     return false;
        // }

        return valid;
    }

    // ----------------------------------------------------------------------
    // Versioning
    // ----------------------------------------------------------------------

    @Override
    public void update(File lroot, VFile rroot, VProgressMonitor pm) {
        throw new UnsupportedOperationException("commit");
    }

    @Override
    public void commit(File lroot, VFile rroot, VProgressMonitor pm) {
        throw new UnsupportedOperationException("update");
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    protected void checkfs() {
        if (!isConnected())
            throw new NotConnectedException();
    }

}
