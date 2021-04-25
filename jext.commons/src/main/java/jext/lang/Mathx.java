package jext.lang;

public class Mathx {

    public static int chop(int min, int x, int max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}
