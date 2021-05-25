package jext.util.hash;

public interface HashFunction {

    Hasher newHasher();

    /** newHasher().putBytes(input).hash() */
    default HashCode hashBytes(byte[] input) {
        return newHasher().putBytes(input).hash();
    }

    /** newHasher().putBytes(input, off, len).hash() */
    default HashCode hashBytes(byte[] input, int off, int len) {
        return newHasher().putBytes(input, off, len).hash();
    }

}
