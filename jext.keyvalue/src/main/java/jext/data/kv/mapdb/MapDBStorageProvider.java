package jext.data.kv.mapdb;

import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageProvider;
import jext.data.kv.OpenMode;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

public class MapDBStorageProvider implements KVStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties) {
        DBMaker.Maker maker =  DBMaker.fileDB(storageFile).fileMmapEnableIfSupported();
        if (mode == OpenMode.READ)
            maker.readOnly();
        DB db = maker.make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        return new MapDBStorage<>(storageFile, db, map);
    }

}
