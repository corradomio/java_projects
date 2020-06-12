package jext.javaparser.symbolsolver.javassistmodel;

import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistClassDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistMethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.MethodResolutionLogic;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.SyntheticAttribute;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


public class JavassistJavaLangObjectDeclaration /*extends JavassistClassDeclaration*/ {
    //
    // private CtClass ctClass;
    // private TypeSolver typeSolver;
    //
    // public JavassistJavaLangObjectDeclaration(CtClass ctClass, TypeSolver typeSolver) {
    //     super(ctClass, typeSolver);
    //     this.ctClass = ctClass;
    //     this.typeSolver = typeSolver;
    // }
    //
    // @Override
    // public List<ResolvedReferenceType> getAncestors(boolean acceptIncompleteList) {
    //     return Collections.emptyList();
    // }
    //
    // @Override
    // public ResolvedReferenceType getSuperClass() {
    //     return null;
    // }
    //
    // @Override
    // @Deprecated
    // public SymbolReference<ResolvedMethodDeclaration> solveMethod(String name, List<ResolvedType> argumentsTypes, boolean staticOnly) {
    //     List<ResolvedMethodDeclaration> candidates = new ArrayList<>();
    //     Predicate<CtMethod> staticOnlyCheck = m -> !staticOnly || (staticOnly && Modifier.isStatic(m.getModifiers()));
    //     for (CtMethod method : ctClass.getDeclaredMethods()) {
    //         boolean isSynthetic = method.getMethodInfo().getAttribute(SyntheticAttribute.tag) != null;
    //         boolean isNotBridge = (method.getMethodInfo().getAccessFlags() & AccessFlag.BRIDGE) == 0;
    //         if (method.getName().equals(name) && !isSynthetic && isNotBridge && staticOnlyCheck.test(method)) {
    //             ResolvedMethodDeclaration candidate = new JavassistMethodDeclaration(method, typeSolver);
    //             candidates.add(candidate);
    //
    //             // no need to search for overloaded/inherited methods if the method has no parameters
    //             if (argumentsTypes.isEmpty() && candidate.getNumberOfParams() == 0) {
    //                 return SymbolReference.solved(candidate);
    //             }
    //         }
    //     }
    //
    //     ResolvedReferenceType rrt = getSuperClass();
    //     if (rrt == null) {
    //         return SymbolReference.unsolved(ResolvedMethodDeclaration.class);
    //     }
    //
    //     // add the method declaration of the superclass to the candidates, if present
    //     SymbolReference<ResolvedMethodDeclaration> superClassMethodRef = MethodResolutionLogic
    //         .solveMethodInType(getSuperClass().getTypeDeclaration(), name, argumentsTypes, staticOnly);
    //     if (superClassMethodRef.isSolved()) {
    //         candidates.add(superClassMethodRef.getCorrespondingDeclaration());
    //     }
    //
    //     // add the method declaration of the interfaces to the candidates, if present
    //     for (ResolvedReferenceType interfaceRef : getInterfaces()) {
    //         SymbolReference<ResolvedMethodDeclaration> interfaceMethodRef =
    //             MethodResolutionLogic.solveMethodInType(interfaceRef.getTypeDeclaration(), name, argumentsTypes,
    //                 staticOnly);
    //         if (interfaceMethodRef.isSolved()) {
    //             candidates.add(interfaceMethodRef.getCorrespondingDeclaration());
    //         }
    //     }
    //
    //     return MethodResolutionLogic.findMostApplicable(candidates, name, argumentsTypes, typeSolver);
    // }
}
