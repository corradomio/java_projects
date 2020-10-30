package jext.springframework.data.neo4j.repository.support;

import java.util.ArrayList;
import java.util.List;

public class PageContent {

    public static <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
