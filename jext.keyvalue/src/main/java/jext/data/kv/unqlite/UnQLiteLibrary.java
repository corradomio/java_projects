package jext.data.kv.unqlite;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface UnQLiteLibrary extends Library {

    // UnQLiteLibrary INSTANCE = Native.load("libunqlite", UnQLiteLibrary.class);

    int unqlite_open(PointerByReference ppDB, String filename, int imode);
    int unqlite_close(Pointer pDB);

    int unqlite_kv_store(Pointer pDB, byte[] key, int keylen, byte[] data, int datalen);
    int unqlite_kv_fetch(Pointer pDB, byte[] key, int keylen, byte[] data, int[] pdatalen);
    int unqlite_kv_delete(Pointer pDB, byte[] key, int keylen);

    int unqlite_kv_cursor_init(Pointer pDB, PointerByReference ppCur);
    int unqlite_kv_cursor_release(Pointer pDB, Pointer pCur);
    int unqlite_kv_cursor_first_entry(Pointer pCur);
    int unqlite_kv_cursor_next_entry(Pointer pCur);
    // int unqlite_kv_cursor_last_entry(Pointer pCur);
    // int unqlite_kv_cursor_valid_entry(Pointer pCur);
    // int unqlite_kv_cursor_prev_entry(Pointer pCur);
    int unqlite_kv_cursor_key(Pointer pCur, byte[] key, int[] pkeylen);
    int unqlite_kv_cursor_data(Pointer pCur, byte[] data, int[] pdatalen);

}
