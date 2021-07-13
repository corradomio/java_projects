package jext.sourcecode.project.util;

import jext.name.NamedObject;
import jext.name.ObjectName;
import jext.sourcecode.project.Method;
import jext.sourcecode.project.Parameter;
import jext.sourcecode.project.RefType;

public class MethodParameter extends NamedObject implements Parameter {

    private Method ownerMethod;
    private RefType parameterType;

    public MethodParameter(Method method, String parameterName) {
        super(new ObjectName(method.getName(), parameterName));
        this.ownerMethod = method;
    }

    public void setType(RefType type) {
        this.parameterType = type;
    }

    public RefType getType() {
        return parameterType;
    }

    public String getTypeId() {
        return getType().getId();
    }

    public RefType getOwnerType() {
        return new ReferencedType(getName().getParent().getParent());
    }

    public String getOwnerTypeId() {
        return getOwnerType().getId();
    }

    public Method getOwnerMethod() {
        return ownerMethod;
    }

    public String getOwnerMethodId() {
        return getOwnerMethod().getId();
    }
}
