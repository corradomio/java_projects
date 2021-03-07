package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.resolution.types.ResolvedType;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;

import java.io.File;
import java.util.Optional;

public class ContextVisitorAdapter extends BaseVoidVisitorAdapter {

    // protected String packageName;
    // protected Map<String, String> namedImports = new HashMap<>();
    // protected Set<String> qualifiedImports = new HashSet<>();
    // protected List<String> starImports = new ArrayList<>();
    // protected ContextSolvedSymbols resolvedSymbols;
    // protected UnsolvedSymbols unsolvedSymbols;
    protected File source;
    protected ContextTypeSolver ctx;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ContextVisitorAdapter() {

    }

    // ----------------------------------------------------------------------
    // analyze
    // ----------------------------------------------------------------------

    public void analyze(CompilationUnit cu, ContextTypeSolver ts) {
        cu.getStorage().ifPresent(storage ->
            source = storage.getPath().toFile());

        this.ctx = ts;
        this.ctx.setCu(cu);
        super.analyze(cu, ts);
    }

    // ----------------------------------------------------------------------
    // visit
    // ----------------------------------------------------------------------

    // ---
    // BEFORE ImportDeclaration
    // AFTER  PackageDeclaration
    //

    // @Override
    // public void visit(ImportDeclaration n, Void arg) {
    //     // called BEFORE PackageDeclaration
    //     String importName = n.getNameAsString();
    //
    //     if (n.isStatic()) return;
    //     if (n.isAsterisk()) {
    //         starImports.add(importName);
    //     }
    //     else {
    //         namedImports.put(JavaUtils.nameOf(importName), importName);
    //         qualifiedImports.add(importName);
    //     }
    //     logger.printf("imp: %s", n.getNameAsString());
    //
    //     super.visit(n, arg);
    // }

    // @Override
    // public void visit(PackageDeclaration n, Void arg) {
    //     // called AFTER the ImportDeclaration
    //     packageName = n.getNameAsString();
    //
    //     // classes in the same package
    //     starImports.add(packageName);
    //     // classes in "java.lang"
    //     starImports.add(JavaUtils.JAVA_LANG);
    //     // to support fully qualified classes
    //     starImports.add(JavaUtils.ROOT);
    //
    //     logger.printf("pkg: %s", packageName);
    //
    //     super.visit(n, arg);
    // }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // protected Optional<String> resolveType(String name, Node n) {
    //     if (!JavaUtils.isClassName(name))
    //         return Optional.empty();
    //     // if (tsx().isNamespace(name))
    //     //     return Optional.empty();
    //
    //     Optional<String> resolved = Optional.empty();
    //     if (!resolved.isPresent())
    //         resolved = resolveUsingNamedImports(name);
    //     if (!resolved.isPresent())
    //         resolved = resolveUsingStarImports(name);
    //     if (!resolved.isPresent())
    //         resolved = resolveUsingLocalClasses(name, n);
    //     // if (!resolved.isPresent())
    //     //     resolved = resolveUsingCurrentPackage(name);
    //     if (resolved.isPresent())
    //         return resolved;
    //
    //     unsolvedSymbols.unsolved(name, n, source);
    //
    //     return Optional.empty();
    // }

    // private Optional<String> resolveUsingNamedImports(String name) {
    //     if (namedImports.containsKey(name)) {
    //         String resolved = namedImports.get(name);
    //         resolvedSymbols.resolved(resolved);
    //         return Optional.of(namedImports.get(name));
    //     }
    //     return Optional.empty();
    // }

    // private Optional<String> resolveUsingStarImports(String name) {
    //     for(String namespace : starImports) {
    //         String qualifiedName = JavaUtils.fullName(namespace, name);
    //         SymbolReference<ResolvedReferenceTypeDeclaration>
    //             solved = ts.tryToSolveType(qualifiedName);
    //         if (solved.isSolved()) {
    //             resolvedSymbols.resolved(solved.getCorrespondingDeclaration());
    //             return Optional.of(solved.getCorrespondingDeclaration().getQualifiedName());
    //         }
    //     }
    //     return Optional.empty();
    // }

    // private Optional<String> resolveUsingLocalClasses(String name, Node n) {
    //     for(ClassOrInterfaceDeclaration c : cu.findAll(ClassOrInterfaceDeclaration.class))
    //         if (name.equals(c.getNameAsString()))
    //             return c.getFullyQualifiedName();
    //     for(EnumDeclaration e : cu.findAll(EnumDeclaration.class))
    //         if (name.equals(e.getNameAsString()))
    //             return e.getFullyQualifiedName();
    //     for(EnumConstantDeclaration c : cu.findAll(EnumConstantDeclaration.class))
    //         if (name.equals(c.getNameAsString()))
    //             return Optional.empty();
    //     for(AnnotationDeclaration a : cu.findAll(AnnotationDeclaration.class))
    //         if (name.equals(a.getNameAsString()))
    //             return Optional.empty();
    //     return Optional.empty();
    // }

    // private Optional<String> resolveUsingCurrentPackage(String name) {
    //     String qualifiedName = JavaUtils.fullName(packageName, name);
    //     SymbolReference<ResolvedReferenceTypeDeclaration>
    //         solved = ts.tryToSolveType(qualifiedName);
    //     if (solved.isSolved()) {
    //         resolvedSymbols.resolved(solved.getCorrespondingDeclaration());
    //         return Optional.of(solved.getCorrespondingDeclaration().getQualifiedName());
    //     }
    //     return Optional.empty();
    // }

    // protected Optional<String> resolveCallableDeclaration(String name, CallableDeclaration n) {
    //     logger.printf("   ?m: %s", name);
    //
    //     return Optional.empty();
    // }

    // protected Optional<String> resolveMethodCall(String name, MethodCallExpr n) {
    //     logger.printf("   ?mcall: %s", name);
    //
    //     return Optional.empty();
    // }

    // protected Optional<String> resolveTypeParameter(String name, TypeParameter n) {
    //     // logger.printf("   ?tpar: %s", name);
    //
    //     return Optional.empty();
    // }

    // protected Optional<String> resolveVarType(String name, VarType n) {
    //     logger.printf("   ?vtype: %s", name);
    //
    //     return Optional.empty();
    // }

    // protected Optional<String> resolveNameExpr(String name, NameExpr n) {
    //     // logger.printf("   ?mcall: %s", name);
    //     //
    //     // return Optional.empty();
    //     return resolveType(name, n);
    // }

    protected void resolveType(String symbol, ClassOrInterfaceType n) {
        Optional<ResolvedType> resolved = ctx.resolve(symbol, n);
        if (!resolved.isPresent())
            logger.error("Unsolved ClassOrInterfaceType: " + symbol);
    }

    protected void resolveType(String symbol, ArrayType n) {
        Optional<ResolvedType> resolved = ctx.resolve(symbol, n);
        if (!resolved.isPresent())
            logger.error("Unsolved ClassOrInterfaceType: " + symbol);
    }

    protected void resolveMethodCall(String symbol, MethodCallExpr n) {

    }

    protected void resolveVarType(String symbol, VarType n) {

    }

    protected void resolveType(String symbol, NameExpr n) {

    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
