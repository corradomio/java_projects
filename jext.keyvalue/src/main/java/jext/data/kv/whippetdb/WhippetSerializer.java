package jext.data.kv.whippetdb;

import io.github.whippetdb.db.api.TypeIO;
import io.github.whippetdb.db.api.types.BooleanIO;
import io.github.whippetdb.db.api.types.BytesIO;
import io.github.whippetdb.db.api.types.CharsIO;
import io.github.whippetdb.db.api.types.IntIO;
import io.github.whippetdb.db.api.types.LongIO;
import io.github.whippetdb.memory.api.MemArray;
import io.github.whippetdb.memory.api.MemDataArray;
import io.github.whippetdb.memory.basic.SimpleHeapDataBuffer;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class WhippetSerializer {

    private static class MemDataArrayOutputStream extends OutputStream {

        private MemDataArray buf;
        private byte[] bytes = new byte[1];
        private long addr;

        MemDataArrayOutputStream(MemDataArray buf) {
            this.buf = buf;
            this.addr = 0;
        }

        @Override
        public void write(@NotNull byte[] b, int off, int len) {
            buf.write(addr, b, off, len);
            addr += len;
        }

        @Override
        public void write(int b) throws IOException {
            bytes[0] = (byte) b;
            write(bytes, 0, 1);
        }
    }

    private static class MemDataArrayInputStream extends InputStream {
        private MemDataArray buf;
        private byte[] bytes = new byte[1];
        private long addr;
        private long size;

        MemDataArrayInputStream(MemDataArray buf) {
            this.buf = buf;
            this.addr = 0;
            this.size = buf.size();
        }

        @Override
        public int read() throws IOException {
            read(bytes, 0, 1);
            return bytes[0];
        }
        public int read(byte b[], int off, int len) {
            long rest = size - addr;
            if (rest < len)
                len = (int) rest;
            buf.read(addr, b, 0, len);
            addr += len;
            return len;
        }
    }

    private static class SerializeIO implements TypeIO {

        private Class clazz;

        SerializeIO(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Class dataType() {
            return clazz;
        }

        @Override
        public MemArray convert(Object obj, MemDataArray tmpBuf) {
            byte[] data = SerializationUtils.serialize((Serializable) obj);
            return new SimpleHeapDataBuffer(data, data.length);
        }

        @Override
        public void writeObject(Object obj, MemDataArray buf) {
            SerializationUtils.serialize((Serializable) obj, new MemDataArrayOutputStream(buf));
        }

        @Override
        public Object readObject(MemDataArray buf) {
            // int n = (int) buf.size();
            // byte[] data = new byte[n];
            // buf.read(0, data, 0, n);
            return SerializationUtils.deserialize(new MemDataArrayInputStream(buf));
        }
    }

    public static <T> TypeIO<T> create(Class<T> clazz) {
        if (clazz == Integer.class || clazz == int.class)
            return (TypeIO<T>) new IntIO();
        if (clazz == Long.class || clazz == long.class)
            return (TypeIO<T>) new LongIO();
        if (clazz == Boolean.class || clazz == boolean.class)
            return (TypeIO<T>) new BooleanIO();
        if (clazz == String.class)
            return (TypeIO<T>) new SerializeIO(clazz);
        if (CharSequence.class.isAssignableFrom(clazz))
            return (TypeIO<T>) new CharsIO();
        if (clazz == byte[].class)
            return (TypeIO<T>) new BytesIO();
        else
            return (TypeIO<T>) new SerializeIO(clazz);
    }
}
