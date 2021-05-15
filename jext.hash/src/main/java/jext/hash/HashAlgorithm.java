package jext.hash;

public interface HashAlgorithm {

    void reset();

    void update(byte[] data, int offset, int length);

    Hash hash();

}
