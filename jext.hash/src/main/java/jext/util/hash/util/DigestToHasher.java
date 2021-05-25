package jext.util.hash.util;

import jext.util.hash.Digester;
import jext.util.hash.HashCode;
import jext.util.hash.Hasher;

import java.nio.ByteBuffer;

public class DigestToHasher implements Hasher {

    private Digester digest;

    public DigestToHasher(Digester digest) {
        this.digest = digest;
    }

    @Override
    public Hasher putByte(byte b) {
        digest.update(b);
        return this;
    }

    @Override
    public Hasher putBytes(byte[] bytes) {
        digest.update(bytes, 0, bytes.length);
        return this;
    }

    @Override
    public Hasher putBytes(byte[] bytes, int off, int len) {
        for (int i = 0; i < len; ++i, ++off)
            digest.update(bytes[i]);
        return this;
    }

    @Override
    public Hasher putBytes(ByteBuffer bytes) {
        for (int i=0;i<bytes.limit(); ++i)
            digest.update(bytes.get());
        return this;
    }

    @Override
    public Hasher putShort(short s) {
        digest.update((byte) s);
        s >>>= 8;
        digest.update((byte) s);
        s >>>= 8;
        return this;
    }

    @Override
    public Hasher putInt(int i) {
        digest.update((byte) i);
        i >>>= 8;
        digest.update((byte) i);
        i >>>= 8;
        digest.update((byte) i);
        i >>>= 8;
        digest.update((byte) i);
        i >>>= 8;
        return this;
    }

    @Override
    public Hasher putLong(long l) {
        digest.update((byte) l);
        l >>>= 8;
        digest.update((byte) l);
        l >>>= 8;
        digest.update((byte) l);
        l >>>= 8;
        digest.update((byte) l);
        l >>>= 8;
        return this;
    }

    @Override
    public Hasher putFloat(float f) {
        putInt(Float.floatToIntBits(f));
        return this;
    }

    @Override
    public Hasher putDouble(double d) {
        putLong(Double.doubleToLongBits(d));
        return this;
    }

    @Override
    public Hasher putBoolean(boolean b) {
        putByte((byte) (b ? 1 : 0));
        return this;
    }

    @Override
    public Hasher putChar(char c) {
        digest.update((byte) c);
        c >>>= 8;
        digest.update((byte) c);
        c >>>= 8;
        return this;
    }

    @Override
    public Hasher putString(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); ++i)
            putChar(charSequence.charAt(i));
        return this;
    }

    // @Override
    // public Hasher putString(CharSequence charSequence, Charset charset) {
    //     for (int i = 0; i < charSequence.length(); ++i)
    //         putChar(charSequence.charAt(i));
    //     return this;
    // }

    @Override
    public <T> Hasher putObject(T instance) {
        // return this;
        throw new UnsupportedOperationException();
    }

    @Override
    public HashCode hash() {
        return new DigestHashCode(digest.digest());
    }
}
