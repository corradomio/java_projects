package jext.metrics.providers.sonarqube;

import jext.logging.Logger;
import jext.metrics.Metric;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsObjects;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsValues;
import jext.metrics.ObjectType;
import jext.util.Assert;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;
import org.sonar.wsclient.metrics.internal.DefaultMetricsClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static jext.metrics.providers.sonarqube.SonarProvider.INVALID_METRIC_KEYS;

public class SonarProject extends SonarObject implements MetricsProject {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(SonarProject.class);

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
        logger.info("initialize");
        logger.info("done");
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
    public MetricsProvider getProvider() {
        return provider;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ObjectType getType() {
        return ObjectType.PROJECT;
    }

    @Override
    public boolean hasChildren() {
        return true;
    }

    @Override
    public List<MetricsObject> getChildren() {
        ComponentClient cclient = client.componentClient();
        return cclient.list(getId(), false)
                .stream()
                .map(c -> new SonarObject(c, this))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // MetricsObjects
    // ----------------------------------------------------------------------

    @Override
    public MetricsObjects getMetricsObjects(ObjectType type) {
        Assert.verify(validateType(type), String.format("%s is not available as type to search objects", type));

        MetricsObjects metricsObjects = new SonarObjects(type);
        ComponentClient cclient = client.componentClient();
        cclient.list(getId(), true)
                .forEach(c -> {
                    SonarObject so = new SonarObject(c, this);
                    metricsObjects.add(so);
                });
        return metricsObjects;
    }

    private static final Set<ObjectType> SUPPORTED_TYPES = new HashSet<>(){{
        add(ObjectType.FILE);
        add(ObjectType.DIRECTORY);
    }};

    private static boolean validateType(ObjectType type) {
        return SUPPORTED_TYPES.contains(type);
    }

    // ----------------------------------------------------------------------
    // Metrics/MetricsValues
    // ----------------------------------------------------------------------

    @Override
    public Set<Metric> getMetrics() {
        Set<Metric> metrics = new HashSet<>();

        getMetricsValues(ObjectType.ALL, null).forEach(mv -> {
            metrics.add(mv.getMetric());
        });

        return metrics;
    }

    @Override
    public MetricsValues getMetricsValues(ObjectType type, String category) {
        Map<String, SonarMetric> mmap = new HashMap<>();
        Set<String> mkeys = new HashSet<>();
        for(Metric metric : provider.getMetrics(category)) {
            mmap.put(metric.getId(), (SonarMetric)metric);
            mkeys.add(metric.getId());
        }

        // Remove some invalid metrics
        mkeys.removeAll(INVALID_METRIC_KEYS);

        // Initialize the component map with the current prokect component
        Map<String, SonarObject> cmap = new HashMap<>();

        MetricsValues metricsValues = new SonarValues();
        MetricsClient metricClient = client.metricsClient();
        metricClient.list(getId(), mkeys, true)
                .stream()
                // select ONLY the specified object types
                .filter(measure -> type == ObjectType.ALL || type == SonarObject.toType(((DefaultMetricsClient.CMeasure)measure).getComponent().qualifier()))
                .forEach(measure -> {
                    SonarObject so;
                    DefaultMetricsClient.CMeasure cmeasure = (DefaultMetricsClient.CMeasure) measure;
                    // check if the component is available
                    if (!cmap.containsKey(cmeasure.getComponent().key())) {
                        Component c = cmeasure.getComponent();
                        so = new SonarObject(c, this);
                        cmap.put(so.getId(), so);
                    }
                    so = cmap.get(cmeasure.getComponent().key());

                    SonarMetricValue metricValue = SonarMetricValue.of(so, mmap.get(measure.getMetricKey()), measure);

                    metricsValues.add(metricValue);
                });

        return metricsValues;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
