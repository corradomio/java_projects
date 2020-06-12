package jext.compress.txz;

import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.tukaani.xz.XZInputStream;

import java.io.IOException;
import java.io.InputStream;

public class TarXZArchiveInputStream extends TarArchiveInputStream {

    public TarXZArchiveInputStream(InputStream is) throws IOException, IOException {
        super(new XZInputStream(is));
    }

    public TarXZArchiveInputStream(InputStream is, String encoding) throws IOException {
        super(new XZInputStream(is), encoding);
    }

    public TarXZArchiveInputStream(InputStream is, int blockSize) throws IOException {
        super(new XZInputStream(is), blockSize);
    }

    public TarXZArchiveInputStream(InputStream is, int blockSize, String encoding) throws IOException {
        super(new XZInputStream(is), blockSize, encoding);
    }

    public TarXZArchiveInputStream(InputStream is, int blockSize, int recordSize) throws IOException {
        super(new XZInputStream(is), blockSize, recordSize);
    }

    public TarXZArchiveInputStream(InputStream is, int blockSize, int recordSize, String encoding) throws IOException {
        super(new XZInputStream(is), blockSize, recordSize, encoding);
    }
}
