package jext.metrics.providers.sonarqube;

import jext.metrics.Metric;
import jext.metrics.MetricValue;
import jext.metrics.MetricsObject;
import jext.metrics.MetricsProject;
import jext.metrics.MetricsProvider;
import jext.metrics.ObjectType;
import jext.util.MapUtils;
import jext.util.PathUtils;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.metrics.MetricsClient;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SonarObject implements MetricsObject {

    public static SonarObject of(Component c, SonarProvider provider, SonarClient client) {
        return new SonarObject(c, provider, client);
    }

    public static SonarObject of(Component c, SonarObject parent) {
        return new SonarObject(c, parent);
    }

    // -----------------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------------

    private static final String QUAL_FIL = "FIL";
    private static final String QUAL_DIR = "DIR";
    private static final String QUAL_TRK = "TRK";
    private static final String QUAL_UNK = "UNK";

    public static ObjectType toType(String qualifier) {
        if (qualifier.equals(QUAL_FIL))
            return ObjectType.SOURCE;
        if (qualifier.equals(QUAL_TRK))
            return ObjectType.PROJECT;
        if (qualifier.equals(QUAL_DIR))
            return ObjectType.MODULE;
        else
            return ObjectType.UNKNOWN;
    }

    public static String toQualifier(ObjectType type) {
        // special case used in aggregate values
        if (type == ObjectType.VIRTUAL)
            return QUAL_UNK;

        if (type == ObjectType.PROJECT)
            return QUAL_TRK;
        if (type == ObjectType.SOURCE)
            return QUAL_FIL;
        if (type == ObjectType.MODULE)
            return QUAL_DIR;
        else
            throw new RuntimeException(String.format("Unsupported type %s", type));
    }

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

    protected SonarObject(Component c, SonarProvider provider, SonarClient client) {
        this.component = c;
        this.provider = provider;
        this.client = client;
        this.project = (SonarProject) this;
    }

    protected SonarObject(Component c, SonarObject parent) {
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
    public String getLongname() {
        return component.longName();
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
        try {
            return cclient.list(getId(), false)
                    .stream()
                    .map(c -> new SonarObject(c, provider, client))
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            project.getLogger().error(e, e);
            return Collections.emptyList();
        }
    }

    @Nullable
    @Override
    public MetricsObject getMetricsObject(ObjectType type, String path) {

        String id = String.format("%s:%s", getName(), path);
        Component c = client.componentClient().get(id);

        if (c == null)
            return null;
        else
            return SonarObject.of(c, this);
    }

    @Override
    public MetricsObject newMetricsObject(ObjectType type, String path) {
        // "key": "Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisStempelPatchStemsCommandTest.cs",
        // "name": "AnalysisStempelPatchStemsCommandTest.cs",
        // "qualifier": "UTS",
        // "path": "dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisStempelPatchStemsCommandTest.cs",
        // "language": "cs"
        Component c = new Component(MapUtils.asMap(
                "key", String.format("%s:%s", getName(), path),
                "name", PathUtils.getName(path),
                "qualifier", toQualifier(type),
                "path", path,
                "language", ""
        ));

        return SonarObject.of(c, this);
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
        for(Metric metric : provider.getCategory(category).getMetrics()) {
            mmap.put(metric.getId(), (SonarMetric)metric);
            mkeys.add(metric.getId());
        }

        MetricsClient metricsClient = client.metricsClient();
        metricsClient.list(getId(), null, mkeys, false, measure -> {
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
