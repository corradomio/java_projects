package jext.sourcecode.project.csharp;

import jext.java.TypeRole;
import jext.name.Name;
import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.TypeParam;

import java.util.Collections;
import java.util.List;

public class Namespace extends NamedObject implements RefType {

    public static Namespace of(String name) {
        return new Namespace(ObjectName.of(name));
    }

    public static Namespace of(Name name) {
        return new Namespace(name);
    }

    public Namespace(Name name) {
        super(name);
    }

    @Override
    public Project getProject() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Library getLibrary() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name getNamespace() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TypeRole getRole() {
        return TypeRole.UNKNOWN;
    }

    @Override
    public boolean isType() {
        return false;
    }

    @Override
    public Type asType() {
        throw new UnsupportedOperationException();
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
    public int getTypeParametersCount() {
        return 0;
    }

    @Override
    public List<TypeParam> getTypeParameters() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }
}
