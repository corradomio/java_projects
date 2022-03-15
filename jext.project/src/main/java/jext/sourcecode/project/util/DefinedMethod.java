package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.NamedObject;
import jext.sourcecode.project.DeclType;
import jext.sourcecode.project.Method;
import jext.sourcecode.project.MethodName;
import jext.sourcecode.project.Parameter;
import jext.sourcecode.project.RefType;
import jext.util.SetUtils;
import jext.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class DefinedMethod extends NamedObject implements Method {

    public static DefinedMethod of(RefType ownerType, String methodName) {
        return of(ownerType, methodName, methodName);
    }

    public static DefinedMethod of(RefType ownerType, String methodName, String signature) {
        return new DefinedMethod(ownerType, methodName, signature);
    }

    public static DefinedMethod of(RefType ownerType, DeclType returnType, String methodName, String signature) {
        DefinedMethod method = of(ownerType, methodName, signature);
        method.setReturnType(returnType);
        return method;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String STATIC = "static";

    private final RefType ownerType;
    private DeclType returnType;
    private Name lastCallName;
    private final List<Parameter> parameters = new ArrayList<>();

    private int numOfTypeParams;

    private Set<String> modifiers = Collections.emptySet();
    private String declaration;
    private final String signature;

    private final AtomicInteger callIndex;
    private String digest = "0";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DefinedMethod(
        RefType ownerType,
        String methodName,
        String signature)
    {
        super(new MethodNameObject(ownerType.getName(), methodName, signature));

        this.ownerType = ownerType;
        this.callIndex = new AtomicInteger();
        this.lastCallName = getName();
        this.returnType   = DeclaredType.VOID;
        this.signature    = signature;
        this.declaration  = signature;
    }

    // ----------------------------------------------------------------------
    // Setters
    // ----------------------------------------------------------------------

    public void setDigest(String digest) {
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

    public void setReturnType(DeclType returnType) {
        this.returnType = returnType;
    }

    public void setDeclaration(String declaration) {
        if (declaration != null)
            this.declaration = declaration.trim();
    }

    public void add(DeclType parameterType, String parameterName) {
        MethodParameter parameter = new MethodParameter(this, parameterType, parameterName);
        parameters.add(parameter);
    }

    public void setNumOfTypeParams(int nTypeParams) {
        this.numOfTypeParams = nTypeParams;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public MethodName getName() {
        return (MethodName) super.getName();
    }

    @Override
    public Set<String> getModifiers() {
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
    public int getNumOfTypeParams() {
        return numOfTypeParams;
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public RefType getOwnerType() {
        return ownerType;
    }

    @Override
    public String getSignature() {
        return signature;
    }

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
        // for (int i=0; i<modifiers.length; ++i)
        //     if (STATIC.equals(modifiers[i]))
        //         return true;
        // return false;
        return modifiers.contains(STATIC);
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
