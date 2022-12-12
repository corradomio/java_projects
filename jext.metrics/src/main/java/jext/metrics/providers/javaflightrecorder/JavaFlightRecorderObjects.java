package jext.metrics.providers.javaflightrecorder;

import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.ObjectType;
import jext.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static jext.metrics.ObjectType.ALL;

public class JavaFlightRecorderObjects extends ArrayList<MetricsObject> implements MetricsObjects {
    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String ID = "id";
   // public static final String PATH = "path";

    private final ObjectType type;

    // keep the map: object path -> object
    //private final Map<String/*path*/, MetricsObject> pathMap = new HierarchicalMap<>();
    private final Map<String/*id*/, MetricsObject> idMap = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    JavaFlightRecorderObjects(ObjectType type) {
        this.type = type;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean add(MetricsObject object) {
        if (type == ALL || type == object.getType()) {
            super.add(object);
            idMap.put(object.getId(), object);
            return true;
        }
        return false;
    }

    @Override
    public Optional<MetricsObject> findObject(String name, Object value) {
        Assert.verify(validateName(name), String.format("%s is not available as property to search objects", name));

        if (ID.equals(name))
            return findById((String)value);
        else
            return Optional.empty();
    }

    private Optional<MetricsObject> findById(String id) {
//        // [type, Neo4J id] -> SciTools id
//        String scitoolsId = idmaps.get(type, id);
//        if (scitoolsId == null)
//            return Optional.empty();

        MetricsObject mo = idMap.get(id);
        return Optional.ofNullable(mo);
    }


    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private boolean validateName(String name) {
        return name.equals(ID);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
