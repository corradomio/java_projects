package jext.versioning.compress;

import jext.compress.Archives;
import jext.versioning.AbstractVersioningSystem;
import jext.versioning.VersioningSystemException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CompressVersioningSystem extends AbstractVersioningSystem {

    private File compressedFile;
    private String base;

    public CompressVersioningSystem(String surl, Properties properties) {
        super(surl, properties);
        String path = this.url.getPath();
        this.compressedFile = new File(path);
        this.base = this.url.getFragment();
    }

    @Override
    public void checkout(File localDirectory) {
        try {
            CompressedFile root = scanArchive();
            root.copyInto(localDirectory);
        }
        catch (IOException e) {
            throw new VersioningSystemException(e);
        }
    }

    @Override
    public void update(File localDirectory) {
        try {
            CompressedFile root = scanArchive();
            root.alignWith(localDirectory);
        }
        catch (IOException e) {
            throw new VersioningSystemException(e);
        }
    }

    private CompressedFile scanArchive() throws IOException {
        ArchiveEntry ae;
        CompressedFile root = new CompressedFile(compressedFile, base);
        try (ArchiveInputStream astream = Archives.openArchive(compressedFile)) {
            while((ae = astream.getNextEntry()) != null)
                if (!ae.isDirectory())
                    root.add(ae);
        }
        return root;
    }

}
