package org.hls.check;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.logging.Logger;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException {
        Logger.configure();
        CacheManager.configure();

        Cache<String, Integer> cache = CacheManager.getCache("org.hls.default", String.class, Integer.class);

        cache = CacheManager.getCache("test.home.this.ciccio", String.class, Integer.class);

        System.out.println(cache.getOrDefault("zero", -1));

        System.out.printf("%s\n", cache.get("one", () -> {
            System.out.println("called callable 1");
            return 1;
        }));

        System.out.printf("%s\n", cache.get("two", () -> {
            System.out.println("called callable 2");
            return 2;
        }));

        System.out.printf("%s\n", cache.get("two", () -> {
            System.out.println("called callable 2");
            return 2;
        }));
    }
}
