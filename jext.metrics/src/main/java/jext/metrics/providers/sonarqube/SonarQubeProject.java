package jext.metrics.providers.sonarqube;

import jext.metrics.MetricValue;
import jext.metrics.MetricsComponent;
import jext.metrics.MetricsProject;
import jext.util.Assert;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.ComponentClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SonarQubeProject implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final String name;
    private final SonarQubeProvider provider;
    private SonarClient client;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarQubeProject(String name, SonarQubeProvider provider) {
        Assert.notNull(name, "name");
        this.name = name;
        this.provider = provider;
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
    public boolean hasChildren() {
        return true;
    }

    @Override
    public List<MetricsComponent> getChildren() {
        ComponentClient cclient = client.componentClient();
        return cclient.list(getId())
                .stream()
                .map(c -> new SonarQubeComponent(c, client))
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Measures
    // ----------------------------------------------------------------------

    @Override
    public Collection<MetricValue> getMetricValues(String id) {
        return Collections.emptyList();
    }

    @Override
    public Collection<MetricValue> getMetricValues(String id, String category) {
        return Collections.emptyList();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    void connect() {
        String url = provider.getUrl();
        String username = provider.getUsername();
        String password = provider.getPassword();

        client = SonarClient.builder()
                .url(url)
                .login(username)
                .password(password)
                .build();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
