// package jext.util.concurrent;
//
// import java.util.HashMap;
// import java.util.Map;
//
// /// Data structure to keep in a centralized place a lock object for each key
// public class MultiLocks<K> {
//     private Map<K, Object> locks = new HashMap<>();
//
//     public Object get(K key) {
//         synchronized (locks) {
//             if (!locks.containsKey(key))
//                 locks.put(key, new Object());
//             return locks.get(key);
//         }
//     }
// }
