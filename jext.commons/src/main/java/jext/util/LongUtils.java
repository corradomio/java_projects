package jext.util;

public class LongUtils {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@$";
    // +|@  -> 62
    // /|$  -> 63
    public static final int MAX_RADIX = DIGITS.length();


    public static String toString(long l) {
        return toString(l, Character.MAX_RADIX);
    }

    public static long parseLong(String s) {
        return parseLong(s, Character.MAX_RADIX);
    }

    public static String toString(long l, int radix) {

        if(radix <= Character.MAX_RADIX)
            return Long.toString(l, radix);
        if (radix > MAX_RADIX)
            radix = 10;

        StringBuilder sb = new StringBuilder();
        boolean neg = l < 0;
        if (neg) l = -l;

        do {
            int d = (int)(l % radix);
            l /= radix;
            sb.append(DIGITS.charAt(d));
        } while(l != 0);

        if (neg) sb.append("-");
        return sb.reverse().toString();
    }

    public static long parseLong(String s, int radix) {
        if(radix <= Character.MAX_RADIX)
            return Long.parseLong(s, radix);
        if (radix > MAX_RADIX)
            radix = 10;

        boolean neg = false;
        long l = 0;
        int n = s.length();

        for(int i=0; i<n; ++i) {
            char ch = s.charAt(i);
            l *= radix;
            if (ch == '-')                      // -
                neg = !neg;
            else if (ch == '+' || ch == '@')    // +|@
                l += 62;
            else if (ch == '/' || ch == '$')    // /|$
                l += 63;
            else if (ch <= '9')         // [0-9]
                l += (ch - '0');
            else if (ch <= 'Z')         // [A-Z]
                l += (ch - 'A' + 36);
            else                        // [a-z]
                l += (ch - 'a' + 10);
        }
        return neg ? -l : l;
    }

}
