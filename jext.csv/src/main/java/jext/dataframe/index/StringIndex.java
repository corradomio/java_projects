package jext.dataframe.index;

import jext.dataframe.util.Arrays;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class StringIndex implements jext.dataframe.StringIndex {

    private Map<String, Integer> map = new TreeMap<>();

    // ----------------------------------------------------------------------

    public StringIndex(String[] index) {
        for (int i=0; i< index.length; ++i) {
            map.put(index[i], i);
        }
    }

    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public int at(String loc) {
        return map.getOrDefault(loc, -1);
    }

    @Override
    public int get(String loc) {
        return map.getOrDefault(loc, -1);
    }

    // ----------------------------------------------------------------------

    @Override
    public Iterator<String> iterator() {
        return map.keySet().iterator();
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Index(%s)", Arrays.toString(map.keySet()));
    }
}
