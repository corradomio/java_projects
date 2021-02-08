package jext.data.kv.leveldb;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedInputStream extends InputStream {

    protected final ByteBuffer _b;

    public ByteBufferBackedInputStream(ByteBuffer buf) {
        this._b = buf;
    }

    @Override
    public int read() throws IOException {
        return _b.get();
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        return _b.get(b, off, len).limit();
    }
}