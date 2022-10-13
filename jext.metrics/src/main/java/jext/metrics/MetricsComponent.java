package jext.metrics;

import java.util.List;

public interface MetricsComponent {

    String getId();
    String getName();

    boolean hasChildren();
    List<MetricsComponent> getChildren();

}
