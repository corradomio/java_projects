package org.hls.check;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

public class MainMapDB {

    static void create() {
        //import org.mapdb.*
        DB db = DBMaker.fileDB("test.mapdb").make();
        ConcurrentMap<String, String> map = db.hashMap("map",
            Serializer.STRING, Serializer.STRING).createOrOpen();
        map.put("something", "here");

        db.commit();
        System.out.println(map.get("something"));
        db.close();
    }

    static void open() {
        DB db = DBMaker.fileDB("test.mapdb").make();
        ConcurrentMap<String, String> map = db.hashMap("map",
            Serializer.STRING, Serializer.STRING).open();
        System.out.println(map.get("something"));

        db.close();
    }

    public static void main(String[] args) {
        create();
        open();
    }
}
