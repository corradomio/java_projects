package jext.name;

public class EmptyName implements Name {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String EMPTY = "";
    public static final EmptyName NO_NAME = new EmptyName();

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static EmptyName noName() {
        return NO_NAME;
    }

    public static EmptyName empty() {
        return NO_NAME;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private EmptyName() { }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int compareTo(Name o) {
        return EMPTY.compareTo(o.getFullName());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
