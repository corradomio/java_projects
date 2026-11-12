package jext.util.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Flatten {

    public static <T> List<T> flatten(Collection<List<T>> llist) {
        List<T> flattened = new ArrayList<>();

        for (List<T> l : llist)
            flattened.addAll(l);

        return flattened;
    }
}
