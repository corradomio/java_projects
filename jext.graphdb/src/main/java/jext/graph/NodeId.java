package jext.graph;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeId {

    public static Long asId(String id) {
        return Long.valueOf(id);
    }

    public static String toId(long id) {
        return Long.toString(id);
    }

    public static Set<Long> asIds(Collection<String> ids) {
        return ids
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toSet());
    }

}
