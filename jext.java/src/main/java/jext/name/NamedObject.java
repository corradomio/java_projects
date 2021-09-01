package jext.name;

public class NamedObject implements IdNamed {

    private Name name;
    private String id;

    protected NamedObject(Name name) {
        this.name = name;
        setIdFromName();
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    // public void setName(String name) {
    //     name = name.replace(' ', '-');
    //     this.name = new PathName(name);
    //     setIdFromName();
    // }

    public void setName(Name name) {
        this.name = name;
        setIdFromName();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdFromName() {
        if (name != null)
            this.id = Integer.toString(name.getFullName().hashCode(), 16);
        else
            this.id = "0";
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
        return name.equals(that.getName());
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
