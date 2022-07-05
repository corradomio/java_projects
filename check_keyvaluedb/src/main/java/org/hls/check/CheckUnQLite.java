package org.hls.check;

import jext.data.kv.unqlite.UnQLiteDB;

import java.io.File;

public class CheckUnQLite {

    public static void main(String[] args) {
        // UnQLiteLibrary unqlite = UnQLiteLibrary.INSTANCE;
        // PointerByReference puqdb = new PointerByReference();
        // Pointer uqdb;

        new File("test.unqlite").delete();
        UnQLiteDB uqdb = UnQLiteDB.open(new File("test.unqlite"), UnQLiteDB.OpenMode.UNQLITE_OPEN_CREATE);

        // int rc = unqlite.unqlite_open(puqdb, "test.unqlite", UnQLiteDB.OpenMode.UNQLITE_OPEN_CREATE);
        // uqdb = puqdb.getValue();

        byte[] key = "ciao".getBytes();
        byte[] value = "ciccio".getBytes();

        uqdb.store(key, value);
        value = uqdb.fetch(key);
        String msg = new String(value);
        System.out.printf("ciao %s\n", msg);

        // rc = unqlite.unqlite_kv_store(uqdb, key, key.length, value, value.length);
        // value = new byte[100];
        // int[] plength = new int[]{100};
        // rc = unqlite.unqlite_kv_fetch(uqdb, key, key.length, value, plength);
        // String sval = new String(value, 0, plength[0]);

        // rc = unqlite.unqlite_close(uqdb);
        System.out.println("done");
    }
}
