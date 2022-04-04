package org.hls.anon;

import org.hls.anon.use.UseKey;
import org.hls.anon.use.UseValue;

import java.util.AbstractMap;
import java.util.Set;

public class ConcreteKV extends AbstractMap<UseKey, UseValue> {
    @Override
    public Set<Entry<UseKey, UseValue>> entrySet() {
        return null;
    }
}
