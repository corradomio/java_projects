package jext.sourcecode.resources.type;

import jext.java.TypeRole;
import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.Field;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Method;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.TypeUse;
import jext.sourcecode.project.UseDirection;
import jext.sourcecode.project.util.NamedObject;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static jext.lang.JavaUtils.PUBLIC;

public class ImplementedType extends NamedObject implements Type {

    public static ImplementedType ANONYMOUS = new ImplementedType("", "Anonymous", TypeRole.UNKNOWN, null);

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Name namespace;
    private Source source;
    private TypeRole role;
    public boolean innerType;
    public int nParams;

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
    }

    public ImplementedType(String namespace, String name, TypeRole role, Source source) {
        this(new ObjectName(namespace), new ObjectName(name), role, source);
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

    @Override
    public Set<String> getModifiers() {
        return Collections.emptySet();
    }

    @Override
    public String getVisibility() {
        return PUBLIC;
    }

    @Override
    public Set<String> getStructure() {
        return Collections.emptySet();
    }

    // -- type role

    @Override
    public TypeRole getRole() {
        return role;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isInner() {
        return !namespace.equals(getName().getParent());
    }

    @Override
    public List<Type> getUseTypes(TypeUse useType, UseDirection direction, boolean recursive, boolean refTypes) {
        return Collections.emptyList();
    }

    @Override
    public List<Field> getFields() {
        return Collections.emptyList();
    }

    @Override
    public List<Method> getMethods() {
        return Collections.emptyList();
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

    @Override
    public String getLibraryId() {
        return null;
    }

    // -- Source

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public String getSourceId() {
        return source.getId();
    }

    // -- Module

    @Override
    public Module getModule() {
        return source.getModule();
    }

    @Override
    public String getModuleId() {
        return source.getModule().getId();
    }

    @Override
    public String getModuleRefId() {
        return source.getModule().getRefId();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

}
