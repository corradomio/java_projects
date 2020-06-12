package jext.javassist;

import java.util.ArrayList;
import java.util.List;

public class TypeDesc implements Comparable<TypeDesc> {

    public enum Role {
        UNKNOWN,
        CLASS,
        INTERFACE,
        ENUM,
        ANNOTATION,
        PRIMITIVE,
        TYPE_PARAM
    }

    public String name;
    public Role role = Role.UNKNOWN;
    public List<String> extendsTypes = new ArrayList<>();
    public List<String> implementsTypes = new ArrayList<>();
    public List<TypeDesc> typeParams = new ArrayList<>();
    public String signature;

    @Override
    public int compareTo(TypeDesc that) {
        return toString().compareTo(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        TypeDesc that = (TypeDesc) obj;
        return toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return name;
    }
}
