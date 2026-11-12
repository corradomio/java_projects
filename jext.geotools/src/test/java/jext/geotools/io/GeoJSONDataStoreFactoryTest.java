package jext.geotools.io;

import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFinder;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFactorySpi;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.locationtech.jts.geom.Point;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.ServiceLoader;

import static org.junit.Assert.*;

public class GeoJSONDataStoreFactoryTest {
    @Rule
    public TemporaryFolder temporary = new TemporaryFolder();

    private File fixture(String name) throws Exception {
        File file = temporary.newFile(name);
        Files.writeString(file.toPath(), """
                {"type":"FeatureCollection","features":[
                  {"type":"Feature","geometry":{"type":"Point","coordinates":[12.5,45.5]},
                   "properties":{"name":"Café","population":42}}
                ]}
                """);
        return file;
    }

    private void assertFeatures(DataStore store) throws Exception {
        assertNotNull(store);
        try {
            assertEquals(1, store.getTypeNames().length);
            try (SimpleFeatureIterator features =
                    store.getFeatureSource(store.getTypeNames()[0]).getFeatures().features()) {
                assertTrue(features.hasNext());
                var feature = features.next();
                assertEquals("Café", feature.getAttribute("name"));
                assertEquals(42, ((Number) feature.getAttribute("population")).intValue());
                Point point = (Point) feature.getDefaultGeometry();
                assertEquals(12.5, point.getX(), 0.0);
                assertEquals(45.5, point.getY(), 0.0);
                assertFalse(features.hasNext());
            }
        } finally {
            store.dispose();
        }
    }

    @Test
    public void readsUrlAndMapParameters() throws Exception {
        GeoJSONDataStoreFactory factory = new GeoJSONDataStoreFactory();
        File file = fixture("places with spaces.JSON");
        URL url = file.toURI().toURL();
        assertTrue(factory.canProcess(url));
        assertFeatures(factory.createDataStore(url));
        assertFeatures(factory.createDataStore(Map.of("file", file)));
        assertFeatures(factory.createDataStore(Map.of("url", url)));
        FileDataStore store = factory.createDataStore(url);
        try {
            assertEquals(store.getFeatureSource().getSchema().getTypeName(), factory.getTypeName(url));
        } finally {
            store.dispose();
        }
    }

    @Test
    public void discoversFactoryAndReadsGeoJson() throws Exception {
        assertTrue(ServiceLoader.load(FileDataStoreFactorySpi.class).stream()
                .anyMatch(provider -> provider.type() == GeoJSONDataStoreFactory.class));
        File file = fixture("places.geojson");
        assertFeatures(FileDataStoreFinder.getDataStore(file));
        assertFeatures(DataStoreFinder.getDataStore(Map.of("url", file.toURI().toURL())));
    }

    @Test
    public void checksExtensionsWithoutQueryOrFragment() throws Exception {
        GeoJSONDataStoreFactory factory = new GeoJSONDataStoreFactory();
        assertTrue(factory.canProcess(new URL("https://example.org/places.GEOJSON?download=1#data")));
        assertFalse(factory.canProcess(new URL("file:/places.shp")));
        assertFalse(factory.canProcess(Map.of("url", new URL("file:/notjson"))));
        assertFalse(factory.canProcess((URL) null));
        assertFalse(factory.canProcess(Map.of()));
        assertNull(factory.createDataStore(new URL("file:/places.shp")));
    }
}
