package jext.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RARArchiveInputStream extends ArchiveInputStream {

    private Archive archive;

    public RARArchiveInputStream(InputStream is) throws IOException {
        readContent(is);
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        FileHeader fh = archive.nextFileHeader();
        if (fh == null)
            return null;

        return new RARArchiveEntry(fh);
    }

    private void readContent(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;

        while((len = is.read(b)) > 0)
            baos.write(b, 0, len);

        try {
            archive = new Archive(new ByteArrayVolumeManager(baos.toByteArray()));
        } catch (RarException e) {
            throw new IOException(e);
        }
    }
}
