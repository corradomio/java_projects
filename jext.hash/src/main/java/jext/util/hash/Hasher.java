package jext.util.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public interface Hasher {

    Hasher putByte(byte b);

    Hasher putBytes(byte[] bytes);

    Hasher putBytes(byte[] bytes, int off, int len);

    Hasher putBytes(ByteBuffer bytes);

    Hasher putShort(short s);

    Hasher putInt(int i);

    Hasher putLong(long l);

    /** Equivalent to {@code putInt(Float.floatToRawIntBits(f))}. */
    Hasher putFloat(float f);

    /** Equivalent to {@code putLong(Double.doubleToRawLongBits(d))}. */
    Hasher putDouble(double d);

    /** Equivalent to {@code putByte(b ? (byte) 1 : (byte) 0)}. */
    Hasher putBoolean(boolean b);

    Hasher putChar(char c);

    Hasher putString(CharSequence charSequence);

    // Hasher putString(CharSequence charSequence, Charset charset);

    <T> Hasher putObject(T instance);

    HashCode hash();
}
