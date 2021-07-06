package jext.graph;

public class Name {
    private String name;
    private int index;

    public Name(String name) {
        this(name, -1);
    }

    public Name(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
