package jext.sourcecode.project.util;

import jext.java.TypeRole;
import jext.name.Name;
import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;

import java.util.Collections;
import java.util.List;

public class ImplementedType extends NamedObject implements Type {

    public static ImplementedType ANONYMOUS = new ImplementedType("", "Anonymous", TypeRole.UNKNOWN, null);

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Name namespace;
    private Source source;
    private TypeRole role;
    private int nParams;
    private String digest;
    private String[] modifiers;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public ImplementedType(Name name, TypeRole role, Source source) {
        this(name.getParent(), name, role, source);
    }

    public ImplementedType(Name namespace, Name name, TypeRole role, Source source) {
        super(name);
        this.namespace = namespace;
        this.source = source;
        this.role = role;
        this.digest = "0";
    }

    public ImplementedType(String namespace, String name, TypeRole role, Source source) {
        this(new ObjectName(namespace), new ObjectName(name), role, source);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setModifiers(String[] modifiers) {
        this.modifiers = modifiers;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return source.getProject();
    }

    // -- Type conversion

    @Override
    public boolean isType() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public List<RefType> getElements() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAnonymous() {
        return this.getName().getName().contains("Anonymous");
    }

    @Override
    public Type asType() {
        return this;
    }

    // -- type role

    @Override
    public TypeRole getRole() {
        return role;
    }

    // --

    @Override
    public Name getNamespace() {
        return namespace;
    }

    @Override
    public int getTypeParametersCount() {
        return nParams;
    }

    @Override
    public Library getLibrary() {
        return null;
    }

    // -- Source

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public String getDigest() {
        return digest;
    }

    @Override
    public String[] getModifiers() {
        return modifiers;
    }

    // -- Module

    @Override
    public Module getModule() {
        return source.getModule();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

}
