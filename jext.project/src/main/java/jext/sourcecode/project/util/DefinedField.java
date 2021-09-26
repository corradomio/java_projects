package jext.sourcecode.project.util;

import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.Field;
import jext.sourcecode.project.RefType;

public class DefinedField extends NamedObject implements Field {

    private RefType fieldType;
    private RefType ownerType;

    public DefinedField(RefType ownerType, String fieldName) {
        super(new ObjectName(ownerType.getName(), fieldName));
        this.ownerType = ownerType;
    }

    public void setType(RefType type) {
        this.fieldType = type;
    }

    @Override
    public RefType getType() {
        return fieldType;
    }

    // @Override
    // public String getTypeId() {
    //     return fieldType.getId();
    // }

    @Override
    public RefType getOwnerType() {
        return ownerType;
    }

    // @Override
    // public String getOwnerTypeId() {
    //     return ownerType.getId();
    // }
}
