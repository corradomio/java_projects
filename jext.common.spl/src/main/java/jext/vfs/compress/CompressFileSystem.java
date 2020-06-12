package jext.vfs.compress;

import jext.logging.Logger;
import jext.net.URL;
import jext.vfs.*;
import jext.vfs.util.FileCount;
import jext.vfs.util.ProgressMonitor;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CompressFileSystem extends AbstractFileSystem {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(CompressFileSystem.class);

    private static Map<String, Class<? extends ArchiveInputStream>> archivers = new HashMap<>();

    static {
        try (InputStream inp = VFileSystems.class.getResourceAsStream("/jext/compress/archivers.properties")) {
            Properties props = new Properties();
            props.load(inp);

            for(String atype: props.stringPropertyNames()) {
                try {
                    Class aclass = Class.forName(props.getProperty(atype));

                    archivers.put(atype, aclass);
                }
                catch(Exception e) {
                    logger.error(e, e);
                }
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    private static final String HOME_FOLDER = "$homeFolder";

    private File compressedFile = null;

    private CEntries entries = new CEntries();
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

        try {
            initialize();
            scanEntries();

            return this;

        } catch (Exception e) {
            throw new IOException(e);
        }
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
        String path = url.getPath();
        String folder = props.getProperty(HOME_FOLDER, null);
        int pos;

        if (folder != null)
            compressedFile = new File(folder, path);
        else
            compressedFile = new File(path);

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

    private void scanEntries() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

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
        int count;
        if (!folder.exists() && !folder.mkdirs())
            throw new IOException(String.format("Unable to create folder %s", folder.getAbsolutePath()));

        try(OutputStream o = new FileOutputStream(file)) {
            count = IOUtils.copy(i, o);
        }
        fc.processFile(ae.getSize());
    }

    private String entryName(ArchiveEntry ae) {
        String name = ae.getName();
        if (name.startsWith(fileName))
            name = name.substring(fileName.length());
        return name;
    }

    private ArchiveInputStream openArchive() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        String atype = extensionOf(compressedFile).toLowerCase();
        Class aclass = archivers.getOrDefault(atype, null);
        if (aclass == null)
            throw new IOException(String.format("Unsupported archiver for file %s", compressedFile.getAbsolutePath()));

        InputStream istream =  new FileInputStream(compressedFile);
        ArchiveInputStream astream = (ArchiveInputStream) aclass.getConstructor(InputStream.class).newInstance(istream);
        return astream;
    }

    CEntry select(String path) {
        return entries.select(path);
    }

    private static String extensionOf(File file) {
        int pos;
        String name = file.getName();

        // special handling for "name.tar.*"
        if (name.contains(".tar."))
            pos = name.lastIndexOf(".tar.");
        else
            pos = name.lastIndexOf('.');
        return name.substring(pos+1);
    }

}
