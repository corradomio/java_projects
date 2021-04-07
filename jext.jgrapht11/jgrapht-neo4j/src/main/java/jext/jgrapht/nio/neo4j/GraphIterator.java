package jext.jgrapht.nio.neo4j;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface GraphIterator<T> extends Iterator<T> {

    List<T> toList();

    Set<T> toSet();

    default void forEach(Consumer<? super T> consumer) {
        while(hasNext()) {
            consumer.accept(next());
        }
    }

}
