package jext.metrics.providers.scitools;

import jext.metrics.ObjectType;
import jext.util.DefaultHashMap;

import java.util.HashMap;
import java.util.Map;

public class IdMaps extends DefaultHashMap<ObjectType, Map<String, String>> {

    // type -> Neo4J id -> SciTools id

    public IdMaps() {
        super((key)->new HashMap<>());
    }

    public String get(ObjectType type, String id) {
        return get(type).get(id);
    }

}
