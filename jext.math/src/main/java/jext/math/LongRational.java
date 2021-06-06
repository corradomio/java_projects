package jext.math;

import sun.plugin.dom.exception.InvalidStateException;

public class LongFraction extends Number implements Comparable<LongFraction> {

    public static LongFraction from(double x, int ndigits) {
        long ip = (long)x;
        long p = 1;
        x = x - ip;
        LongFraction l = new LongFraction(ip, p);
        while (ndigits-- > 0) {
            p *= 10;
            x *= 10;
            ip = (long)x;
            x = x - ip;
            l = l.add(new LongFraction(ip, p));
        }
        return l;
    }

    private long num;
    private long den;

    public LongFraction() {
        this(0,1);
    }

    public LongFraction(long num) {
        this(num,1);
    }

    public LongFraction(long n, long d) {
        this.num = n;
        this.den = d;
        if (den == 0)
            throw new InvalidStateException("Denominator can be not 0");

        long f = gcd(num, den);
        if (f != 1) {
            num /= f;
            den /= f;
        }
        if (den < 0) {
            num = -num;
            den = -den;
        }
    }

    // ----------------------------------------------------------------------
    // Arithmetic
    // ----------------------------------------------------------------------

    public static long gcd(long a, long b) {
        long t;
        while (b!=0) {
            t = b;
            b = a%b;
            a = t;
        }
        return a;
    }

    public static long pow(long a, int e) {
        long p = 1;
        long f = a;
        while(e != 0) {
            if ((e&0x1) != 0)
                p *= f;
            f *= f;
            e >>= 1;
        }
        return p;
    }

    // ----------------------------------------------------------------------
    // Arithmetic
    // ----------------------------------------------------------------------

    public LongFraction add(long that) {
        return add(new LongFraction(that));
    }

    public LongFraction subtract(long that) {
        return subtract(new LongFraction(that));
    }

    public LongFraction multiply(long that) {
        return multiply(new LongFraction(that));
    }

    public LongFraction divide(long that) {
        return divide(new LongFraction(that));
    }

    public LongFraction add(LongFraction that) {
        return new LongFraction(this.num*that.den + that.num*this.den, this.den*that.den);
    }

    public LongFraction subtract(LongFraction that) {
        return new LongFraction(this.num*that.den - that.num*this.den, this.den*that.den);
    }

    public LongFraction multiply(LongFraction that) {
        return new LongFraction(this.num*that.num, this.den*that.den);
    }

    public LongFraction divide(LongFraction that) {
        return new LongFraction(this.num*that.den, this.den*that.num);
    }

    public LongFraction pow(int exp) {
        if (exp == 0)
            return new LongFraction(1);
        if (exp > 0)
            return new LongFraction(pow(num, exp), pow(den, exp));
        else
            return new LongFraction(pow(den, -exp), pow(num, -exp));
    }

    public LongFraction abs() {
        if (num > 0)
            return this;
        else
            return negate();
    }

    public LongFraction negate() {
        return new LongFraction(-num, den);
    }

    public LongFraction min(LongFraction that) {
        int cmp = compareTo(that);
        return (cmp < 0) ? this : that;
    }

    public LongFraction max(LongFraction that) {
        int cmp = compareTo(that);
        return (cmp > 0) ? this : that;
    }

    public long quotient() {
        return num/den;
    }

    public long remainder() {
        return num%den;
    }

    // ----------------------------------------------------------------------
    // Number interface
    // ----------------------------------------------------------------------

    @Override
    public int intValue() {
        return (int)(num/den);
    }

    @Override
    public long longValue() {
        return num/den;
    }

    @Override
    public float floatValue() {
        return (float)((0.+num)/den);
    }

    @Override
    public double doubleValue() {
        return ((0.+num)/den);
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("%d/%d", num, den);
    }

    @Override
    public boolean equals(Object obj) {
        LongFraction that = (LongFraction) obj;
        return this.num == that.num && this.den == that.den;
    }

    @Override
    public int hashCode() {
        return (int)(num*31 + den);
    }

    @Override
    public int compareTo(LongFraction that) {
        long l1 = this.num*that.den;
        long l2 = that.num*this.den;
        if (l1 < l2)
            return -1;
        if (l1 > l2)
            return +1;
        else
            return 0;
    }

}
