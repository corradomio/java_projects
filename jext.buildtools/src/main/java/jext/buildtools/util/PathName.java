package jext.buildtools.util;

import jext.buildtools.Name;

public class PathName implements Name {

    private String path;

    public PathName(String path) {
        this.path = path.replace('\\', '/');
    }

    @Override
    public String getName() {
        int sep = path.lastIndexOf('/');
        if (sep != -1)
            return path.substring(sep+1);
        else
            return path;
    }

    @Override
    public String getParentName() {
        int sep = path.lastIndexOf('/');
        if (sep != -1)
            return path.substring(0, sep);
        else
            return null;
    }

    @Override
    public Name getParent() {
        String pname = getParentName();
        if (pname != null)
            return new PathName(pname);
        else
            return null;
    }

    @Override
    public String getFullName() {
        return path;
    }

    @Override
    public int hashCode() {
        return getFullName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Name that = (Name) obj;
        return getFullName().equals(that.getFullName());
    }

    @Override
    public int compareTo(Name that) {
        return getFullName().compareTo(that.getFullName());
    }

    @Override
    public String toString() {
        return path;
    }
}
