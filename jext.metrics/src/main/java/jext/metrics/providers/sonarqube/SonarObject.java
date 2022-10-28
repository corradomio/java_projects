package jext.metrics.providers.sonarqube;

import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.MetricsValues;
import jext.metrics.ObjectType;
import jext.util.HashSet;
import jext.util.MapUtils;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SonarObject implements MetricsObject {

    // -----------------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------------

    private static final String QUAL_FIL = "FIL";
    private static final String QUAL_DIR = "DIR";
    private static final String QUAL_TRK = "TRK";

    public static ObjectType toType(String qualifier) {
        if (qualifier.equals(QUAL_FIL))
            return ObjectType.FILE;
        if (qualifier.equals(QUAL_TRK))
            return ObjectType.PROJECT;
        if (qualifier.equals(QUAL_DIR))
            return ObjectType.DIRECTORY;
        else
            return ObjectType.UNKNOWN;
    }

    // -----------------------------------------------------------------------
    // Conversions
    // -----------------------------------------------------------------------

    // public static String fromType(ObjectType type) {
    //     if(type == ObjectType.FILE)
    //         return QUAL_FIL;
    //     if (type == ObjectType.DIRECTORY)
    //         return QUAL_DIR;
    //     if (type == ObjectType.PROJECT)
    //         return QUAL_TRK;
    //     else
    //         throw new RuntimeException(String.format("Unsupported type %s", type));
    // }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected final SonarProvider provider;
    protected final SonarProject project;
    protected final SonarClient client;
    protected final Component component;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarObject(Component c, SonarProvider provider, SonarClient client) {
        this.component = c;
        this.provider = provider;
        this.client = client;
        this.project = (SonarProject) this;
    }

    SonarObject(Component c, SonarObject parent) {
        this.component = c;
        this.provider = parent.provider;
        this.client = parent.client;
        this.project = parent.project;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MetricsProject getProject() {
        return project;
    }

    @Override
    public String getId() {
        return component.key();
    }

    @Override
    public String getName() {
        return component.name();
    }

    @Override
    public ObjectType getType() {
        /*
            Qualifiers
                TRK     project
                FIL
                DIR
                UTS     ???
                BRC     branch ???
         */
        return toType(component.qualifier());
    }

    @Override
    public Map<String, Object> getData() {
        return MapUtils.asMap(
                "id", getId(),
                "name", getName(),
                "type", getType().toString(),
                "qualifier", component.qualifier(),
                "path", component.path()
        );
    }

    @Override
    public boolean hasChildren() {
        return "DIR".equals(component.qualifier());
    }

    @Override
    public List<MetricsObject> getChildren() {
        ComponentClient cclient = client.componentClient();
        return cclient.list(getId(), false)
                .stream()
                .map(c -> new SonarObject(c, provider, client))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Metrics
    // ----------------------------------------------------------------------

    @Override
    public Collection<MetricValue> getMetricValues() {
        return getMetricValues(MetricsProvider.ALL_METRICS);
    }

    @Override
    public Collection<MetricValue> getMetricValues(String category) {
        ArrayList<MetricValue> mvalues = new ArrayList<>();
        getMetricValues(category, mvalues::add);
        return mvalues;
    }

    // ----------------------------------------------------------------------
    // Metrics
    // ----------------------------------------------------------------------

    @Override
    public void getMetricValues(Consumer<MetricValue> callback) {
        getMetricValues(MetricsProvider.ALL_METRICS, callback);
    }

    @Override
    public void getMetricValues(String category, Consumer<MetricValue> callback) {
        Map<String, SonarMetric> mmap = new HashMap<>();
        List<String> mkeys = new ArrayList<>();
        for(Metric metric : provider.getMetrics(category)) {
            mmap.put(metric.getId(), (SonarMetric)metric);
            mkeys.add(metric.getId());
        }

        MetricsClient metricsClient = client.metricsClient();
        metricsClient.list(getId(), mkeys, false, measure -> {
            MetricValue mvalue = SonarMetricValue.of(this,mmap.get(measure.getMetricKey()), measure);

            callback.accept(mvalue);
        });
    }

    // ----------------------------------------------------------------------
    // Custom
    // ----------------------------------------------------------------------

    public String getPath() {
        return component.path();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
