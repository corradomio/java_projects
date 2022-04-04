package org.hls.anon;

import org.hls.anon.known.TypeReference;
import org.hls.anon.use.UseKey;
import org.hls.anon.use.UseValue;

import java.util.Map;

public class CallAnon1 {

    public static void main(String[] args) {

        (new TypeReference<Map<String, Integer>>() {
            // 1
        }).size();

        (new TypeReference<Map<String, Integer>>() {
            // 2
            @Override
            public int size() {
                return 0;
            }
        }).size();

        (new TypeReference<Map<UseKey, UseValue>>() {
            // 3
        }).size();

        (new TypeReference<Map<UseKey, UseValue>>() {
            // 4
            @Override
            public int size() {
                return 0;
            }
        }).size();

    }
}
