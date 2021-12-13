package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.MethodName;
import jext.util.Assert;

import java.util.Objects;

public class MethodNameObject extends ObjectName implements MethodName {

    private final String signature;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MethodNameObject(Name name, String methodName, String signature) {
        this(name.getFullName(), methodName, signature);
    }

    public MethodNameObject(String typeName, String methodName, String signature) {
        super(typeName, methodName);

        Assert.check(signature != null, "signature must be not null");
        this.signature = signature;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getSignature() {
        return signature;
    }

    // ----------------------------------------------------------------------
    // Override
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), signature);
    }

    @Override
    public boolean equals(Object obj) {
        MethodName that = (MethodName) obj;
        return this.getFullName().equals(that.getFullName())
            && this.getSignature().equals(that.getSignature());
    }

    @Override
    public int compareTo(Name name) {
        MethodName that = (MethodName) name;
        int cmp = this.getFullName().compareTo(that.getFullName());
        if (cmp != 0) return cmp;
        return this.getSignature().compareTo(that.getSignature());
    }

    @Override
    public String toString() {
        return String.format("%s", getName());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
