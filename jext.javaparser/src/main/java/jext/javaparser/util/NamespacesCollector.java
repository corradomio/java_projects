package jext.javaparser.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class NamespacesCollector {

    private static Map<String, NamespacesCollector> collectors = new HashMap<>();

    public static NamespacesCollector get(String name) {
        synchronized (collectors) {
            if (!collectors.containsKey(name))
                collectors.put(name, new NamespacesCollector());
            return collectors.get(name);
        }
    }

    public static void remove(String name) {
        synchronized (collectors) {
            collectors.remove(name);
        }
    }

    private Set<String> namespaces = new ConcurrentSkipListSet<>();

    public void add(String namespace) {
        namespaces.add(namespace);
    }

    public boolean contains(String namespace) {
        return namespaces.contains(namespace);
    }
}
