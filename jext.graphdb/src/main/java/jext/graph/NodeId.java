package jext.graph;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeId {
    public static final String NO_ID = "-1";

    public static boolean invalidId(String id) {
        return id == null || NO_ID.equals(id) || id.isEmpty();
    }

    public static boolean invalidId(Collection<String> ids) {
        return ids == null || ids.isEmpty();
    }

    public static Long asId(String id) {
        return Long.valueOf(id);
    }

    public static Set<Long> asId(Collection<String> ids) {
        if (ids == null)
            return null;
        return ids
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toSet());
    }

    public static String toId(long id) {
        return Long.toString(id);
    }

}
