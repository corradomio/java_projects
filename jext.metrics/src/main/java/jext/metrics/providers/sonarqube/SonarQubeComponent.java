package jext.metrics.providers.sonarqube;

import jext.metrics.ComponentType;
import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SonarQubeComponent implements MetricsComponent {

    public static SonarQubeComponent of(Component c, SonarQubeProvider provider, SonarClient client) {
        return new SonarQubeComponent(c, provider, client);
    }

    public static ComponentType getType(String qualifier) {
        if (qualifier.equals("FIL"))
            return ComponentType.FILE;
        if (qualifier.equals("TRK"))
            return ComponentType.PROJECT;
        if (qualifier.equals("DIR"))
            return ComponentType.DIRECTORY;
        else
            return ComponentType.UNKNOWN;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected final SonarQubeProvider provider;
    protected final SonarClient client;
    protected final Component component;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarQubeComponent(Component c, SonarQubeProvider provider, SonarClient client) {
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
                .map(c -> new SonarQubeComponent(c, provider, client))
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
        Map<String, SonarQubeMetric> mmap = new HashMap<>();
        List<String> mkeys = new ArrayList<>();
        for(Metric metric : provider.getMetrics(category)) {
            mmap.put(metric.getId(), (SonarQubeMetric)metric);
            mkeys.add(metric.getId());
        }
        MetricsClient metricsClient = client.metricsClient();
        return metricsClient.list(getId(), mkeys, false).stream()
                .map(measure -> SonarQubeMetricValue.of(this,mmap.get(measure.getMetricKey()), measure))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
