package jext.metrics.providers.scitools;

import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.ObjectType;
import jext.util.Assert;
import jext.util.HierarchicalMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static jext.metrics.ObjectType.ALL;

public class SciToolsObjects extends ArrayList<MetricsObject> implements MetricsObjects {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String ID = "id";
    public static final String PATH = "path";

    private final ObjectType type;
    private final IdMaps idmaps;

    // keep the map: object path -> object
    private final Map<String/*path*/, MetricsObject> pathMap = new HierarchicalMap<>();
    private final Map<String/*id*/, MetricsObject> idMap = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SciToolsObjects(ObjectType type, IdMaps idmaps) {
        this.type = type;
        this.idmaps = idmaps;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean add(MetricsObject object) {
        if (type == ALL || type == object.getType()) {
            super.add(object);

            idMap.put(object.getId(), object);
            if (object.getType() == ObjectType.SOURCE) {
                String path = ((SciToolsObject)object).getPath();
                pathMap.put(path, object);
            }
            return true;
        }
        return false;
    }

    @Override
    public Optional<MetricsObject> findObject(String name, Object value) {
        Assert.verify(validateName(name), String.format("%s is not available as property to search objects", name));

        if (ID.equals(name))
            return findById((String)value);
        if (PATH.equals(name))
            return findByPath((String)value);
        else
            return Optional.empty();
    }

    private Optional<MetricsObject> findById(String id) {
        // [type, Neo4J id] -> SciTools id
        String scitoolsId = idmaps.get(type, id);
        if (scitoolsId == null)
            return Optional.empty();

        MetricsObject mo = idMap.get(scitoolsId);
        return Optional.ofNullable(mo);
    }

    private  Optional<MetricsObject> findByPath(String path) {
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
        return name.equals(ID) || name.equals(PATH);
    }

    private static String pathRest(String path) {
        int p = path.indexOf('/');
        return p != -1 ? path.substring(p+1) : "";
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
