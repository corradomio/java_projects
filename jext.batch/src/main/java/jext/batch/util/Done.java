package jext.batch.util;

public class Done {
    public int current;
    public int total;

    public Done(int current, int total) {
        this.current = current;
        this.total = total;
    }

    public double getCompleted() {
        return total != 0 ? current/(0.+total) : 0.;
    }
}
