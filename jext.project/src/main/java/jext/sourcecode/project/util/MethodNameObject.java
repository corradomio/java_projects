package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.MethodName;

import java.util.Objects;

public class MethodNameObject extends ObjectName implements MethodName {

    private int nParams;
    private String signature;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MethodNameObject(Name name, int nParams, String signature) {
        this(name.getFullName(), nParams, signature);
    }

    public MethodNameObject(Name name, String methodName, int nParams, String signature) {
        this(name.getFullName(), methodName, nParams, signature);
    }

    public MethodNameObject(String qualifiedName, int nParams, String signature) {
        super(qualifiedName);

        this.nParams = nParams;
        this.signature = signature;
    }

    public MethodNameObject(String typeName, String methodName, int nParams, String signature) {
        super(typeName, methodName);

        this.nParams = nParams;
        this.signature = signature;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public int getNumOfParams() {
        return nParams;
    }

    @Override
    public boolean hasSignature() {
        return signature != null;
    }

    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        if (signature != null)
            return Objects.hash(getFullName(), signature, nParams);
        else
            return Objects.hash(getFullName(), nParams);
    }

    @Override
    public boolean equals(Object obj) {
        MethodName that = (MethodName) obj;
        return this.getFullName().equals(that.getFullName())
            && this.getNumOfParams() == that.getNumOfParams()
            && this.hasSignature() == that.hasSignature()
            && (this.hasSignature() && this.getSignature().equals(that.getSignature()) || !this.hasSignature());
    }

    @Override
    public int compareTo(Name name) {
        MethodName that = (MethodName) name;
        int cmp = this.getFullName().compareTo(that.getFullName());
        if (cmp != 0) return cmp;
        cmp = this.getNumOfParams() - that.getNumOfParams();
        if (cmp != 0) return cmp;
        cmp = (this.hasSignature() ? 1 : 0) - (that.hasSignature() ? 1 : 0);
        if (cmp != 0) return cmp;
        if (this.hasSignature())
            return this.getSignature().compareTo(that.getSignature());
        return 0;
    }

    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return getSignature();
    }

}