package jext.metrics.providers.scitools;

import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.ObjectType;
import jext.util.Assert;

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

    private final ObjectType type;
    private final IdMaps idmaps;
    private final Map<String, MetricsObject> map = new HashMap<>();

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
            map.put(object.getId(), object);
            return true;
        }
        return false;
    }

    @Override
    public Optional<MetricsObject> findObject(String name, Object value) {
        Assert.verify(validateName(name), String.format("%s is not available as property to search objects", name));

        // [type, Neo4J id] -> SciTools id
        String scitoolsId = idmaps.get(type, (String)value);
        if (scitoolsId == null)
            return Optional.empty();

        MetricsObject mo = map.get(scitoolsId);
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
