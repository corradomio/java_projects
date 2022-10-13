package jext.sourcecode.project.java.types;

import jext.name.NamedObject;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.Field;
import jext.sourcecode.project.FieldName;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.util.FieldNameObject;
import jext.util.LongHash;
import jext.util.SetUtils;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

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
    private Set<String> modifiers = Collections.emptySet();
    private String digest = "0";
    private String declaration = "";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DefinedField(RefType ownerType, DeclType fieldType, String fieldName) {
        super(FieldNameObject.of(ownerType.getName(), fieldName));
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
    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        if (declaration != null)
            this.declaration = declaration.trim();
    }

    @Override
    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(String ... modifiers) {
        setModifiers(SetUtils.asSet(modifiers));
    }

    public void setModifiers(Set<String> modifiers) {
        if (this.modifiers.isEmpty())
            this.modifiers = new TreeSet<>(modifiers);
        else
            this.modifiers.addAll(modifiers);
        this.digest = LongHash.toString(LongHash.hash(modifiers, fieldType.getSignature()));
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
