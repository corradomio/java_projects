package jext.sourcecode.project.util;

import jext.name.NamedObject;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.Field;
import jext.sourcecode.project.FieldName;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.util.FieldNameObject;

public class DefinedField extends NamedObject implements Field {

    public static DefinedField of(RefType ownerType, DeclType fieldType, String fieldName) {
        DefinedField dfield = new DefinedField(ownerType, fieldType, fieldName);
        return dfield;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final DeclType fieldType;
    private final RefType  ownerType;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DefinedField(RefType ownerType, DeclType fieldType, String fieldName) {
        super(new FieldNameObject(ownerType.getName(), fieldName));
        this.ownerType = ownerType;
        this.fieldType = fieldType;
        ((FieldNameObject)getName()).setHash(fieldType.getHash());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public FieldName getName() {
        return (FieldName) super.getName();
    }

    @Override
    public DeclType getType() {
        return fieldType;
    }

    @Override
    public RefType getOwnerType() {
        return ownerType;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
