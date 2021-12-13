package jext.sourcecode.project.util;

import jext.name.NamedObject;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.Method;
import jext.sourcecode.project.MethodName;
import jext.sourcecode.project.Parameter;
import jext.sourcecode.project.RefType;
import jext.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class DefinedMethod extends NamedObject implements Method {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final String declaration;
    private final RefType ownerType;
    private DeclType returnType;
    private final List<Parameter> parameters = new ArrayList<>();
    private final AtomicInteger callIndex;
    private MethodName lastCallName;
    private boolean isStatic;
    private String digest;
    private String[] modifiers = StringUtils.emptyArray();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public DefinedMethod(RefType ownerType, String methodName) {
        this(ownerType, methodName, signatureOf(methodName), null);
    }

    public DefinedMethod(RefType ownerType,
                         String methodName,
                         String signature,
                         String declaration) {
        super(new MethodNameObject(ownerType.getName(), methodName, signature));

        // remove spaces
        if (declaration != null)
            declaration = declaration.trim();

        this.ownerType = ownerType;
        this.callIndex = new AtomicInteger();
        this.lastCallName = getName();
        this.declaration = declaration;
        setReturnType(DeclaredType.of(ReferencedType.VOID));
    }

    private static String signatureOf(String name) {
        return String.format("%s()", name);
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public void setReturnType(DeclType returnType) {
        this.returnType = returnType;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setModifiers(String[] modifiers) {
        this.modifiers = modifiers;
    }

    public void add(DeclType parameterType, String parameterName) {
        MethodParameter parameter = new MethodParameter(this, parameterType, parameterName);
        parameters.add(parameter);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MethodName getName() {
        return (MethodName) super.getName();
    }

    @Override
    public String[] getModifiers() {
        return modifiers;
    }

    @Override
    public DeclType getReturnType() {
        return returnType;
    }

    @Override
    public int getNumOfParams() {
        return parameters.size();
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public RefType getOwnerType() {
        return ownerType;
    }

    // @Override
    // public String getSignature() {
    //     return getMethodName().getSignature();
    // }

    @Override
    public String getDeclaration() {
        return declaration;
    }

    @Override
    public String getDigest() {
        return digest;
    }

    @Override
    public boolean isStatic() {
        return isStatic;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public Optional<Integer> nextIndex(Method lastCall) {
        if (lastCallName.equals(lastCall.getName()))
            return Optional.empty();
        lastCallName = lastCall.getName();
        return Optional.of(callIndex.getAndIncrement());
    }
}
