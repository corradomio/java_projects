package jext.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NodeId {
    public static final String NO_ID = "-1";

    public static boolean invalidId(String id) {
        return id == null || NO_ID.equals(id) || id.isEmpty();
    }

    public static boolean invalidId(Collection<String> ids) {
        return ids == null || ids.isEmpty();
    }

    public static String toId(long id) {
        return Long.toString(id);
    }

    public static Long asId(String id) {
        return Long.valueOf(id);
    }

    public static List<Long> asId(String[] ids) {
        List<Long> list = new ArrayList<>();
        for(String id : ids)
            list.add(asId(id));
        return list;
    }

    public static List<Long> asId(Collection<String> ids) {
        List<Long> list = new ArrayList<>();
        for(String id : ids)
            list.add(asId(id));
        return list;
    }

}
