package jext.tasks;

public class ProgressStatus {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private int total;
    private int current;
    private String message;

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public int    getTotal()   { return total; }
    public int    getCurrent() { return current; }
    public double getDone()    { return (total > 0 && current >= 0) ? (0.+current)/(0.+total) : 0.;}
    public String getMessage() { return message; }

    // ----------------------------------------------------------------------
    // Setter
    // ----------------------------------------------------------------------
    // Sometimes the total number of steps is not known. In this case the
    // total is updated

    public ProgressStatus setTotal(int total) {
        // set current to -1 for this reason:
        // if there are 3 steps to execute, when we are at step n. 3
        // the execution is not terminated yet!

        this.total = total;
        this.current = -1;
        this.message = "Started ...";
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

    public int update(String msg, int wd) {
        if (message == null)
            message = "update";

        current += wd;
        message = msg;
        if (current >= total)
            total = current + 1;

        return current;
    }

    public ProgressStatus completed() {
        current = total;
        return this;
    }

}
