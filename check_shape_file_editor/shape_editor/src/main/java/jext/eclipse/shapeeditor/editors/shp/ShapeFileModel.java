package jext.eclipse.shapeeditor.editors.shp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.api.data.SimpleFeatureSource;
import org.locationtech.jts.geom.Geometry;
import org.geotools.api.feature.simple.SimpleFeature;

public class ShapeFileModel {

    private final List<Geometry> geometries = new ArrayList<>();
    private String name = "";

    public void load(File file) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("url", file.toURI().toURL());

        DataStore store = DataStoreFinder.getDataStore(params);
        if (store == null) {
            throw new IllegalStateException("No GeoTools data store available for " + file.getName());
        }

        try {
            String[] typeNames = store.getTypeNames();
            if (typeNames.length == 0) {
                throw new IllegalStateException("Data source contains no feature types: " + file.getName());
            }
            SimpleFeatureSource source = store.getFeatureSource(typeNames[0]);

            geometries.clear();
            try (SimpleFeatureIterator iterator = source.getFeatures().features()) {
                while (iterator.hasNext()) {
                    SimpleFeature feature = iterator.next();
                    Object defaultGeometry = feature.getDefaultGeometry();
                    if (defaultGeometry instanceof Geometry) {
                        geometries.add((Geometry) defaultGeometry);
                    }
                }
            }

            name = file.getName();
        } finally {
            store.dispose();
        }
    }

    public List<Geometry> getGeometries() {
        return new ArrayList<>(geometries);
    }

    public String getName() {
        return name;
    }
}
