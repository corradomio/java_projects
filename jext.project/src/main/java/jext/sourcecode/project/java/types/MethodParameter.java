package jext.sourcecode.project.java.types;

import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.Method;
import jext.sourcecode.project.Parameter;
import jext.sourcecode.project.RefType;

public class MethodParameter extends NamedObject implements Parameter {

    private final Method ownerMethod;
    private DeclType parameterType;

    public MethodParameter(Method method, DeclType parameterType, String parameterName) {
        super(new ObjectName(method.getName(), parameterName));
        this.ownerMethod = method;
        this.parameterType = parameterType;
    }

    public DeclType getType() {
        return parameterType;
    }

    public RefType getOwnerType() {
        return new ReferencedType(getName().getParent().getParent());
    }

    public Method getOwnerMethod() {
        return ownerMethod;
    }

}
