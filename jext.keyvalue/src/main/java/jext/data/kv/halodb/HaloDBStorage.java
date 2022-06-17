package jext.data.kv.halodb;

import com.oath.halodb.HaloDB;
import com.oath.halodb.HaloDBException;
import com.oath.halodb.HaloDBIterator;
import jext.data.kv.KVStorageError;
import jext.data.kv.util.AbstractStorage;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class HaloDBStorage<K extends Serializable, V extends Serializable> extends AbstractStorage<K, V> {

    private HaloDB halodb;

    public HaloDBStorage(File storageFile, HaloDB halodb) {
        super(storageFile);
        this.halodb = halodb;
    }

    @Override
    public void close() {
        try {
            this.halodb.close();
        } catch (HaloDBException e) { }
    }

    @Override
    public Set<K> keySet() {
        Set<K> kset = new HashSet<>();
        HaloDBIterator it = null;
        try {
            it = this.halodb.newIterator();
            while(it.hasNext()) {
                byte[] value = it.next().getKey();
                K key = SerializationUtils.deserialize(value);
                kset.add(key);
            }
            return kset;
        } catch (HaloDBException e) {
            throw new KVStorageError(e);
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            halodb.put(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
            return null;
        } catch (HaloDBException e) {
            throw new KVStorageError(e);
        }
    }

    @Override
    public V get(Object key) {
        try {
            byte[] value = halodb.get(SerializationUtils.serialize((Serializable) key));
            return SerializationUtils.deserialize(value);
        } catch (HaloDBException e) {
            throw new KVStorageError(e);
        }
    }
}
