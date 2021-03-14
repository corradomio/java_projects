package jext.javaparser.util;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodCallScope {

    public enum Type {
        UNAVAILABLE,
        INITIALIZER,
        CONSTRUCTOR,
        METHOD
    }

    private static MethodCallScope instance = new MethodCallScope();

    public static MethodCallScope unavailable() { return instance; }

    public Type type;

    public InitializerDeclaration inizializerDeclaration;
    public MethodDeclaration methodDeclaration;
    public ConstructorDeclaration constructorDeclaration;

    private MethodCallScope() {
        type = Type.UNAVAILABLE;
    }

    public MethodCallScope(InitializerDeclaration id) {
        inizializerDeclaration = id;
        type = Type.INITIALIZER;
    }

    public MethodCallScope(ConstructorDeclaration cd) {
        constructorDeclaration = cd;
        type = Type.CONSTRUCTOR;
    }

    public MethodCallScope(MethodDeclaration md) {
        methodDeclaration = md;
        type = Type.METHOD;
    }

    public boolean isPresent() {
        return type != Type.UNAVAILABLE;
    }

}
