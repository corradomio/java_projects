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
import org.sonar.wsclient.base.HttpException;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;
import org.sonar.wsclient.metrics.internal.DefaultMetricsClient;

import java.util.Collections;
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

    private static final Set<ObjectType> SUPPORTED_TYPES = new HashSet<>(){{
        add(ObjectType.SOURCE);
        add(ObjectType.MODULE);
        add(ObjectType.PROJECT);
    }};

    private final String name;
    private boolean valid;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarProject(String name, SonarProvider provider, SonarClient client) {
        super(null, provider, client);
        Assert.notNull(name, "name");
        this.name = name;
        this.valid = true;
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
        if (!valid)
            return Collections.emptyList();

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
    public Set<ObjectType> getObjectTypes() {
        return SUPPORTED_TYPES;
    }

    @Override
    public MetricsObjects getMetricsObjects(ObjectType type) {
        MetricsObjects metricsObjects = new SonarObjects(type);
        if (!valid)
            return metricsObjects;


        if (!validateType(type)) {
            Assert.check(false, String.format("%s is not available as type to search objects", type));
            return metricsObjects;
        }

        ComponentClient cclient = client.componentClient();
        cclient.list(getId(), true)
                .forEach(c -> {
                    SonarObject so = new SonarObject(c, this);
                    metricsObjects.add(so);
                });
        return metricsObjects;
    }

    private static boolean validateType(ObjectType type) {
        return SUPPORTED_TYPES.contains(type);
    }

    // ----------------------------------------------------------------------
    // Metrics/MetricsValues
    // ----------------------------------------------------------------------

    @Override
    public Set<Metric> getMetrics() {
        if (!valid)
            return Collections.emptySet();

        Set<Metric> metrics = new HashSet<>();

        getMetricsValues(ObjectType.ALL, null).forEach(mv -> {
            metrics.add(mv.getMetric());
        });

        return metrics;
    }

    @Override
    public Set<Metric> getMetrics(ObjectType otype) {
        if (!valid)
            return Collections.emptySet();

        Set<Metric> metrics = new HashSet<>();

        try {
            getMetricsValues(otype, null).forEach(mv -> {
                metrics.add(mv.getMetric());
            });
        }
        catch (RuntimeException e) {
            logger.errorf("Project %s not found", project.getName());
            valid = false;
        }

        return metrics;
    }

    @Override
    public MetricsValues getMetricsValues(ObjectType type, String category) {
        MetricsValues metricsValues = new SonarValues();
        if (!valid)
            return metricsValues;

        Map<String, SonarMetric> mmap = new HashMap<>();
        Set<String> mkeys = new HashSet<>();
        for(Metric metric : provider.getCategory(category).getMetrics()) {
            mmap.put(metric.getId(), (SonarMetric)metric);
            mkeys.add(metric.getId());
        }

        if (mkeys.isEmpty())
            return metricsValues;

        // Remove some invalid metrics
        INVALID_METRIC_KEYS.forEach(mkeys::remove);

        // Initialize the component map with the current prokect component
        Map<String, SonarObject> cmap = new HashMap<>();

        MetricsClient metricClient = client.metricsClient();
        String qualifier = SonarObject.toQualifier(type);

        if (type == ObjectType.PROJECT) {
            metricClient.list(getId(), qualifier, mkeys, false)
                    .stream()
                    // select ONLY the specified object types
                    .filter(measure -> type == ObjectType.ALL || type == SonarObject.toType(((DefaultMetricsClient.CMeasure) measure).getComponent().qualifier()))
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
        }
        else {
            metricClient.list(getId(), qualifier, mkeys, true)
                    .stream()
                    // select ONLY the specified object types
                    .filter(measure -> type == ObjectType.ALL || type == SonarObject.toType(((DefaultMetricsClient.CMeasure) measure).getComponent().qualifier()))
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
        }

        return metricsValues;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
