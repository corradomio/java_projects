package jext.metrics.providers.sonarqube;

import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SonarQubeComponent implements MetricsComponent {

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
        return Collections.emptyList();
    }

    @Override
    public Collection<MetricValue> getMetricValues(String category) {
        return Collections.emptyList();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
