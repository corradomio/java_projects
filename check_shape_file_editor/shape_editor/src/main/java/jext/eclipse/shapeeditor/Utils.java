package jext.eclipse.shapeeditor;

public class Utils {

    public static int[] compose(int[] xs, int[] ys, int n) {
        int[] xys = new int[2*n];
        for (int i = 0, j=0; i < n; i++) {
            xys[j++] = xs[i];
            xys[j++] = ys[i];
        }
        return xys;
    }
}
