package org.hls.anon;

import org.hls.anon.use.UseKey;
import org.hls.anon.use.UseValue;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;

public class CallAnon3 {

    public static void main(String[] args) {

        List<Map<String, Integer>> local1 = new AbstractList<>() {
            // 1
            @Override
            public Map<String, Integer> get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };

        List<Map<UseKey, UseValue>> local2 = new AbstractList<>() {
            // 2
            @Override
            public Map<UseKey, UseValue> get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    public static abstract class C<T1 extends Number, T2 > {}

}
