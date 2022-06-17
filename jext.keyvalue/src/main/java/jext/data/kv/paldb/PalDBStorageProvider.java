package jext.data.kv.paldb;

import com.linkedin.paldb.api.PalDB;
import com.linkedin.paldb.api.StoreReader;
import com.linkedin.paldb.api.StoreWriter;
import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageException;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;

import java.io.File;
import java.util.Properties;

public class PalDBStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties) {
        storageFile = toStorage(storageFile);

        switch (mode) {
            case READ:
                StoreReader reader = PalDB.createReader(storageFile);
                return new PalDBStorage<>(storageFile, reader);

            case WRITE:
                StoreWriter writer = PalDB.createWriter(storageFile);
                return new PalDBStorage<>(storageFile, writer);

            default:
                throw new KVStorageException(String.format("Unsupported open mode %s", mode));
        }
    }

    @Override
    public String getFileExtension() {
        return ".paldb";
    }
}
