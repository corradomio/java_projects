package jext.sourcecode.project.util;

import jext.name.NamedObject;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.Field;
import jext.sourcecode.project.FieldName;
import jext.sourcecode.project.RefType;
import jext.util.LongHash;
import jext.util.StringUtils;

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
    private String[] modifiers = StringUtils.emptyArray();
    private String digest = "0";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DefinedField(RefType ownerType, DeclType fieldType, String fieldName) {
        super(new FieldNameObject(ownerType.getName(), fieldName));
        this.ownerType = ownerType;
        this.fieldType = fieldType;
        this.digest = LongHash.asString(fieldType.getSignature());
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

    @Override
    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String[] getModifiers() {
        return modifiers;
    }

    public void setModifiers(String[] modifiers) {
        this.modifiers = modifiers;
        this.digest = LongHash.asString(modifiers, fieldType.getSignature());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
