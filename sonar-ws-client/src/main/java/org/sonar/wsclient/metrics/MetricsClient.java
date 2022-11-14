package org.sonar.wsclient.metrics;

import org.sonar.wsclient.services.Measure;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface MetricsClient {

    List<Measure> list(String id, String qualifier, Collection<String> metrics, boolean recursive);
    void list(String id, String qualifier, Collection<String> metrics, boolean recursive, Consumer<Measure> callback);
}
