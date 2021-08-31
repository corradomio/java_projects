package jext.util.hash;

import java.nio.ByteBuffer;

public class HashUtils {

    public static void  putByte(Digester digest, byte b) {
        digest.update(b);
    }

    public static void  putBytes(Digester digest, byte[] bytes) {
        digest.update(bytes, 0, bytes.length);
    }

    public static void  putBytes(Digester digest, byte[] bytes, int off, int len) {
        for (int i = 0; i < len; ++i, ++off)
            digest.update(bytes[i]);
    }

    public static void  putBytes(Digester digest, ByteBuffer bytes) {
        for (int i=0;i<bytes.limit(); ++i)
            digest.update(bytes.get());
    }

    public static void  putShort(Digester digest, short s) {
        digest.update((byte) s);
        s >>>= 8;
        digest.update((byte) s);
        s >>>= 8;
    }

    public static void  putInt(Digester digest, int i) {
        digest.update((byte) i);
        i >>>= 8;
        digest.update((byte) i);
        i >>>= 8;
        digest.update((byte) i);
        i >>>= 8;
        digest.update((byte) i);
        i >>>= 8;
    }

    public static void  putLong(Digester digest, long l) {
        digest.update((byte) l);
        l >>>= 8;
        digest.update((byte) l);
        l >>>= 8;
        digest.update((byte) l);
        l >>>= 8;
        digest.update((byte) l);
        l >>>= 8;
    }

    public static void  putFloat(Digester digest, float f) {
        putInt(digest, Float.floatToIntBits(f));
    }

    public static void  putDouble(Digester digest, double d) {
        putLong(digest, Double.doubleToLongBits(d));
    }

    public static void  putBoolean(Digester digest, boolean b) {
        putByte(digest, (byte) (b ? 1 : 0));
    }

    public static void  putChar(Digester digest, char c) {
        digest.update((byte) c);
        c >>>= 8;
        digest.update((byte) c);
        c >>>= 8;
    }

    public static void  putString(Digester digest, CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); ++i)
            putChar(digest, charSequence.charAt(i));
    }

    // @Override
    // public static void  putString(CharSequence charSequence, Charset charset) {
    //     for (int i = 0; i < charSequence.length(); ++i)
    //         putChar(charSequence.charAt(i));
    //     return this;
    // }

    public static  <T> void putObject(T instance) {
        throw new UnsupportedOperationException();
    }

}
