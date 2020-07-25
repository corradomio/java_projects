package jext.buildtools.util;

import jext.buildtools.Name;

public class PathName implements Name {

    public static PathName empty() {
        return new PathName("");
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String path;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------


    public PathName(String path) {
        this.path = PathUtils.normalize(path);
    }

    public PathName(String ns, String name) {
        this.path = PathUtils.concat(ns, name);
    }

    public PathName(Name parent, String name) {
        this.path = PathUtils.concat(parent.getFullName(), name);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return PathUtils.getName(path);
    }

    @Override
    public Name getParent() {
        return new PathName(PathUtils.getParent(path));
    }

    @Override
    public String getParentName() {
        return getParent().getFullName();
    }

    @Override
    public String getFullName() {
        return path;
    }

    @Override
    public boolean isRoot() {
        return path.length() == 0;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // @Override
    // public Name compose(String name) {
    //     return new PathName(path, name);
    // }

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
        return path;
    }
}
