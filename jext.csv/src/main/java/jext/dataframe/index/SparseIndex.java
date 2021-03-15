package jext.dataframe.index;

import jext.dataframe.IntegerIndex;
import jext.dataframe.util.Arrays;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SparseIndex implements IntegerIndex {

    private Map<Integer, Integer> map = new TreeMap<>();

    // ----------------------------------------------------------------------

    public SparseIndex(int[] indices) {
        for (int i=0; i<indices.length; ++i)
            map.put(indices[i], i);
    }

    // ----------------------------------------------------------------------

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public int at(Integer loc) {
        return map.getOrDefault(loc, -1);
    }

    @Override
    public int get(int loc) {
        return map.getOrDefault(loc, -1);
    }

    // ----------------------------------------------------------------------

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Index(%s)", Arrays.toString(map.keySet()));
    }
}
