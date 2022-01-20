package jext.name;

/*
    'a/b/c'
    '/a/b/c' -> 'a/b/c'
    '/'
    ''
 */

import jext.util.PathUtils;

public class PathName implements Name {

    public static PathName of(String path) {
        return new PathName(path);
    }

    public static PathName of(String ns, String name) {
        return new PathName(ns, name);
    }

    public static PathName of(Name parent, String name) {
        return new PathName((PathName)parent, name);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String path;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private PathName(String path) {
        this.path = PathUtils.normalize(path);
    }

    private PathName(String ns, String name) {
        this.path = PathUtils.concat(ns, name);
    }

    private PathName(PathName parent, String name) {
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
            return PathName.of(PathUtils.getParent(path));
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
    //     return PathName.of(path, name);
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
