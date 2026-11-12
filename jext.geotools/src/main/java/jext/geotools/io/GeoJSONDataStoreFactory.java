package jext.geotools.io;

import org.geotools.api.data.DataStore;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFactorySpi;

import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

/**
 * Opens geographic features stored as GeoJSON, including shapefiles exported to
 * GeoJSON. Supports .json, .geojson and .gjson files, not arbitrary JSON or ESRI JSON.
 *
 * <p>Use {@link #createDataStore(URL)} or pass a {@code url} (URL) or
 * {@code file} (File) parameter to {@link #createDataStore(Map)}.
 * Call {@link DataStore#dispose()} when finished with the returned store.
 * Parsing and datastore operations are provided by GeoTools' GeoJSON reader.
 */
public class GeoJSONDataStoreFactory implements FileDataStoreFactorySpi {

    private final org.geotools.data.geojson.store.GeoJSONDataStoreFactory delegate =
            new org.geotools.data.geojson.store.GeoJSONDataStoreFactory();

    @Override
    public String getDisplayName() {
        return delegate.getDisplayName();
    }

    @Override
    public String getDescription() {
        return delegate.getDescription();
    }

    @Override
    public Param[] getParametersInfo() {
        return delegate.getParametersInfo();
    }

    @Override
    public boolean isAvailable() {
        return delegate.isAvailable();
    }

    @Override
    public Map<RenderingHints.Key, ?> getImplementationHints() {
        return delegate.getImplementationHints();
    }

    @Override
    public String[] getFileExtensions() {
        return new String[] {".json", ".geojson", ".gjson"};
    }

    @Override
    public boolean canProcess(URL url) {
        if (url == null) {
            return false;
        }
        String path = url.getPath().toLowerCase(Locale.ROOT);
        for (String extension : getFileExtensions()) {
            if (path.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canProcess(Map<String, ?> params) {
        if (params == null) {
            return false;
        }
        try {
            File file = (File)
                    org.geotools.data.geojson.store.GeoJSONDataStoreFactory.FILE_PARAM.lookUp(params);
            URL url = (URL)
                    org.geotools.data.geojson.store.GeoJSONDataStoreFactory.URL_PARAM.lookUp(params);
            return canProcess(file == null ? url : file.toURI().toURL());
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public FileDataStore createDataStore(URL url) throws IOException {
        if (!canProcess(url)) {
            return null;
        }
        return delegate.createDataStore(url);
    }

    @Override
    public DataStore createDataStore(Map<String, ?> params) throws IOException {
        if (!canProcess(params)) {
            return null;
        }
        return delegate.createDataStore(params);
    }

    @Override
    public DataStore createNewDataStore(Map<String, ?> params) throws IOException {
        return delegate.createNewDataStore(params);
    }

    @Override
    public String getTypeName(URL url) throws IOException {
        FileDataStore store = createDataStore(url);
        if (store == null) {
            return null;
        }
        try {
            return store.getFeatureSource().getSchema().getTypeName();
        } finally {
            store.dispose();
        }
    }
}
