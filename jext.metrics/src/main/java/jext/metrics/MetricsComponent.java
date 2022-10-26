package jext.metrics;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MetricsComponent {

    MetricsProject getProject();

    /**
     * Component id
     * The name depend on the tool
     *
     * @return component id
     */
    String getId();

    /**
     * Component name
     * The name depend on the tool and component's type
     *
     * @return component name
     */
    String getName();

    /**
     * Component type.
     * Different tools are able to process different component types
     *
     * @return component type
     */
    ComponentType getType();

    /**
     * Component data.
     * It contains, at minimum, id, name and type.
     * However different tools can offer extra data
     *
     * @return
     */
    Map<String, Object> getData();

    /**
     * Check if the component has children components or it is a leaf.
     * Note: the test is based on the component type NOT on the existence of
     * children.
     * For example: for a component of DIRECTORY type it returns true also
     * if the directory doesn't contain other sub-directories or files
     *
     * @return true if it can have children
     */
    boolean hasChildren();

    /**
     * List of component children. The list can be empty
     *
     * @return list of component's children
     */
    List<MetricsComponent> getChildren();

    /**
     * List of all metric values attached to this component
     *
     * @return list of metric values
     */
    Collection<MetricValue> getMetricValues();

    /**
     * List of metrics of the specified category attached to the component
     *
     * @param category metrics' category
     * @return list of metric values
     */
    Collection<MetricValue> getMetricValues(String category);

}
