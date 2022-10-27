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

    private static final String PATH = "path";

    // keep the map: object path -> object
    private Map<String/*path*/, MetricsObject> pathMap = new HierarchicalMap<>();

    @Override
    public boolean add(MetricsObject object) {
        super.add(object);

        String path = ((SonarObject)object).getPath();
        pathMap.put(path, object);

        return true;
    }

    @Override
    public Optional<MetricsObject> findObject(ObjectType type, String name, Object value) {
        Assert.verify(validateName(name), String.format("%s is not available as property to search objects", name));
        Assert.verify(validateType(type), String.format("%s is not available as type to search objects", type));

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

    private boolean validateName(String name) {
        return name.equals(PATH);
    }

    private boolean validateType(ObjectType type) {
        return type == ObjectType.FILE || type == ObjectType.DIRECTORY;
    }

    private static String pathRest(String path) {
        int p = path.indexOf('/');
        return p != -1 ? path.substring(p+1) : "";
    }

}
