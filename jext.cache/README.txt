JSR 107
https://www.baeldung.com/jcache
https://docs.oracle.com/cd/B14099_19/web.1012/b14012/objcache.htm
https://java-source.net/open-source/cache-solutions

https://github.com/apache/commons-jcs
https://github.com/cache2k/cache2k
    https://cache2k.org/
    https://cache2k.org/docs/latest/user-guide.html
https://github.com/google/guava
    https://github.com/google/guava/wiki
https://github.com/Cetsoft/imcache
https://github.com/ehcache/ehcache3
https://github.com/ben-manes/caffeine


https://hazelcast.com/
    https://www.baeldung.com/java-hazelcast
http://www.mapdb.org/


CachingProvider -> List[CacheManager]
    CacheManager -> List[Cache]
        Cache -> List[Pair[Entry,Expiry]]
            Entry -> Pair[Key,Value]
            Expiry

Cache limits:
    cache size (n of elements)
    expire after insert (milliseconds)
    expire after last access (milliseconds)


Cache.configure(File)
Cache.getCache(name) -> Cache

    Cache<K,V>
        containsKey(K)
        get(K) -> V or null
        get(K, ()->V)  -> V
        put(K,V)
        remove(K)
        clear()
        close()

        evictAll(Collection<K>)
