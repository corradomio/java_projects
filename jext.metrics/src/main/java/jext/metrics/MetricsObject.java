package jext.metrics;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface MetricsObject {

    MetricsProject getProject();

    /**
     * Object id
     * The name depend on the tool
     *
     * @return object id
     */
    String getId();

    /**
     * Object name
     * The name depend on the tool and object's type
     *
     * @return object name
     */
    String getName();
    String getLongname();

    /**
     * Object type.
     * Different tools are able to process different object types
     *
     * @return object type
     */
    ObjectType getType();

    /**
     * Object data.
     * It contains, at minimum, id, name and type.
     * Different tools offer extra data
     *
     * @return map with all data offered by the tool
     */
    Map<String, Object> getData();

    /**
     * Retrieve the specified object
     * @param type objectType
     * @param path relative path respect the current object
     * @return the object, if found
     */
    @Nullable
    MetricsObject getMetricsObject(ObjectType type, String path);

    /**
     * Used to create 'virtual' project objects, not existent in the provider,
     * but useful to implement the 'aggregate values'.
     *
     * @param type object type
     * @param path relative path respect the current object
     * @return the 'virtual object'
     */
    MetricsObject newMetricsObject(ObjectType type, String path);

    /**
     * Check if the object has children or it is a leaf.
     * Note: the test is based on the object type NOT on the existence of
     * children.
     * For example: for an object of DIRECTORY type it returns true also
     * if the directory is empty
     *
     * @return true if it can have children
     */
    boolean hasChildren();

    /**
     * List of children. The list can be empty
     *
     * @return list of object's children
     */
    List<MetricsObject> getChildren();

    /**
     * List of all metric values attached to this object
     *
     * @return list of metric values
     */
    Collection<MetricValue> getMetricValues();

    /**
     * List of metrics of the specified category attached to this object
     *
     * @param category metrics' category
     * @return list of metric values
     */
    Collection<MetricValue> getMetricValues(String category);

    // based on callback

    void getMetricValues(Consumer<MetricValue> callback);
    void getMetricValues(String category, Consumer<MetricValue> callback);

}
