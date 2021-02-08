package jext.data.kv.chronicle;

import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ChronicleMapStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        ChronicleMap<K, V> cmap = ChronicleMapBuilder.of(kclass, vclass)
            .name(nameOf(storageFile))
            .averageKeySize(128)
            .averageValueSize(512)
            .entries(1000000)
            .createPersistedTo(storageFile);
        return new ChronicleMapStorage(storageFile, cmap);
    }

    @Override
    protected String getFileExtension() {
        return ".cmap";
    }
}
