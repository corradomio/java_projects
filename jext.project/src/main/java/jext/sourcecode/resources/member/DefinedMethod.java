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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static jext.lang.JavaUtils.PUBLIC;

public class DefinedMethod extends NamedObject implements Method {

    private String declaration;
    private RefType ownerType;
    private RefType returnType = ReferencedType.JAVA_LANG_VOID;
    private List<Parameter> parameters = new ArrayList<>();
    private AtomicInteger callIndex;
    private MethodName lastCallName;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public DefinedMethod(RefType ownerType, String methodName, int nParams) {
        this(ownerType, methodName, nParams, null, null);
    }

    public DefinedMethod(RefType ownerType,
                         String methodName, int nParams,
                         String signature, String declaration) {
        super(new MethodNameObject(ownerType.getName(), methodName, nParams, toSignature(methodName, nParams, signature)));

        // remove spaces
        if (declaration != null)
            declaration = declaration.trim();
        this.ownerType = ownerType;
        this.callIndex = new AtomicInteger();
        this.lastCallName = getMethodName();
        this.declaration = declaration;
    }

    private static String toSignature(String methodName, int nParams, String signature) {
        // methodName = StringUtils.lastOf(methodName, ".");
        // if (signature != null)
        //     return JavaUtils.toPlainMethodSignature(signature);
        // else
        //     return JavaUtils.toPlainMethodSignature(methodName, nParams);
        return signature;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    public void setReturnType(RefType returnType) {
        if (returnType != null)
            this.returnType = returnType;
        else
            this.returnType = ReferencedType.JAVA_LANG_VOID;
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


    @Override
    public String getSignature() {
        return getMethodName().getSignature();
    }

    @Override
    public String getDeclaration() {
        return declaration;
    }

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
