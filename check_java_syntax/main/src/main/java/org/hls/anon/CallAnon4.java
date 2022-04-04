package org.hls.anon;

import org.hls.anon.known.TypeReference;
import org.hls.anon.use.UseKey;
import org.hls.anon.use.UseValue;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;

public class CallAnon4 {
    public static void main(String[] args) {

        TypeReference<Map<String, Integer>> trmsi1 = new TypeReference<Map<String, Integer>>() {
            // 1
        };

        TypeReference<Map<String, Integer>> trmsi2 = new TypeReference<Map<String, Integer>>() {
            // 2
            @Override
            public int size() {
                return 0;
            }
        };

        TypeReference<Map<UseKey, UseValue>> trmkv1 = new TypeReference<Map<UseKey, UseValue>>() {
            // 3
        };

        TypeReference<Map<UseKey, UseValue>> trmkv2 = new TypeReference<Map<UseKey, UseValue>>() {
            // 4
            @Override
            public int size() {
                return 0;
            }
        };
    }
}
