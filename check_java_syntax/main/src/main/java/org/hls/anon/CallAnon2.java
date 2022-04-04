package org.hls.anon;

import org.hls.anon.known.TypeReference;

import java.util.Map;

public class CallAnon2 {

    public static void main(String[] args) {

        (new TypeReference<Map<String, Integer>>() {

            @Override
            public int size() {
                return super.size();
            }

            @Override
            public Map<String, Integer> get(int i) {
                return super.get(i);
            }
        }).size();

    }
}
