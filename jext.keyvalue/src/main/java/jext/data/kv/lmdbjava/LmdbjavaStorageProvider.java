package jext.data.kv.lmdbjava;

import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;
import org.lmdbjava.Dbi;
import org.lmdbjava.Env;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

import static org.lmdbjava.DbiFlags.MDB_CREATE;
import static org.lmdbjava.Env.create;


public class LmdbjavaStorageProvider  extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        if (!storageFile.exists() && !storageFile.mkdirs())
            throw new IOException("Unable to create directory " + storageFile);

        Env<ByteBuffer> env = create()
            // LMDB also needs to know how large our DB might be. Over-estimating is OK.
            .setMapSize(10_485_760)
            // LMDB also needs to know how many DBs (Dbi) we want to store in this Env.
            .setMaxDbs(1)
            // Now let's open the Env. The same path can be concurrently opened and
            // used in different processes, but do not open the same path twice in
            // the same process at the same time.
            .open(storageFile);

        Dbi<ByteBuffer> db = env.openDbi(nameOf(storageFile), MDB_CREATE);

        return new LmdbjavaStorage(storageFile, env, db);
    }


    @Override
    protected String getFileExtension() {
        return ".lmdbj";
    }
}
