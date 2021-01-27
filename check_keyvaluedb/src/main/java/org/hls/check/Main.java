package org.hls.check;

import com.google.common.primitives.Ints;
import com.oath.halodb.HaloDB;
import com.oath.halodb.HaloDBException;
import com.oath.halodb.HaloDBIterator;
import com.oath.halodb.HaloDBOptions;
import com.oath.halodb.HaloDBStats;
import com.oath.halodb.Record;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import org.mapdb.DBMaker;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Main {
    public static void main(String[] args) {

    }

    static void test_mapdb() {
        Map<Integer, String> m;
        ConcurrentMap<Integer, String> cm;

        DBMaker.memoryDB().make();
    }

    interface PostalCodeRange {
        int minCode();
        void minCode(int minCode);

        int maxCode();
        void maxCode(int maxCode);
    }
    static void test_cm() {
        ChronicleMapBuilder<CharSequence, PostalCodeRange> cityPostalCodesMapBuilder =
            ChronicleMapBuilder.of(CharSequence.class, PostalCodeRange.class)
                .name("city-postal-codes-map")
                .averageKey("Amsterdam")
                .entries(50_000);
        ChronicleMap<CharSequence, PostalCodeRange> cityPostalCodes =
            cityPostalCodesMapBuilder.create();
    }

    static void test_lmdb() {
        final String DB_NAME = "my DB";
        final TemporaryFolder tmp = new TemporaryFolder();

        final File path = tmp.newFolder();

        // We always need an Env. An Env owns a physical on-disk storage file. One
        // Env can store many different databases (ie sorted maps).
        final Env<ByteBuffer> env = create()
            // LMDB also needs to know how large our DB might be. Over-estimating is OK.
            .setMapSize(10_485_760)
            // LMDB also needs to know how many DBs (Dbi) we want to store in this Env.
            .setMaxDbs(1)
            // Now let's open the Env. The same path can be concurrently opened and
            // used in different processes, but do not open the same path twice in
            // the same process at the same time.
            .open(path);

        // We need a Dbi for each DB. A Dbi roughly equates to a sorted map. The
        // MDB_CREATE flag causes the DB to be created if it doesn't already exist.
        final Dbi<ByteBuffer> db = env.openDbi(DB_NAME, MDB_CREATE);

        // We want to store some data, so we will need a direct ByteBuffer.
        // Note that LMDB keys cannot exceed maxKeySize bytes (511 bytes by default).
        // Values can be larger.
        final ByteBuffer key = allocateDirect(env.getMaxKeySize());
        final ByteBuffer val = allocateDirect(700);
        key.put("greeting".getBytes(UTF_8)).flip();
        val.put("Hello world".getBytes(UTF_8)).flip();
        final int valSize = val.remaining();

        // Now store it. Dbi.put() internally begins and commits a transaction (Txn).
        db.put(key, val);

        // To fetch any data from LMDB we need a Txn. A Txn is very important in
        // LmdbJava because it offers ACID characteristics and internally holds a
        // read-only key buffer and read-only value buffer. These read-only buffers
        // are always the same two Java objects, but point to different LMDB-managed
        // memory as we use Dbi (and Cursor) methods. These read-only buffers remain
        // valid only until the Txn is released or the next Dbi or Cursor call. If
        // you need data afterwards, you should copy the bytes to your own buffer.
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            final ByteBuffer found = db.get(txn, key);
            assertNotNull(found);

            // The fetchedVal is read-only and points to LMDB memory
            final ByteBuffer fetchedVal = txn.val();
            assertThat(fetchedVal.remaining(), is(valSize));

            // Let's double-check the fetched value is correct
            assertThat(UTF_8.decode(fetchedVal).toString(), is("Hello world"));
        }

        // We can also delete. The simplest way is to let Dbi allocate a new Txn...
        db.delete(key);

        // Now if we try to fetch the deleted row, it won't be present
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            assertNull(db.get(txn, key));
        }

        env.close();
    }

    static void test_halodb() throws HaloDBException {
        // Open a db with default options.
        HaloDBOptions options = new HaloDBOptions();

        // Size of each data file will be 1GB.
        options.setMaxFileSize(1024 * 1024 * 1024);

        // Size of each tombstone file will be 64MB
        // Large file size mean less file count but will slow down db open time. But if set
        // file size too small, it will result large amount of tombstone files under db folder
        options.setMaxTombstoneFileSize(64 * 1024 * 1024);

        // Set the number of threads used to scan index and tombstone files in parallel
        // to build in-memory index during db open. It must be a positive number which is
        // not greater than Runtime.getRuntime().availableProcessors().
        // It is used to speed up db open time.
        options.setBuildIndexThreads(8);

        // The threshold at which page cache is synced to disk.
        // data will be durable only if it is flushed to disk, therefore
        // more data will be lost if this value is set too high. Setting
        // this value too low might interfere with read and write performance.
        options.setFlushDataSizeBytes(10 * 1024 * 1024);

        // The percentage of stale data in a data file at which the file will be compacted.
        // This value helps control write and space amplification. Increasing this value will
        // reduce write amplification but will increase space amplification.
        // This along with the compactionJobRate below is the most important setting
        // for tuning HaloDB performance. If this is set to x then write amplification
        // will be approximately 1/x.
        options.setCompactionThresholdPerFile(0.7);

        // Controls how fast the compaction job should run.
        // This is the amount of data which will be copied by the compaction thread per second.
        // Optimal value depends on the compactionThresholdPerFile option.
        options.setCompactionJobRate(50 * 1024 * 1024);

        // Setting this value is important as it helps to preallocate enough
        // memory for the off-heap cache. If the value is too low the db might
        // need to rehash the cache. For a db of size n set this value to 2*n.
        options.setNumberOfRecords(100_000_000);

        // Delete operation for a key will write a tombstone record to a tombstone file.
        // the tombstone record can be removed only when all previous version of that key
        // has been deleted by the compaction job.
        // enabling this option will delete during startup all tombstone records whose previous
        // versions were removed from the data file.
        options.setCleanUpTombstonesDuringOpen(true);

        // HaloDB does native memory allocation for the in-memory index.
        // Enabling this option will release all allocated memory back to the kernel when the db is closed.
        // This option is not necessary if the JVM is shutdown when the db is closed, as in that case
        // allocated memory is released automatically by the kernel.
        // If using in-memory index without memory pool this option,
        // depending on the number of records in the database,
        // could be a slow as we need to call _free_ for each record.
        options.setCleanUpInMemoryIndexOnClose(false);

        // ** settings for memory pool **
        options.setUseMemoryPool(true);

        // Hash table implementation in HaloDB is similar to that of ConcurrentHashMap in Java 7.
        // Hash table is divided into segments and each segment manages its own native memory.
        // The number of segments is twice the number of cores in the machine.
        // A segment's memory is further divided into chunks whose size can be configured here.
        options.setMemoryPoolChunkSize(2 * 1024 * 1024);

        // using a memory pool requires us to declare the size of keys in advance.
        // Any write request with key length greater than the declared value will fail, but it
        // is still possible to store keys smaller than this declared size.
        options.setFixedKeySize(8);

        // Represents a database instance and provides all methods for operating on the database.
        HaloDB db = null;

        // The directory will be created if it doesn't exist and all database files will be stored in this directory
        String directory = "directory";

        // Open the database. Directory will be created if it doesn't exist.
        // If we are opening an existing database HaloDB needs to scan all the
        // index files to create the in-memory index, which, depending on the db size, might take a few minutes.
        db = HaloDB.open(directory, options);

        // key and values are byte arrays. Key size is restricted to 128 bytes.
        byte[] key1 = Ints.toByteArray(200);
        byte[] value1 = "Value for key 1".getBytes();

        byte[] key2 = Ints.toByteArray(300);
        byte[] value2 = "Value for key 2".getBytes();

        // add the key-value pair to the database.
        db.put(key1, value1);
        db.put(key2, value2);

        // read the value from the database.
        value1 = db.get(key1);
        value2 = db.get(key2);

        // delete a key from the database.
        db.delete(key1);

        // Open an iterator and iterate through all the key-value records.
        HaloDBIterator iterator = db.newIterator();
        while (iterator.hasNext()) {
            Record record = iterator.next();
            System.out.println(Ints.fromByteArray(record.getKey()));
            System.out.println(new String(record.getValue()));
        }

        // get stats and print it.
        HaloDBStats stats = db.stats();
        System.out.println(stats.toString());

        // reset stats
        db.resetStats();

        // pause background compaction thread.
        // if a file is being compacted the thread
        // will block until the compaction is complete.
        db.pauseCompaction();

        // resume background compaction thread.
        db.resumeCompaction();

        // repeatedly calling pause/resume compaction methods will have no effect.

        // Close the database.
        db.close();
    }
}
