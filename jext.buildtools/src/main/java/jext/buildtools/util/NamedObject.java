package jext.buildtools.util;

import jext.buildtools.Name;
import jext.buildtools.Named;
import jext.util.StringUtils;

public class NamedObject implements Named, Comparable<Named> {

    private Name name;

    protected NamedObject(Name name) {
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String getId() {
        return StringUtils.digest(name.getFullName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Named that = (Named)obj;
        return name.equals(that.getName());
    }

    @Override
    public int compareTo(Named that) {
        return name.compareTo(that.getName());
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s]", getClass().getSimpleName(), name.getFullName(), getId());
    }

    //
    protected void setName(Name name) {
        this.name = name;
    }
}
