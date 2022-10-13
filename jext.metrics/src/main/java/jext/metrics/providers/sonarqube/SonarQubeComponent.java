package jext.metrics.providers.sonarqube;

import jext.metrics.MetricsComponent;
import org.sonar.wsclient.SonarClient;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;

import java.util.List;
import java.util.stream.Collectors;

public class SonarQubeComponent implements MetricsComponent {

    private final SonarClient client;
    private final Component component;

    SonarQubeComponent(Component c, SonarClient client) {
        this.component = c;
        this.client = client;
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
    public boolean hasChildren() {
        return "DIR".equals(component.qualifier());
    }

    @Override
    public List<MetricsComponent> getChildren() {
        ComponentClient cclient = client.componentClient();
        return cclient.list(getId())
                .stream()
                .map(c -> new SonarQubeComponent(c, client))
                .collect(Collectors.toList());
    }

}
