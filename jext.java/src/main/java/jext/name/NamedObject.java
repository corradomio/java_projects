package jext.name;

public class NamedObject implements IdNamed {

    protected Name name;
    protected String id;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected NamedObject(Name name) {
        this.name = name;
        if (name != null)
            setIdFromName();
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    public void setNameWithId(Name name) {
        this.name = name;
        setIdFromName();
    }

    public void setIdFromName() {
        this.id = Integer.toHexString(this.name.hashCode());
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    // ----------------------------------------------------------------------
    // Hash support
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Named that = (Named)obj;
        return this.getName().equals(that.getName());
    }

    @Override
    public int compareTo(Named that) {
        return name.compareTo(that.getName());
    }

    // ----------------------------------------------------------------------
    // Tree support
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s[%s, %s]", getClass().getSimpleName(), name.getFullName(), getId());
    }

}
