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

    public static ObjectName of(String qualifiedName) {
        return new ObjectName(qualifiedName);
    }

    // public static ObjectName NO_NAME = new ObjectName("");

    // public static ObjectName empty() {
    //     return NO_NAME;
    // }

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    protected String name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ObjectName(Name name) {
        this.name = name.getFullName();
    }

    public ObjectName(String name) {
        this.name = name;
    }

    public ObjectName(String namespace, String name) {
        this.name = JavaUtils.qualifiedName(namespace, name);
    }

    public ObjectName(Name parent, String name) {
        this.name = JavaUtils.qualifiedName(parent.getFullName(), name);
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
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
