package jext.name;

import java.util.Objects;

public class ReferencedObject extends NamedObject implements RefIdNamed {

    private String refId;

    public ReferencedObject(Name name) {
        super(name);
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public void setRefIdFromName() {
        this.refId = Integer.toHexString(getName().getFullName().hashCode());
    }

    public void setRefIdFromId() {
        this.refId = getId();
    }

    // ----------------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------------

    @Override
    public String getRefId() {
        return refId;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public int hashCode() {
        return Objects.hash(getName().getFullName(), getRefId());
    }

    public boolean equals(Object obj) {
        RefIdNamed that = (RefIdNamed) obj;
        return getName().equals(that.getName()) && getRefId().equals(that.getRefId());
    }

    @Override
    public int compareTo(Named named) {
        RefIdNamed that = (RefIdNamed) named;
        int cmp = getName().compareTo(that.getName());
        if (cmp == 0)
            cmp = getRefId().compareTo(that.getRefId());
        return cmp;
    }


}
