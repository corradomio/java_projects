package jext.metrics.providers.scitools;

import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.ObjectType;

import java.util.ArrayList;
import java.util.Optional;

public class SciToolsObjects extends ArrayList<MetricsObject> implements MetricsObjects {

    @Override
    public Optional<MetricsObject> findObject(ObjectType type, String name, Object value) {
        return Optional.empty();
    }
}
