package jext.javaparser.symbolsolver.javassistmodel;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFactory;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import javassist.CtClass;

public class JavassistFactoryObject extends JavassistFactory {

    public static ResolvedReferenceTypeDeclaration toTypeDeclaration(CtClass ctClass, TypeSolver typeSolver) {

        // TRICK to resolve a problem of "Stack Overflow"!
        // The parent of "java.lang.Object" is "java.lang.Object"!
        //
        // if ("java.lang.Object".equals(ctClass.getName()))
        //     return new JavassistJavaLangObjectDeclaration(ctClass, typeSolver);
        // else
        //     return JavassistFactory.toTypeDeclaration(ctClass, typeSolver);

        return JavassistFactory.toTypeDeclaration(ctClass, typeSolver);
    }
}
