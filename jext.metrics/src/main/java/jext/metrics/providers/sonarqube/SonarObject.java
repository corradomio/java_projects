package jext.metrics.providers.sonarqube;

import jext.metrics.ComponentType;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SonarObject implements MetricsComponent {

    private static final String QUAL_FIL = "FIL";
    private static final String QUAL_DIR = "DIR";
    private static final String QUAL_TRK = "TRK";

    public static ComponentType toType(String qualifier) {
        if (qualifier.equals(QUAL_FIL))
            return ComponentType.FILE;
        if (qualifier.equals(QUAL_TRK))
            return ComponentType.PROJECT;
        if (qualifier.equals(QUAL_DIR))
            return ComponentType.DIRECTORY;
        else
            return ComponentType.UNKNOWN;
    }

    public static String fromType(ComponentType type) {
        if(type == ComponentType.FILE)
            return QUAL_FIL;
        if (type == ComponentType.DIRECTORY)
            return QUAL_DIR;
        if (type == ComponentType.PROJECT)
            return QUAL_TRK;
        else
            throw new RuntimeException(String.format("Unsupported type %s", type));
    }

    public static SonarObject of(Component c, SonarProvider provider, SonarClient client) {
        return new SonarObject(c, provider, client);
    }

    public static ComponentType getType(String qualifier) {
        return toType(qualifier);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected final SonarProvider provider;
    protected final SonarClient client;
    protected final Component component;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarObject(Component c, SonarProvider provider, SonarClient client) {
        this.component = c;
        this.provider = provider;
        this.client = client;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return component.key();
    }

    @Override
    public String getName() {
        return component.name();
    }

    @Override
    public ComponentType getType() {
        /*
            Qualifiers
                TRK     project
                FIL
                DIR
                UTS     ???
                BRC     branch ???
         */
        return getType(component.qualifier());
    }

    @Override
    public boolean hasChildren() {
        return "DIR".equals(component.qualifier());
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
    // Metrics
    // ----------------------------------------------------------------------

    @Override
    public Collection<MetricValue> getMetricValues() {
        List<MetricValue> allMetricValues = new ArrayList<>();
        // collect all categories
        Collection<String> categories = provider.getCategories();
        for(String category : categories) {
            Collection<MetricValue> metricValues = getMetricValues(category);
            allMetricValues.addAll(metricValues);
        }
        return allMetricValues;
    }

    @Override
    public Collection<MetricValue> getMetricValues(String category) {
        // collect the metrics in the specified category
        Map<String, SonarMetric> mmap = new HashMap<>();
        List<String> mkeys = new ArrayList<>();
        for(Metric metric : provider.getMetrics(category)) {
            mmap.put(metric.getId(), (SonarMetric)metric);
            mkeys.add(metric.getId());
        }
        MetricsClient metricsClient = client.metricsClient();
        return metricsClient.list(getId(), mkeys, false).stream()
                .map(measure -> SonarMetricValue.of(this,mmap.get(measure.getMetricKey()), measure))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
