package jext.compress.tgz;

import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class TarGZArchiveInputStream extends TarArchiveInputStream {

    public TarGZArchiveInputStream(InputStream is) throws IOException {
        super(new GZIPInputStream(is));
    }

    public TarGZArchiveInputStream(InputStream is, String encoding) throws IOException {
        super(new GZIPInputStream(is), encoding);
    }

    public TarGZArchiveInputStream(InputStream is, int blockSize) throws IOException {
        super(new GZIPInputStream(is), blockSize);
    }

    public TarGZArchiveInputStream(InputStream is, int blockSize, String encoding) throws IOException {
        super(new GZIPInputStream(is), blockSize, encoding);
    }

    public TarGZArchiveInputStream(InputStream is, int blockSize, int recordSize) throws IOException {
        super(new GZIPInputStream(is), blockSize, recordSize);
    }

    public TarGZArchiveInputStream(InputStream is, int blockSize, int recordSize, String encoding) throws IOException {
        super(new GZIPInputStream(is), blockSize, recordSize, encoding);
    }
}
