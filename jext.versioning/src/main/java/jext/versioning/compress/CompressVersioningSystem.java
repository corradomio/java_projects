package jext.versioning.compress;

import jext.compress.Archives;
import jext.util.FileUtils;
import jext.util.PathUtils;
import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

public class CompressVersioningSystem extends AbstractVersioningSystem {

    private final File compressedFile;
    private final String base;

    public CompressVersioningSystem(String surl, Properties properties) {
        super(surl, properties);
        String path = url.getPath();
        compressedFile = new File(path);
        base = url.getFragment();
    }

    @Override
    public void checkout(File localDirectory) {
        try {
            copyInto(localDirectory);
        }
        catch (IOException e) {
            throw new VersioningSystemException(e);
        }
    }

    @Override
    public void update(File localDirectory) {
        try {
            copyInto(localDirectory);
            CompressedEntry root = scanArchive();
            alignWith(root, localDirectory);
        }
        catch (IOException e) {
            throw new VersioningSystemException(e);
        }
    }

    private void copyInto(File localDirectory) throws IOException {
        try (ArchiveInputStream aistream = Archives.openArchive(compressedFile)) {
            ArchiveEntry entry;
            while((entry = aistream.getNextEntry()) != null) {
                String archivePath = entry.getName();
                String path = PathUtils.relativePath(base, archivePath);
                File file = new File(localDirectory, path);

                if (entry.isDirectory()) {
                    file.mkdirs();
                }
                else {
                    long lastModified = entry.getLastModifiedDate().getTime();
                    try (OutputStream ostream = new FileOutputStream(file)) {
                        FileUtils.copy(aistream, ostream);
                    }
                    file.setLastModified(lastModified);
                }
            }
        }
    }

    private void alignWith(CompressedEntry entry, File dir) throws IOException {
        Set<String> names = entry.names();

        // delete extra files
        for (File file : FileUtils.asList(dir.listFiles())) {
            if (!names.contains(file.getName()))
                FileUtils.delete(file);
        }

        // scan sub directories
        for (CompressedEntry ce : entry.list()) {
            if (!ce.isDirectory())
                continue;

            String name = ce.getName();
            alignWith(ce, new File(dir, name));
        }
    }

    private CompressedEntry scanArchive() throws IOException {
        ArchiveEntry ae;
        CompressedEntry root = new CompressedEntry();
        try (ArchiveInputStream astream = Archives.openArchive(compressedFile)) {
            while((ae = astream.getNextEntry()) != null)
                if (!ae.isDirectory())
                    root.add(ae);
        }
        return root;
    }

}
