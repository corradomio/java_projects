package jext.scitools.und;

public class InFile {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private com.scitools.understand.Entity file;
    private int line;
    private int column;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public InFile(com.scitools.understand.Entity file, int line, int column) {
        this.file = file;
        this.line = line;
        this.column = column;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public Ent file() {
        return Ent.of(file);
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }
}
