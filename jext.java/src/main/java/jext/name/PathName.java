package jext.name;

/*
    'a/b/c'
    '/a/b/c' -> 'a/b/c'
    '/'
    ''
 */

import jext.util.PathUtils;

public class PathName implements Name {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String path;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PathName(String path) {
        if (path == null)
            throw new NullPointerException();
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
    public boolean isRoot() {
        return path.length() == 0;
    }

    @Override
    public String getName() {
        return PathUtils.getName(path);
    }

    @Override
    public Name getParent() {
        if (isRoot())
            return null;
        else
            return new PathName(PathUtils.getParent(path));
    }

    @Override
    public String getParentName() {
        if (isRoot())
            return null;
        else
            return PathUtils.getParent(path);
    }

    @Override
    public String getFullName() {
        return path;
    }

    // @Override
    // public String[] getParts() {
    //     return path.split("/");
    // }

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
