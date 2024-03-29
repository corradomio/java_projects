package jext.data.kv.leveldb;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

class ByteBufferBackedOutputStream extends OutputStream {
    protected final ByteBuffer _b;

    ByteBufferBackedOutputStream(ByteBuffer buf) {
        this._b = buf;
    }

    public void write(int b) throws IOException {
        this._b.put((byte)b);
    }

    public void write(byte[] bytes, int off, int len) throws IOException {
        this._b.put(bytes, off, len);
    }
}
