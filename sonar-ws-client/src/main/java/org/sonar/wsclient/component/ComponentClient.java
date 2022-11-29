package org.sonar.wsclient.component;

import java.util.List;

public interface ComponentClient {

    Component get(String id);
    List<Component> list(String id, boolean recursive);
}
