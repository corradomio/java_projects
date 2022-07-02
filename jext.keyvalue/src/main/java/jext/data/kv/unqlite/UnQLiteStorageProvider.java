package jext.data.kv.unqlite;

import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static jext.data.kv.unqlite.UnQLiteDB.OpenMode.UNQLITE_OPEN_CREATE;
import static jext.data.kv.unqlite.UnQLiteDB.OpenMode.UNQLITE_OPEN_READONLY;
import static jext.data.kv.unqlite.UnQLiteDB.OpenMode.UNQLITE_OPEN_READWRITE;

public class UnQLiteStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        int omode;

        switch (mode) {
            case READ:
                omode = UNQLITE_OPEN_READONLY;
                break;
            case WRITE:
                omode = UNQLITE_OPEN_CREATE;
                break;
            default:
                omode = UNQLITE_OPEN_READWRITE;
                break;
        }

        UnQLiteDB uqdb = UnQLiteDB.open(storageFile, omode);
        return new UnQLiteStorage<>(storageFile, uqdb);
    }

    @Override
    public String getFileExtension() {
        return ".unqlite";
    }
}
