package jext.bits;

//
// https://www.jjj.de/fxt/#fxt
//

public class Bits {

    public static int  INT_BITS = 32;
    public static int  INT_MASK = 0xFFFFFFFF;
    public static int  LONG_BITS = 64;
    public static long LONG_MASK = 0xFFFFFFFFFFFFFFFFL;

    // -- int

    public static boolean get(int a, int ibit) {
        if (ibit < 0)
            ibit += INT_BITS;
        return (a & (1 << ibit)) != 0;
    }

    public static int and(int a, int b) {
        return a & b;
    }

    public static int or(int a, int b) {
        return a | b;
    }

    public static int xor(int a, int b) {
        return a ^ b;
    }

    public static int not(int a) {
        return ~a;
    }

    /**
     * Shift left (nbits > 0) or right (nbits < 0)
     */
    public static int shift(int a, int nbits) {
        if (nbits > 0)
            return a << nbits;
        else
            return a >>> (-nbits);
    }

    public static int signed_shift(int a, int nbits) {
        if (nbits > 0)
            return a << nbits;
        else
            return a >> (-nbits);
    }

    /**
     * Rotate left (nbits > 0) or right (nbits < 0)
     */
    public static int rotate(int a, int nbits) {
        if (nbits > 0) {
            int rbits = INT_BITS-nbits;
            return (a << nbits) | (a >>> rbits);
        }
        nbits = -nbits;
        {
            int rbits = INT_BITS-nbits;
            return (a >>> nbits) | (a << rbits);
        }
    }

    // -- long

    public static boolean get(long a, int ibit) {
        if (ibit < 0)
            ibit += LONG_BITS;
        return (a & (1L << ibit)) != 0;
    }

    public static long and(long a, long b) {
        return a & b;
    }

    public static long or(long a, long b) {
        return a | b;
    }

    public static long xor(long a, long b) {
        return a ^ b;
    }

    public static long not(long a) {
        return ~a;
    }

    public static long shift(long a, int nbits) {
        if (nbits > 0)
            return a << nbits;
        else
            return a >>> (-nbits);
    }

    public static long signed_shift(long a, int nbits) {
        if (nbits > 0)
            return a << nbits;
        else
            return a >> (-nbits);
    }

    public static long rotate(long a, int nbits) {
        if (nbits > 0) {
            int rbits = LONG_BITS-nbits;
            return (a << nbits) | (a >>> rbits);
        }
        nbits = -nbits;
        {
            int rbits = LONG_BITS-nbits;
            return (a >>> nbits) | (a << rbits);
        }
    }

}
