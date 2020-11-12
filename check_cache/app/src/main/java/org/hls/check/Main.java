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

        System.out.println(cache.getIfPresent("zero"));

        System.out.printf("%s\n", cache.getChecked("one", () -> {
            System.out.println("called callable 1\n");
            return 1;
        }));

        System.out.printf("%s\n", cache.getChecked("two", () -> {
            System.out.println("called callable 2\n");
            return 2;
        }));

        System.out.printf("%s\n", cache.getChecked("two", () -> {
            System.out.println("called callable 2\n");
            return 2;
        }));
    }
}
