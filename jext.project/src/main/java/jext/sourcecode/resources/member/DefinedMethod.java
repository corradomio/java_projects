package jext.sourcecode.resources.member;

import jext.lang.JavaUtils;
import jext.sourcecode.project.Method;
import jext.sourcecode.project.MethodName;
import jext.sourcecode.project.Parameter;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.util.MethodNameObject;
import jext.sourcecode.project.util.NamedObject;
import jext.sourcecode.resources.type.ReferencedType;
import jext.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class DefinedMethod extends NamedObject implements Method {

    private RefType ownerType;
    private RefType returnType = ReferencedType.VOID;
    private List<Parameter> parameters = new ArrayList<>();
    private AtomicInteger callIndex;
    private MethodName lastCallName;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    // public DefinedMethod(Name methodName, int nParams, String signature) {
    //     super(new MethodNameObject(methodName, nParams, signature));
    //     this.ownerType = new ReferencedType(getName().getParent());
    //     this.callIndex = new AtomicInteger();
    // }

    public DefinedMethod(RefType ownerType, String methodName, int nParams, String signature) {
        super(new MethodNameObject(ownerType.getName(), methodName, nParams, toSignature(methodName, nParams, signature)));
        this.ownerType = ownerType;
        this.callIndex = new AtomicInteger();
        this.lastCallName = getMethodName();
    }

    private static String toSignature(String methodName, int nParams, String signature) {
        methodName = StringUtils.lastOf(methodName, ".");
        if (signature != null)
            return JavaUtils.toPlainMethodSignature(signature);
        else
            return JavaUtils.toPlainMethodSignature(methodName, nParams);
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    public void setType(RefType returnType) {
        if (returnType != null)
            this.returnType = returnType;
        else
            this.returnType = ReferencedType.VOID;
    }

    public void add(String parameterName, RefType type) {
        MethodParameter parameter = new MethodParameter(this, parameterName);
        parameter.setType(type);
        parameters.add(parameter);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MethodName getMethodName() {
        return (MethodName) getName();
    }

    @Override
    public String getSignature() {
        return getMethodName().getSignature();
    }

    @Override
    public RefType getType() {
        return returnType;
    }

    @Override
    public String getTypeId() {
        return returnType.getId();
    }

    @Override
    public List<? extends Parameter> getParameters() {
        return parameters;
    }

    @Override
    public RefType getOwnerType() {
        return ownerType;
    }

    @Override
    public String getOwnerTypeId() {
        return ownerType.getId();
    }

    // private void composeSignature() {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append(returnType.getName().getName())
    //         .append(" ")
    //         .append(getName().getName())
    //         .append("(");
    //
    //     boolean rest = false;
    //     for(Parameter p : parameters) {
    //         if (rest) sb.append(",");
    //         sb.append(p.getType().getName().getName())
    //             .append(" ")
    //             .append(p.getName().getName());
    //         rest = true;
    //     }
    //     sb.append(")");
    //
    //     signature = sb.toString();
    // }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public Optional<Integer> nextIndex(Method lastCall) {
        if (lastCallName.equals(lastCall.getMethodName()))
            return Optional.empty();
        lastCallName = lastCall.getMethodName();
        return Optional.of(callIndex.getAndIncrement());
    }
}
