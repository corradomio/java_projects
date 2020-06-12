package jext.compress.rar;

import com.github.junrar.Archive;
import com.github.junrar.Volume;
import com.github.junrar.io.IReadOnlyAccess;

import java.io.IOException;

public class ByteArrayVolume implements Volume, IReadOnlyAccess {

    private Archive archive;
    private byte[] content;
    private int position;


    public ByteArrayVolume(Archive a, byte[] c) {
        archive = a;
        content = c;
    }

    @Override
    public IReadOnlyAccess getReadOnlyAccess() throws IOException {
        return this;
    }

    @Override
    public long getLength() {
        return content.length;
    }

    @Override
    public Archive getArchive() {
        return archive;
    }

    @Override
    public long getPosition() throws IOException {
        return position;
    }

    @Override
    public void setPosition(long pos) throws IOException {
        position = (int) pos;
    }

    @Override
    public int read() throws IOException {
        return content[position++];
    }

    @Override
    public int read(byte[] buffer, int off, int count) throws IOException {
        int len = min(count, content.length-position);
        if (len > 0)
            System.arraycopy(content, position, buffer, off, len);
        else
            len = 0;
        position += len;
        return len;
    }

    @Override
    public int readFully(byte[] buffer, int count) throws IOException {
        int len = min(count, content.length-position);
        if (len > 0)
            System.arraycopy(content, position, buffer, 0, len);
        position += len;
        return len;
    }

    @Override
    public void close() throws IOException {

    }

    private static int min(int x, int y) { return (int)(x < y ? x : y); }
}
