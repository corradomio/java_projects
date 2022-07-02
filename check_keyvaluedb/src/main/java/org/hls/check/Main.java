package org.hls.check;

import com.linkedin.paldb.api.StoreReader;
import com.linkedin.paldb.api.StoreWriter;
// import com.oath.halodb.HaloDB;
import net.openhft.chronicle.map.ChronicleMap;
import org.iq80.leveldb.DB;
import org.lmdbjava.Dbi;
import swaydb.KeyVal;
import swaydb.java.Map;
import swaydb.java.Stream;
import swaydb.java.memory.MemoryMap;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;

import static swaydb.java.serializers.Default.intSerializer;

public class Main {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map;
    }

    // static void checkHaloDB() {
    //     HaloDB db;
    // }

    static void checkUnQLite() {

    }

    static void checkLmdn() {
        Dbi<ByteBuffer> dbi;
    }

    static void checkLevelDB() {
        DB db;
    }

    static void checkChronicolMap() {
        ChronicleMap<String, String> map;
    }

    static void checkPalDB() {
        StoreWriter writer;
        StoreReader reader;
    }

    static void checkSwayDB() {
        Map<Integer, Integer, Void> map =
            MemoryMap
                .functionsOff(intSerializer(), intSerializer())
                .get();

        //some basic put, get, expire and remove operations
        map.put(1, 1); //basic put
        map.get(1); //basic get returns Optional(1)
        map.expire(1, Duration.ofSeconds(1)); //basic expire
        map.remove(1); //basic remove

        //range based put, expire and remove
        map.put(Arrays.asList(KeyVal.of(1, 1), KeyVal.of(2, 2), KeyVal.of(3, 3))); //write multiple as batch transaction
        map.expire(1, 3, Duration.ofSeconds(2)); //expire range 1 - 3 after 2 seconds
        map.remove(1, 3); //remove all keys ranging from 1 to 3

        //atomic write a Stream of key-value
        map.put(Stream.range(1, 100).map(KeyVal::of));

        //Create a stream that updates all values within range 10 to 90.
        Stream<KeyVal<Integer, Integer>> updatedKeyValues =
            map
                .from(10)
                .takeWhile(keyVal -> keyVal.key() <= 90)
                .map(keyVal -> KeyVal.of(keyVal.key(), keyVal.value() + 5000000));

        //submit the stream to update the key-values as a single transaction.
        map.put(updatedKeyValues);

        //print all key-values to view the update.
        map.forEach(System.out::println);
    }
}
