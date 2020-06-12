package jext.tasks;

public class ProgressStatus {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private int total;
    private int current;

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int    getTotal() { return total; }
    public int    getCurrent() { return current; }
    public double getDone() { return total > 0 ? (0.+current)/(0.+total) : 0.;}

    // ----------------------------------------------------------------------
    // Getter/setter
    // ----------------------------------------------------------------------

    public ProgressStatus setTotal(int total) {
        this.total = total;
        this.current = 0;
        return this;
    }

    public ProgressStatus setCurrent(int current) {
        this.current = current;
        if (current > total)
            total = current;
        return this;
    }

    public ProgressStatus setCurrent(int current, int total) {
        this.current = current;
        this.total = total;
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public int update(int wd) {
        setCurrent(current + wd);
        return current;
    }

    public ProgressStatus completed() {
        current = total;
        return this;
    }

}
