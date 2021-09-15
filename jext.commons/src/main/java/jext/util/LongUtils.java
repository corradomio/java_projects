package jext.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LongUtils {

    public static List<Long> toList(Collection<String> s) {
        return s.stream().map(Long::valueOf).collect(Collectors.toList());
    }

    public static String toString(long l) {
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static long parseLong(String s) {
        return Long.parseUnsignedLong(s, Character.MAX_RADIX);
    }

    /*

     */
    private static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/";
    public static final int MAX_RADIX = DIGITS.length();

    public static String toString(long l, int radix) {
        if(radix <= Character.MAX_RADIX)
            return Long.toString(l, radix);

        StringBuilder sb = new StringBuilder();
        boolean neg = l < 0;
        if (neg) l = -l;

        do {
            int d = (int)(l%radix);
            l /= radix;
            sb.append(DIGITS.charAt(d));
        } while(l != 0);
        if (neg) sb.append("-");
        return sb.reverse().toString();
    }

    public static long parseLong(String s, int radix) {
        if(radix <= Character.MAX_RADIX)
            return Long.parseUnsignedLong(s, radix);

        long l = 0;
        int n = s.length();
        for(int i=0; i<n; ++i) {
            char ch = s.charAt(i);
            l *= radix;
            if (ch == '-')              // -
                l = -l;
            else if (ch == '+')         // +
                l += 62;
            else if (ch == '/')         // /
                l += 63;
            else if (ch <= '9')         // [0-9]
                l += (ch - '0');
            else if (ch <= 'Z')         // [A-Z]
                l += (ch - 'A' + 36);
            else                        // [a-z]
                l += (ch - 'a' + 10);
        }
        return l;
    }
}
