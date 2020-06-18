package jext.buildtools.util;

import jext.buildtools.Name;

public class PathName implements Name {
    private String path;

    public PathName() {
        this.path = "";
    }

    public PathName(String name) {
        this.path = name;
    }

    public PathName(PathName parent, String name) {
        if (parent.toString().isEmpty())
            this.path = name;
        else
            this.path = parent.toString() + "/" + name;
    }

    @Override
    public String getName() {
        int sep = path.lastIndexOf('/');
        return path.substring(sep+1);
    }

    @Override
    public String getParentName() {
        if (path.length() == 0)
            return null;
        int sep = path.lastIndexOf('/');
        if (sep == -1)
            return "";
        else
            return path.substring(0, sep);
    }

    @Override
    public PathName getParent() {
        String name = getParentName();
        return name == null ? null : new PathName(name);
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        PathName that = (PathName) obj;
        return path.equals(that.path);
    }

    @Override
    public String toString() {
        return path;
    }
}
