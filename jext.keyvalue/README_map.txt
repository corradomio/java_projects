public interface Map<K, V> {
    int size();
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    V put(K key, V value);
    V remove(Object key);
    void putAll(Map<? extends K, ? extends V> m);
    void clear();

    Set<K> keySet();
    Collection<V> values();
    Set<Map.Entry<K, V>> entrySet();
}
public interface ConcurrentMap<K,V> extends Map<K,V> {

    V putIfAbsent(K key, V value);
    boolean remove(Object key, Object value);
    boolean replace(K key, V oldValue, V newValue);
    V replace(K key, V value);

}

-----------------------------------------------------------------------------

RocksDB

-----------------------------------------------------------------------------

HaloDB

    public static HaloDB open(File dirname, HaloDBOptions opts) throws HaloDBException {
    public static HaloDB open(String directory, HaloDBOptions opts) throws HaloDBException {

    public byte[] get(byte[] key) throws HaloDBException {
    public boolean put(byte[] key, byte[] value) throws HaloDBException {
    public void delete(byte[] key) throws HaloDBException {

    public void close() throws HaloDBException {

    public long size() {

-----------------------------------------------------------------------------

lmdnjava


public final class Dbi<T> {
  public void close() {
  public boolean delete(final T key) {
  public boolean delete(final Txn<T> txn, final T key) {
  public boolean delete(final Txn<T> txn, final T key, final T val) {
  public void drop(final Txn<T> txn) {
  public void drop(final Txn<T> txn, final boolean delete) {
  public T get(final Txn<T> txn, final T key) {
  public byte[] getName() {
  public void put(final T key, final T val) {
  public boolean put(final Txn<T> txn, final T key, final T val, final PutFlags... flags) {
  public T reserve(final Txn<T> txn, final T key, final int size, final PutFlags... op) {
}

-----------------------------------------------------------------------------

leveldb

public interface DB extends Iterable<Entry<byte[], byte[]>>, Closeable {
    byte[] get(byte[] key) throws DBException;
    byte[] get(byte[] key, ReadOptions options) throws DBException;
    void put(byte[] key, byte[] value) throws DBException;
    void delete(byte[] key) throws DBException;
    Snapshot put(byte[] key, byte[] value, WriteOptions options) throws DBException;
    Snapshot delete(byte[] key, WriteOptions options) throws DBException;
}

-----------------------------------------------------------------------------

chronical map


public interface ChronicleMap<K, V> extends
        ConcurrentMap<K, V>,
        ChronicleHash<K, MapEntry<K, V>,
                         MapSegmentContext<K, V, ?>,
                         ExternalMapQueryContext<K, V, ?>> {
    V get(Object key);
    V getUsing(K key, V usingValue);
    <R> R getMapped(K key, @NotNull SerializableFunction<? super V, R> function);
    void getAll(File toFile) throws IOException;
    void putAll(File fromFile) throws IOException;
}

-----------------------------------------------------------------------------

PalDB

public interface StoreReader {
    long size();
    <K> K get(Object var1);
    <K> K get(Object var1, K var2);

    int get<T>(Object var1) throws NotFoundException;
    int get<T>(Object var1, T var2);

    <K> K[] getArray(Object var1);
    <K> K[] getArray(Object var1, K[] var2);
    int[] get<T>Array(Object var1) throws NotFoundException;
    int[] get<T>Array(Object var1, T[] var2);

}
