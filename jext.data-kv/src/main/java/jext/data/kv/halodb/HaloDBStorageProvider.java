package jext.data.kv.halodb;

import com.oath.halodb.HaloDB;
import com.oath.halodb.HaloDBException;
import com.oath.halodb.HaloDBOptions;
import jext.data.kv.KVStorage;
import jext.data.kv.KVStorageProvider;
import jext.data.kv.OpenMode;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class HaloDBStorageProvider implements KVStorageProvider {
    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        HaloDBOptions options = new HaloDBOptions();

        try {
            HaloDB halodb = HaloDB.open(storageFile, options);

            return new HaloDBStorage(storageFile, halodb);
        } catch (HaloDBException e) {
            throw new IOException(e);
        }
    }
}
