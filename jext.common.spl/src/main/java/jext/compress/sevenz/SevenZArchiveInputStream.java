package jext.compress.sevenz;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;

public class SevenZArchiveInputStream extends ArchiveInputStream {

    private SevenZFile file;

    public SevenZArchiveInputStream(InputStream is) throws IOException {
        readContent(is);
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return file.getNextEntry();
    }

    private void readContent(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;

        while((len = is.read(b)) > 0)
            baos.write(b, 0, len);

        SeekableByteChannel channel = new SeekableInMemoryByteChannel(baos.toByteArray());
        file = new SevenZFile(channel);
    }

    @Override
    public void close() throws IOException {
        file.close();
    }
}
