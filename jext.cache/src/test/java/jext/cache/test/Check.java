package jext.cache.test;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.util.logging.Logger;

public class Check {

    public static void main(String[] args) throws Exception {
        Logger.configure();
        CacheManager.configure();

        Cache<Integer,String> cache1 = CacheManager.getCache("c", Integer.class, String.class);
        cache1.put(1, "one");
        System.out.println(cache1.get(1, null));
        System.out.println(cache1.get(2, () -> "two"));

        Cache<Integer,String> cache2 = CacheManager.getCache("c.d", Integer.class, String.class);
        cache2.put(11, "eleven");
        System.out.println(cache2.get(11, null));
        System.out.println(cache2.get(12, () -> "twelve"));

    }
}
