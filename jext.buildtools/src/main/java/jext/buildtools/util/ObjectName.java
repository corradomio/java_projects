package jext.buildtools.util;

import jext.buildtools.Name;

public class ObjectName implements Name {

    private String namespace;

    public ObjectName(String namespace) {
        this.namespace = namespace;
    }

    public ObjectName(String namespace, String type) {
        this.namespace = String.format("%s.%s", namespace, type);
    }

    @Override
    public String getName() {
        int sep = namespace.lastIndexOf('.');
        if (sep != -1)
            return namespace.substring(sep+1);
        else
            return namespace;
    }

    @Override
    public Name getParent() {
        String pname = getParentName();
        if(pname != null)
            return new ObjectName(pname);
        else
            return null;
    }

    @Override
    public String getParentName() {
        int sep = namespace.lastIndexOf('.');
        if (sep != -1)
            return namespace.substring(0, sep);
        else
            return null;
    }

    @Override
    public String getFullname() {
        return namespace;
    }

    @Override
    public int hashCode() {
        return getFullname().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Name that = (Name) obj;
        return getFullname().equals(that.getFullname());
    }

    @Override
    public int compareTo(Name that) {
        return getFullname().compareTo(that.getFullname());
    }

    @Override
    public String toString() {
        return namespace;
    }
}
