package jext.hashing;

public interface FuzzyHashing {

    void reset();

    void update(byte[] data);
    void update(byte[] data, int offset, int length);

    Hash hash();

}
