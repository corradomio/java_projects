package jext.graph;

import java.util.Objects;

public class Name {

    public static Name of(String name) {
        return new Name(name);
    }

    public static Name of(String name, int index) {
        return new Name(name, index);
    }

    private final String name;
    private final int index;

    public Name(String name) {
        this(name, -1);
    }

    public Name(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        if (index == -1)
            return name;
        else
            return String.format("%s[%d]", name, index);
    }

    @Override
    public int hashCode() {
        if (index == -1)
            return name.hashCode();
        else
            return Objects.hash(name.hashCode(), index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            if (index == -1)
                return name.equals(obj);
            else
                return false;
        }
        Name that = (Name) obj;
        return name.equals(that.name) && index == that.index;
    }

}
