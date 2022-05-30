package jext.util;

import java.util.function.Function;

public class DefaultHashMap<K, T> extends HashMap<K, T> {

    private Function<K, T> defval;

    public DefaultHashMap(Function<K, T> defval) {
        this.defval = defval;
    }

    @Override
    public T get(Object key) {
        if (!super.containsKey(key))
            super.put((K)key, this.defval.apply((K)key));
        return super.get(key);
    }

}
