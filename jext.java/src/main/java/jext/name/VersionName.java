package jext.name;

public class VersionName implements Name {

    private String name;
    private String fullname;

    public VersionName(String name, String version) {
        if (!version.isEmpty() && name.endsWith(version)) {
            int sep = name.lastIndexOf(version);
            name = name.substring(0, sep-1);
        }
        this.name = name;
        if (version.isEmpty())
            this.fullname = name;
        else
            this.fullname = String.format("%s-%s", name, version);
    }

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

    @Override
    public int compareTo(Name that) {
        return getFullName().compareTo(that.getFullName());
    }
}
