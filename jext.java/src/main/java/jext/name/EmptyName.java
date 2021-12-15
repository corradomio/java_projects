package jext.name;

public class EmptyName implements Name {

    private static final String EMPTY = "";
    public static final EmptyName NO_NAME = new EmptyName();

    public static EmptyName noName() {
        return NO_NAME;
    }

    public static EmptyName empty() {
        return NO_NAME;
    }

    private EmptyName() { }

    @Override
    public boolean isRoot() {
        return true;
    }

    @Override
    public Name getParent() {
        return null;
    }

    @Override
    public String getName() {
        return EMPTY;
    }

    @Override
    public String getParentName() {
        return null;
    }

    @Override
    public String getFullName() {
        return EMPTY;
    }

    @Override
    public int compareTo(Name o) {
        return EMPTY.compareTo(o.getFullName());
    }
}
