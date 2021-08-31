package jext.hashing;

public interface Hash {

    String algorithm();

    byte[] hash();

    float distance(Hash that);
}
