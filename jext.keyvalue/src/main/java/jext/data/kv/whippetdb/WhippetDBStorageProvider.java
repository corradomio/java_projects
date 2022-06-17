package jext.data.kv.whippetdb;

import io.github.whippetdb.db.api.DbBuilder;
import io.github.whippetdb.db.api.TypeIO;
import jext.data.kv.KVStorage;
import jext.data.kv.OpenMode;
import jext.data.kv.util.AbstractStorageProvider;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class WhippetDBStorageProvider extends AbstractStorageProvider {

    @Override
    public <K, V> KVStorage<K, V> open(OpenMode mode, File storageFile, Class<K> kclass, Class<V> vclass, Properties properties)
        throws IOException {
        storageFile = toStorage(storageFile);

        TypeIO<K> kser = WhippetSerializer.create(kclass);
        TypeIO<V> vser = WhippetSerializer.create(vclass);

        DbBuilder<K, V> builder = new DbBuilder<>(kser, vser);
        // builder.autoflush(true);
        builder.openOrCreate(storageFile.getAbsolutePath());

        return new WhippetDBStorage<>(storageFile, builder);
    }

    @Override
    public String getFileExtension() {
        return ".whippetdb";
    }
}
