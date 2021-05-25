package jext.util.hash;

public interface HashCode {

    int bits();
    int asInt();
    long asLong();
    byte[] asBytes();
    String asHexString();

    boolean equals(Object o);
    float similarity(HashCode hc);
}
