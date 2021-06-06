package jext.math;

public class LongRational extends Number implements Comparable<LongRational> {

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------
    // https://www.johndcook.com/blog/2010/10/20/best-rational-approximation/

    public static LongRational from(double x) {
        return from(x, 1.e-4);
    }

    public static LongRational from(double x, double dx) {
        boolean neg = x < 0;
        if (neg) x = -x;
        long ip = (long)x;
        LongRational l = fromfp(x-ip, dx);
        l.num += ip*l.den;
        if (neg) l.num = -l.num;
        return l;
    }

    private static LongRational fromfp(double x, double dx) {
        long a=0,b=1,c=1,d=1;
        long N = (long)(1/dx);

        assert(x < 1 && dx < 1);

        while (b <= N && d <= N) {
            double m = (0.+a+c)/(0.+b+d);
            if (x == m) {
                if (b+d <= N)
                    return new LongRational(a+c,b+d);
                else if (d < b)
                    return new LongRational(c, d);
                else
                    return new LongRational(a, b);
            }
            else if (x > m) {
                a = a+c;
                b = b+d;
            }
            else {
                c = a+c;
                d = b+d;
            }
        }
        if (b > N)
            return new LongRational(c, d);
        else
            return new LongRational(a, b);
    }

    public static LongRational from(long num) {
        return new LongRational(num, 1);
    }

    public static LongRational from(long num, long den) {
        return new LongRational(num, den);
    }

    // ----------------------------------------------------------------------
    // Arithmetic
    // ----------------------------------------------------------------------

    private static long lcm(long a, long b) {
        long c = gcd(a, b);
        return a*b/c;
    }

    private static long gcd(long a, long b) {
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
    // Fields
    // ----------------------------------------------------------------------

    private long num;
    private long den;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public LongRational() {
        this(0,1);
    }

    public LongRational(long num) {
        this(num,1);
    }

    public LongRational(long n, long d) {
        this.num = n;
        this.den = d;
        if (den == 0)
            throw new ArithmeticException("Denominator is 0");

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

    public LongRational add(long that) {
        return add(new LongRational(that));
    }

    public LongRational subtract(long that) {
        return subtract(new LongRational(that));
    }

    public LongRational multiply(long that) {
        return multiply(new LongRational(that));
    }

    public LongRational divide(long that) {
        return divide(new LongRational(that));
    }

    public LongRational add(LongRational that) {
        if (this.den == that.den)
            return new LongRational(this.num + that.num, that.den);
        else
            return new LongRational(this.num*that.den + that.num*this.den, this.den*that.den);
    }

    public LongRational subtract(LongRational that) {
        if (this.den == that.den)
            return new LongRational(this.num - that.num, that.den);
        else
            return new LongRational(this.num*that.den - that.num*this.den, this.den*that.den);
    }

    public LongRational multiply(LongRational that) {
        return new LongRational(this.num*that.num, this.den*that.den);
    }

    public LongRational divide(LongRational that) {
        return new LongRational(this.num*that.den, this.den*that.num);
    }

    public LongRational pow(int exp) {
        if (exp == 0)
            return new LongRational(1);
        if (exp > 0)
            return new LongRational(pow(num, exp), pow(den, exp));
        else
            return new LongRational(pow(den, -exp), pow(num, -exp));
    }

    public LongRational abs() {
        if (num > 0)
            return this;
        else
            return negate();
    }

    public LongRational negate() {
        return new LongRational(-num, den);
    }

    public LongRational reciprocal() {
        return new LongRational(den, num);
    }

    public LongRational min(LongRational that) {
        int cmp = compareTo(that);
        return (cmp < 0) ? this : that;
    }

    public LongRational max(LongRational that) {
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
        if (den == 1)
            return Long.toString(num);
        else
            return String.format("%d/%d", num, den);
    }

    @Override
    public boolean equals(Object obj) {
        LongRational that = (LongRational) obj;
        return this.num == that.num && this.den == that.den;
    }

    @Override
    public int hashCode() {
        if (den == 1)
            return Long.hashCode(num);
        else
            return Long.hashCode(den)*31 + Long.hashCode(num);
    }

    @Override
    public int compareTo(LongRational that) {
        long l1;
        long l2;

        if (this.den == that.den) {
            l1 = this.num;
            l2 = that.num;
        }
        else {
            l1 = this.num*that.den;
            l2 = that.num*this.den;
        }

        if (l1 < l2)
            return -1;
        if (l1 > l2)
            return +1;
        else
            return 0;
    }

}
