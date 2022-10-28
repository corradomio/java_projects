package jext.metrics.providers.sonarqube;

import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.ObjectType;
import jext.util.Assert;
import jext.util.HierarchicalMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class SonarObjects extends ArrayList<MetricsObject> implements MetricsObjects {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String PATH = "path";

    private ObjectType type;

    // keep the map: object path -> object
    private Map<String/*path*/, MetricsObject> pathMap = new HierarchicalMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarObjects(ObjectType type) {
        this.type = type;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean add(MetricsObject object) {
        super.add(object);

        String path = ((SonarObject)object).getPath();
        pathMap.put(path, object);

        return true;
    }

    @Override
    public Optional<MetricsObject> findObject(String name, Object value) {
        Assert.verify(validateName(name), String.format("%s is not available as property to search objects", name));

        // Problem:
        // spl:  src/Lucene.Net.Facet/Taxonomy/Directory/Consts.cs
        // sq:   Lucene.Net.Facet/Taxonomy/Directory/Consts.cs
        // Solution: we try some path prefixes

        String path = (String) value;

        while (!path.isEmpty()) {
            MetricsObject mo = pathMap.get(path);
            if (mo != null)
                return Optional.of(mo);

            path = pathRest(path);
        }

        return Optional.empty();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private boolean validateName(String name) {
        return name.equals(PATH);
    }

    private static String pathRest(String path) {
        int p = path.indexOf('/');
        return p != -1 ? path.substring(p+1) : "";
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
