package jext.vfs.compress;

import jext.compress.Archives;
import jext.logging.Logger;
import jext.net.URL;
import jext.util.PathUtils;
import jext.util.PropertiesUtils;
import jext.vfs.AbstractFileSystem;
import jext.vfs.VFile;
import jext.vfs.VFileSelector;
import jext.vfs.VFileSystem;
import jext.vfs.VProgressMonitor;
import jext.vfs.util.FileCount;
import jext.vfs.util.ProgressMonitor;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CompressFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(CompressFileSystem.class);

    private static final String UPLOAD_HOME = "uploadHome";

    private File compressedFile = null;

    private final CEntries entries = new CEntries();
    private CompressedFile root;
    private String fileName;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CompressFileSystem(URL url, Properties props) {
        super(url, props);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isConnected() {
        return compressedFile != null;
    }

    @Override
    public VFile getRoot() {
        return root;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public VFileSystem connect() throws IOException {

            initialize();
            scanEntries();
            return this;
        }

    @Override
    public void close() {

    }

    @Override
    public void copyInto(File lroot, VFile rroot, VFileSelector exclude, VProgressMonitor pm) {
        FileCount fc = new FileCount();

        logger.infof("Uncompress %s into %s ...", compressedFile.getAbsolutePath(), lroot.getAbsolutePath());

        if (pm == null)
            pm = new ProgressMonitor();

        try {
            pm.onStart(2);

            {
                pm.onStartTask("Count files to copy", 1);
                countFiles(fc, pm);
                pm.endTask();
            }
            {
                pm.onStartTask("Copy files", fc.nFiles);
                copyFiles(lroot, fc, pm);
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

        logger.infof("Done");
    }

    @Override
    public void deleteAll(VFile root, VProgressMonitor pm) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(File lroot, VFile root, VProgressMonitor pm) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void commit(File lroot, VFile root, VProgressMonitor pm) {
        throw new UnsupportedOperationException();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void initialize() throws FileNotFoundException {
        // resolve "${...}"
        String path = PropertiesUtils.resolveValue(url.getPath(), props);
        path = PathUtils.normalize(path);
        String uploadFolder = props.getProperty(UPLOAD_HOME, null);
        int pos;

        compressedFile = new File(path);
        if (!compressedFile.exists() && uploadFolder != null)
            compressedFile = new File(uploadFolder, path);

        // if (folder != null)
        //     compressedFile = new File(folder, path);
        // else
        //     compressedFile = new File(path);

        if (!compressedFile.exists())
            throw new FileNotFoundException(compressedFile.getAbsolutePath());

        fileName = compressedFile.getName();
        if (path.contains(".tar.")) {
            pos = fileName.lastIndexOf(".tar.");
            fileName = fileName.substring(0, pos);
        }
        else {
            pos = fileName.lastIndexOf('.');
            fileName = fileName.substring(0, pos);
        }
    }

    private void scanEntries() throws IOException {

        try (ArchiveInputStream astream = openArchive()) {
            ArchiveEntry ae;
            while((ae = astream.getNextEntry()) != null)
                entries.add(ae);
        }

        root = new CompressedFile(this, select(""));
    }

    private void countFiles(FileCount fc, VProgressMonitor pm) throws Exception {
        ArchiveEntry ae;

        try (ArchiveInputStream astream = openArchive()) {
            if (pm.isAborted()) return;
            while((ae = astream.getNextEntry()) != null) {
                if (!astream.canReadEntryData(ae))
                    continue;
                if (ae.isDirectory())
                    fc.addFolder();
                else
                    fc.addFile(ae.getSize());
            }
        }
    }

    private void copyFiles(File lroot, FileCount fc, VProgressMonitor pm) throws Exception {
        ArchiveEntry ae;

        try (ArchiveInputStream astream = openArchive()) {
            if (pm.isAborted()) return;
            while((ae = astream.getNextEntry()) != null) {
                if (!astream.canReadEntryData(ae))
                    continue;
                if (ae.isDirectory())
                    mkFolder(lroot, ae, fc);
                else
                    extractFile(lroot, ae, fc, astream);
            }
        }
    }

    private void mkFolder(File lroot, ArchiveEntry ae, FileCount fc) throws IOException {
        String name = entryName(ae);
        File folder = new File(lroot, name);
        if (!folder.exists() && !folder.mkdirs())
            throw new IOException(String.format("Unable to create folder %s", folder.getAbsolutePath()));

        fc.processFolder();
    }

    private void extractFile(File lroot, ArchiveEntry ae, FileCount fc, ArchiveInputStream i) throws IOException {
        String name = entryName(ae);
        File file = new File(lroot, name);
        File folder = file.getParentFile();
        if (!folder.exists() && !folder.mkdirs())
            throw new IOException(String.format("Unable to create folder %s", folder.getAbsolutePath()));

        try(OutputStream o = new FileOutputStream(file)) {
            IOUtils.copy(i, o);
        }
        fc.processFile(ae.getSize());
    }

    private String entryName(ArchiveEntry ae) {
        String name = ae.getName();
        if (name.startsWith(fileName))
            name = name.substring(fileName.length());
        return name;
    }

    private ArchiveInputStream openArchive() throws IOException {
        return Archives.openArchive(compressedFile);
    }

    CEntry select(String path) {
        return entries.select(path);
    }

}
