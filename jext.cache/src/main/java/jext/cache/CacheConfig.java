package jext.cache;

public interface CacheConfig {

    /** Maximum capacity (number of entries) */
    String CAPACITY = "capacity";

    /** Expiry policy based on the first 'put': after the specified timeout */
    String EXPIRE_AFTER_WRITE = "expireAfterWrite";

    /** Expiry policy based on the last 'put'/'get': after the specified timeout */
    String EXPIRE_AFTER_ACCESS = "expireAfterAccess";

    /** If a value is wrapped by a WeakReference (boolean) */
    String WEAK_VALUES = "weakValues";

    /** If a value is wrapped by a WeakReference (boolean) */
    String SOFT_VALUES = "softValues";

}
