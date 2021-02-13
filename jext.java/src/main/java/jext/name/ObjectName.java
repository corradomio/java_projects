package jext.name;

import jext.lang.JavaUtils;


/**
 * Can handle two types of names:
 *
 *      - java name:  [namepsace].[name]
 *      - file name:  [folder]/[name]
 */
public class ObjectName implements Name {

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static ObjectName _empty = new ObjectName("");

    public static ObjectName empty() {
        return _empty;
    }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected String name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ObjectName(String name) {
        this.name = name;
    }

    public ObjectName(String namespace, String name) {
        this.name = JavaUtils.fullName(namespace, name);
    }

    public ObjectName(Name parent, String name) {
        this.name = JavaUtils.fullName(parent.getFullName(), name);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isRoot() {
        return name.length() == 0;
    }

    @Override
    public String getName() {
        return JavaUtils.nameOf(name);
    }

    @Override
    public Name getParent() {
        if (isRoot())
            return null;
        else
            return new ObjectName(JavaUtils.namespaceOf(name));
    }

    @Override
    public String getParentName() {
        if (isRoot())
            return null;
        else
            return JavaUtils.namespaceOf(name);
    }

    @Override
    public String getFullName() {
        return name;
    }

    @Override
    public String[] getParts() {
        return name.split("\\.");
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // @Override
    // public Name compose(String name) {
    //     return new ObjectName(this.name, name);
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
        return name;
    }

}
