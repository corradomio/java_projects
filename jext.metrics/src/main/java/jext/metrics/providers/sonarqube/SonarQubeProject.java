package jext.metrics.providers.sonarqube;

import jext.metrics.MetricsComponent;
import jext.metrics.MetricsProject;
import jext.util.Assert;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.ComponentClient;

import java.util.List;
import java.util.stream.Collectors;

public class SonarQubeProject extends SonarQubeComponent implements MetricsProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private final String name;
    private final SonarQubeProvider provider;
    private SonarClient client;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    SonarQubeProject(String name, SonarQubeProvider provider, SonarClient client) {
        super(null, provider, client);
        Assert.notNull(name, "name");
        this.name = name;
        this.provider = provider;
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
    public boolean hasChildren() {
        return true;
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
    // End
    // ----------------------------------------------------------------------

}
