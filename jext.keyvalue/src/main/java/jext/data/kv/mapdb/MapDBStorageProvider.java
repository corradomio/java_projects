package jext.data.kv.mapdb;

import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

public class MapDBStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties) {
        storageFile = toStorage(storageFile);

        DBMaker.Maker maker =  DBMaker.fileDB(storageFile).fileMmapEnableIfSupported();
        if (mode == OpenMode.READ)
            maker.readOnly();
        DB db = maker.make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        return new MapDBStorage<>(storageFile, db, map);
    }

    @Override
    protected String getFileExtension() {
        return ".mapdb";
    }
}
