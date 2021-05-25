package jext.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LongUtils {

    public static List<Long> toList(Collection<String> s) {
        return s.stream().map(Long::valueOf).collect(Collectors.toList());
    }
}
