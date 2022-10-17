package org.sonar.wsclient.metrics;

import org.sonar.wsclient.services.Measure;

import java.util.Collection;
import java.util.List;

public interface MetricsClient {

    List<Measure> list(String id, Collection<String> metrics, boolean recursive);
}
