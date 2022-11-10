package jext.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeId {
    public static final String NO_ID = "-1";

    public static boolean invalidId(String id) {
        return id == null || NO_ID.equals(id) || id.isEmpty();
    }

    public static Long asId(String id) {
        return Long.valueOf(id);
    }

    public static String toId(long id) {
        return Long.toString(id);
    }

    public static Set<Long> asIds(Collection<String> ids) {
        if (ids == null)
            return Collections.emptySet();
        return ids
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toSet());
    }

}
