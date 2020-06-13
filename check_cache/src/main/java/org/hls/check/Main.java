package org.hls.check;

import jext.cache.Cache;
import jext.cache.CacheConfigurator;
import jext.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger.configure();
        CacheConfigurator.configure();

        Cache<String, Integer> cache = CacheConfigurator.getCache("org.hls.default", String.class, Integer.class);

    }
}
