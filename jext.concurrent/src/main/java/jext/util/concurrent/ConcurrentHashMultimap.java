// package jext.util.concurrent;
//
// import jext.util.HashMultimap;
// import jext.util.HashSet;
//
// import java.util.Set;
//
// public class ConcurrentHashMultimap<K, V> extends HashMultimap<K, V> {
//
//     @Override
//     public synchronized boolean containsKey(K key) {
//         return super.containsKey(key);
//     }
//
//     @Override
//     public synchronized void put(K key, V value) {
//         super.put(key, value);
//     }
//
//     @Override
//     public synchronized Set<V> get(K key) {
//         return super.get(key);
//     }
//
//     @Override
//     public synchronized Set<V> getOrDefault(K key, Set<V> defval) {
//         return super.getOrDefault(key, defval);
//     }
//
//
//     @Override
//     public synchronized Set<K> keySet() {
//         return new HashSet<>(super.keySet());
//     }
//
// }
