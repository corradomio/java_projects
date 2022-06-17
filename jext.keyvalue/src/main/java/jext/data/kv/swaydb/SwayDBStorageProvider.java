package jext.data.kv.swaydb;

import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;
import swaydb.java.persistent.PersistentMap;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static swaydb.java.serializers.Default.intSerializer;


public class SwayDBStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        PersistentMap.Config<K, V, Void> pmap = PersistentMap.functionsOff(storageFile.toPath(),
            SwaySerializer.create(kclass),
            SwaySerializer.create(vclass));


        return new SwayDBStorage<>(storageFile, pmap.get());
    }

    @Override
    public String getFileExtension() {
        return ".swaydb";
    }
}
