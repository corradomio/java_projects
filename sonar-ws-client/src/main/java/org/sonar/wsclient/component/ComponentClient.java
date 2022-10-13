package org.sonar.wsclient.component;

import java.util.List;

public interface ComponentClient {

    List<Component> list(String id);
}
