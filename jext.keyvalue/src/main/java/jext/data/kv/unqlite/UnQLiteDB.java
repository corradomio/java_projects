package jext.data.kv.unqlite;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnQLiteDB implements AutoCloseable {

    public interface OpenMode {
        int UNQLITE_NONE = 0x00;
        int UNQLITE_OPEN_READONLY = 0x01;
        int UNQLITE_OPEN_READWRITE = 0x02;
        int UNQLITE_OPEN_CREATE = 0x04;
        int UNQLITE_OPEN_EXCLUSIVE = 0x08;
        int UNQLITE_OPEN_TEMP_DB = 0x10;
        int UNQLITE_OPEN_NOMUTEX = 0x20;
        int UNQLITE_OPEN_OMIT_JOURNALING = 0x40;
        int UNQLITE_OPEN_IN_MEMORY = 0x80;
        int UNQLITE_OPEN_MMAP = 0x100;
    }

    public interface ErrorCodes {
        int UNQLITE_OK = 0;
        int UNQLITE_NOMEM = -1;
        int UNQLITE_ABORT = -10;
        int UNQLITE_IOERR = -2;
        int UNQLITE_CORRUPT = -24;
        int UNQLITE_LOCKED = -4;
        int UNQLITE_BUSY = -14;
        int UNQLITE_DONE = -28;
        int UNQLITE_PERM = -19;
        int UNQLITE_NOTIMPLEMENTED = -17;
        int UNQLITE_NOTFOUND = -6;
        int UNQLITE_NOOP = -20;
        int UNQLITE_INVALID = -9;
        int UNQLITE_EOF = 18;
        int UNQLITE_UNKNOWN = -13;
        int UNQLITE_LIMIT = -7;
        int UNQLITE_EXISTS = -11;
        int UNQLITE_EMPTY = -3;
        int UNQLITE_COMPILE_ERR = (-70);
        int UNQLITE_VM_ERR = (-71);
        int UNQLITE_FULL = (-73);
        int UNQLITE_CANTOPEN = (-74);
        int UNQLITE_READ_ONLY = (-75);
        int UNQLITE_LOCKERR = (-76);
    }

    private static UnQLiteLibrary unqlite;

    public static UnQLiteDB open(File dbfile, int openmode) {
        loadLibrary();


        PointerByReference ppDB = new PointerByReference();
        check(unqlite.unqlite_open(ppDB, dbfile.getAbsolutePath(), openmode));

        return new UnQLiteDB(ppDB.getValue());
    }

    public void close() {
        check(unqlite.unqlite_close(pDB));
    }

    private Pointer pDB;
    private byte[] buffer = new byte[1024];
    private int[] plength = new int[1];

    private UnQLiteDB(Pointer pDB) {
        this.pDB = pDB;
    }

    public void store(byte[] key, byte[] value) {
        check(unqlite.unqlite_kv_store(pDB, key, key.length, value, value.length));
    }

    public byte[] fetch(byte[] key) {
        byte[] value = buffer;
        plength[0] = buffer.length;

        check(unqlite.unqlite_kv_fetch(pDB, key, key.length, value, plength));

        return Arrays.copyOf(value, plength[0]);
    }

    private byte[] fetchKey(Pointer pCur) {
        byte[] key = buffer;
        plength[0] = buffer.length;

        check(unqlite.unqlite_kv_cursor_key(pCur, key, plength));

        return Arrays.copyOf(key, plength[0]);
    }

    private byte[] fetchData(Pointer pCur) {
        byte[] data = buffer;
        plength[0] = buffer.length;

        check(unqlite.unqlite_kv_cursor_data(pCur, data, plength));

        return Arrays.copyOf(data, plength[0]);
    }

    public List<byte[]> keys() {
        List<byte[]> keys = new ArrayList<>();

        PointerByReference ppCur = new PointerByReference();
        check(unqlite.unqlite_kv_cursor_init(pDB, ppCur));

        Pointer pCur = ppCur.getValue();
        check(unqlite.unqlite_kv_cursor_first_entry(pCur));

        int rc = 0;
        while (rc == 0) {
            byte[] key = fetchKey(pCur);

            keys.add(key);

            rc = unqlite.unqlite_kv_cursor_next_entry(pCur);
        }
        check(unqlite.unqlite_kv_cursor_release(pDB, pCur));
        return keys;
    }

    public void delete(byte[] key) {
        check(unqlite.unqlite_kv_delete(pDB, key, key.length));
    }

    private static void check(int rc) {
        if (rc == 0)
            return;

        throw new UnQLiteError(String.format("Error code %d", rc));
    }

    private static void loadLibrary() {
        File libraryFile = new File(System.getProperty("java.io.tmpdir"), "libunqlite.dll");
        try(InputStream stream =
                UnQLiteDB.class.getResourceAsStream("/jext/data/kv/unqlite/windows/libunqlite.dll");) {

            if (!libraryFile.exists())
                Files.copy(stream, libraryFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            unqlite = Native.load(libraryFile.toString(), UnQLiteLibrary.class);
        } catch (IOException e) {
            libraryFile.delete();
            throw new RuntimeException(e);
        }

    }
}
