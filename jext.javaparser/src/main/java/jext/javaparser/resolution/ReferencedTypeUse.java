package jext.javaparser.resolution;

import com.github.javaparser.resolution.types.ResolvedType;

public class ReferencedTypeUse implements ResolvedType {

    private String qualifiedName;

    public ReferencedTypeUse(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @Override
    public String describe() {
        return qualifiedName;
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("ReferencedTypeUse[%s]", qualifiedName);
    }
}
