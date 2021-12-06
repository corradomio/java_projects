package jext.javaparser.resolution;

import jext.lang.JavaUtils;

import java.util.HashMap;
import java.util.Map;

public class PrimitiveTypeDeclaration extends ReferencedTypeDeclaration {

    private static Map<String, PrimitiveTypeDeclaration> map = new HashMap<>();
    static {
        for(String primitiveType : JavaUtils.PRIMITIVE_TYPES)
            map.put(primitiveType, new PrimitiveTypeDeclaration(primitiveType));
    }

    public static PrimitiveTypeDeclaration of(String name) {
        return map.get(name);
    }

    private PrimitiveTypeDeclaration(String qualifiedName) {
        super(qualifiedName, 0);
    }
}
