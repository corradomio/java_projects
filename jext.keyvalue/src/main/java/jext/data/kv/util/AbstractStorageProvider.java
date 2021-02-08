package jext.data.kv.util;

import jext.data.kv.KVStorageProvider;

import java.io.File;

public abstract class AbstractStorageProvider implements KVStorageProvider {

    protected File toStorage(File storageFile) {
        if (storageFile.exists())
            return storageFile;

        return new File(storageFile.getParentFile(),
            storageFile.getName() + getFileExtension());
    }

    protected abstract String getFileExtension();

    protected static final String nameOf(File storageFile) {
        String name = storageFile.getName();
        int dot = name.indexOf('.');
        return dot != -1 ? name.substring(0, dot) : name;
    }

}
