package jext.scitools.und;

public class InFile {

    private com.scitools.understand.Entity file;
    private int line;
    private int column;

    public InFile(com.scitools.understand.Entity file, int line, int column) {
        this.file = file;
        this.line = line;
        this.column = column;
    }

    public Entity file() {
        return Entity.of(file);
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }
}
