package jext.metrics;

import java.util.List;
import java.util.Optional;

public interface MetricsObjects extends List<MetricsObject> {

    /**
     * Find an object with the specified value in the property with the specified name
     * Note: the properties that is is possible to use depends on the tool
     *
     * @param name property name
     * @param value property value
     * @return the object if it is present
     */
    Optional<MetricsObject> findObject(String name, Object value);

}
