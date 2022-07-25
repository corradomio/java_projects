package jext.name;

public class VersionName implements Name {

    public static VersionName of(String name, String version) {
        return new VersionName(name, version);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String name;
    private String fullname;
    private String version;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private VersionName(String name, String version) {
        if (version == null)
            version = "";

        if (!version.isEmpty() && name.endsWith(version)) {
            int sep = name.lastIndexOf(version);
            name = name.substring(0, sep-1);
        }
        this.name = name;
        if (version.isEmpty())
            this.fullname = name;
        else
            this.fullname = String.format("%s-%s", name, version);
        this.version = version;
    }

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
        return name;
    }

    @Override
    public String getParentName() {
        return null;
    }

    @Override
    public String getFullName() {
        return fullname;
    }

    public String getVersion() {
        return version;
    }

    // ----------------------------------------------------------------------
    // Override
    // ----------------------------------------------------------------------

    @Override
    public int compareTo(Name that) {
        return getFullName().compareTo(that.getFullName());
    }

    @Override
    public boolean equals(Object obj) {
        Name that = (Name) obj;
        return getFullName().equals(that.getFullName());
    }

    @Override
    public int hashCode() {
        return getFullName().hashCode();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
