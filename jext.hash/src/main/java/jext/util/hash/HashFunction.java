package jext.util.hash;

public interface HashAlgorithm {

    void reset();

    void update(byte[] data, int offset, int length);

    HashCode hash();

}
