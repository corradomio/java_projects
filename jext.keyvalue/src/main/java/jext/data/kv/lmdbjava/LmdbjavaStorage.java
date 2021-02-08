package jext.data.kv.lmdbjava;

import com.fasterxml.jackson.databind.util.ByteBufferBackedOutputStream;
import jext.data.kv.leveldb.ByteBufferBackedInputStream;
import jext.data.kv.util.AbstractStorage;
import org.apache.commons.lang3.SerializationUtils;
import org.lmdbjava.Cursor;
import org.lmdbjava.Dbi;
import org.lmdbjava.Env;
import org.lmdbjava.Txn;

import java.io.File;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import static java.nio.ByteBuffer.allocateDirect;
import static org.lmdbjava.SeekOp.MDB_NEXT;


public class LmdbjavaStorage<K extends Serializable, V extends Serializable> extends AbstractStorage<K, V> {

    private Env<ByteBuffer> env;
    private Dbi<ByteBuffer> db;

    protected LmdbjavaStorage(File storageFile, Env<ByteBuffer> env, Dbi<ByteBuffer> db) {
        super(storageFile);

        this.env = env;
        this.db = db;
    }

    @Override
    public void close() {
        this.db.close();
        this.env.close();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            final ByteBuffer bkey = allocateDirect(env.getMaxKeySize());
            final ByteBuffer bval = allocateDirect(700);
            final Cursor<ByteBuffer> c = db.openCursor(txn);
            while(c.get(bkey, bval, MDB_NEXT)) {
                K key = SerializationUtils.deserialize(new ByteBufferBackedInputStream(bkey));
                keys.add(key);
            }
        }
        return keys;
    }

    @Override
    public V put(K key, V value) {
        final ByteBuffer bkey = allocateDirect(env.getMaxKeySize());
        final ByteBuffer bval = allocateDirect(700);
        SerializationUtils.serialize(key, new ByteBufferBackedOutputStream(bkey));
        SerializationUtils.serialize(value, new ByteBufferBackedOutputStream(bkey));

        this.db.put(bkey, bval);

        return null;
    }

    @Override
    public V get(Object key) {
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            final ByteBuffer bkey = allocateDirect(env.getMaxKeySize());
            ByteBuffer bval = this.db.get(txn, bkey);
            return SerializationUtils.deserialize(new ByteBufferBackedInputStream(bval));
        }
    }
}
