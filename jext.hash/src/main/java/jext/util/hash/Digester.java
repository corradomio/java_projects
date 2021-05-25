package jext.util.hash;

import java.nio.ByteBuffer;

public interface Digester {

    void update(byte b);
    void update(byte[] but, int off, int len);
    byte[] digest();
}
