package jext.metrics.providers.sonarqube;

import jext.metrics.ComponentType;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import jext.metrics.MetricsProject;
import jext.util.Assert;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;
import org.sonar.wsclient.metrics.internal.DefaultMetricsClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SonarProject extends SonarObject implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final String name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarProject(String name, SonarProvider provider, SonarClient client) {
        super(null, provider, client);
        Assert.notNull(name, "name");
        this.name = name;
    }

    void initialize() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void close() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ComponentType getType() {
        return ComponentType.PROJECT;
    }

    @Override
    public boolean hasChildren() {
        return true;
    }

    @Override
    public List<MetricsComponent> getChildren() {
        ComponentClient cclient = client.componentClient();
        return cclient.list(getId())
                .stream()
                .map(c -> new SonarObject(c, provider, client))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // getAllMetricValues
    // ----------------------------------------------------------------------

    private static List<String> INVALID_KEYS = Arrays.asList(
            "ncloc_language_distribution",
            "duplications_data",
            "quality_gate_details"
    );

    @Override
    public Collection<MetricValue> getAllMetricValues(ComponentType type, String category) {
        Map<String, SonarMetric> mmap = new HashMap<>();
        Set<String> mkeys = new HashSet<>();
        for(Metric metric : provider.getMetrics(category)) {
            mmap.put(metric.getId(), (SonarMetric)metric);
            mkeys.add(metric.getId());
        }

        // Remove some invalid metrics
        mkeys.removeAll(INVALID_KEYS);

        Map<String, SonarObject> cmap = new HashMap<>();
        MetricsClient metricClient = client.metricsClient();
        Collection<MetricValue> metricValues = metricClient.list(getId(), mkeys, true).stream()
                .filter(measure -> type == SonarObject.getType(((DefaultMetricsClient.CMeasure)measure).getComponent().qualifier()))
                .map(measure -> {
                    SonarObject component;
                    DefaultMetricsClient.CMeasure cmeasure = (DefaultMetricsClient.CMeasure) measure;
                    // check if the component is available
                    if (!cmap.containsKey(cmeasure.getComponent().key())) {
                        Component c = cmeasure.getComponent();
                        component = SonarObject.of(c, provider, client);
                        cmap.put(component.getId(), component);
                    }
                    component = cmap.get(cmeasure.getComponent().key());

                    return SonarMetricValue.of(component, mmap.get(measure.getMetricKey()), measure);
                })
                .collect(Collectors.toList());

        return metricValues;
    }


    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
