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
import jext.sourcecode.project.TypeParam;
import jext.util.SetUtils;

import java.util.*;

public class ImplementedType extends NamedObject implements Type {

    public static ImplementedType ANONYMOUS = new ImplementedType("", "Anonymous", TypeRole.UNKNOWN, null);

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final Name namespace;
    private final Source source;
    private final TypeRole role;
    private final List<TypeParam> tparams = new ArrayList<>();
    private long digest;
    private Set<String> modifiers = Collections.emptySet();

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
        // this.digest = "0";
        this.digest = 0;
    }

    public ImplementedType(String namespace, String name, TypeRole role, Source source) {
        this(new ObjectName(namespace), new ObjectName(name), role, source);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public void setDigest(long digest) {
        this.digest = digest;
    }

    public void setModifiers(String ... modifiers) {
        setModifiers(SetUtils.asSet(modifiers));
    }

    public void setModifiers(Set<String> modifiers) {
        if (this.modifiers.isEmpty())
            this.modifiers = new TreeSet<>(modifiers);
        else
            this.modifiers.addAll(modifiers);
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
        return tparams.size();
    }

    @Override
    public List<TypeParam> getTypeParameters() {
        return tparams;
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
    public long getDigest() {
        return digest;
    }

    @Override
    public Set<String> getModifiers() {
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

    public void addTypeParameter(String name, String signature) {
        tparams.add(new TypeParamImpl(getName(), name, signature));
    }

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

}
