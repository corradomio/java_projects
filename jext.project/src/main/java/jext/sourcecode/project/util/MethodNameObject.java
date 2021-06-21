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
    // Override
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        if (signature != null)
            return Objects.hash(getFullName(), signature);
        else
            return Objects.hash(getFullName(), nParams);
    }

    @Override
    public boolean equals(Object obj) {
        MethodName that = (MethodName) obj;

        // both method have a signature
        if (this.hasSignature() && that.hasSignature())
        return this.getFullName().equals(that.getFullName())
                && this.getSignature().equals(that.getSignature());
        // some method don't have the signature
        else
            return this.getFullName().equals(that.getFullName())
                && this.getNumOfParams() == that.getNumOfParams();
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

    @Override
    public String toString() {
        return String.format("%s/%d", getName(), getNumOfParams());
    }

}
