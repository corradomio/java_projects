package jext.sourcecode.project.util;

import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.MethodName;
import jext.util.Assert;

import java.util.Objects;

public class MethodNameObject extends ObjectName implements MethodName {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final String signature;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MethodNameObject(Name name, String methodName, String signature) {
        super(name, methodName);
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
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getSignature());
    }

    @Override
    public boolean equals(Object obj) {
        MethodName that = (MethodName) obj;
        return this.getFullName().equals(that.getFullName())
            && this.getSignature().equals(that.getSignature());
    }

    @Override
    public int compareTo(Name o) {
        MethodName that = (MethodName) o;
        int cmp = this.getFullName().compareTo(that.getFullName());
        if (cmp != 0) return cmp;
        return this.getSignature().compareTo(that.getSignature());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
