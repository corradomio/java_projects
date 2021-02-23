package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.resolution.types.ResolvedArrayType;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.resolution.ReferencedTypeDeclaration;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExt;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExtWrapper;
import jext.javaparser.util.JPUtils;
import jext.lang.JavaUtils;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class SolveSymbolsVisitor extends BaseVoidVisitorAdapter {

    private static final int NAME_LENGTH = 16;

    private static Set<String> unsolved = new ConcurrentSkipListSet<>();
    private File source;

    private String packageName;
    private Map<String, String> namedImports = new HashMap<>();
    private List<String> starImports = new ArrayList<>();


    public SolveSymbolsVisitor() {

    }


    public void analyze(CompilationUnit cu, TypeSolver ts) {
        cu.getStorage().ifPresent(storage ->
            source = storage.getPath().toFile());

        if (ts instanceof TypeSolverExt)
            this.ts = ts;
        else
            this.ts = new TypeSolverExtWrapper(ts);

        logger = Logger.getLogger(loggerName());

        super.analyze(cu);
    }

    private String loggerName() {
        String name = FileUtils.getNameWithoutExt(source);
        if (name.length() > NAME_LENGTH)
            name = name.substring(0, NAME_LENGTH);
        while (name.length() < NAME_LENGTH)
            name += " ";
        return name;
    }

    // ---

    @Override
    public void visit(ImportDeclaration n, Void arg) {
        // called BEFORE PackageDeclaration
        String importName = n.getNameAsString();

        if (n.isStatic()) return;
        if (n.isAsterisk()) {
            starImports.add(importName);
        }
        else {
            namedImports.put(JavaUtils.nameOf(importName), importName);
        }
        logger.printf("imp: %s", n.getNameAsString());
        super.visit(n, arg);
    }

    @Override
    public void visit(PackageDeclaration n, Void arg) {
        // called AFTER the ImportDeclaration
        packageName = n.getNameAsString();

        // classes in the same package
        starImports.add(packageName);
        // classes in "java.lang"
        starImports.add(JavaUtils.JAVA_LANG);
        // to support fully qualified classes
        starImports.add(JavaUtils.ROOT);

        logger.printf("pkg: %s", packageName);
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(ClassOrInterfaceDeclaration n) {
        try {
            ResolvedReferenceTypeDeclaration rdecl = n.resolve();
            System.out.printf("c: %s", rdecl.getQualifiedName());

            n.getImplementedTypes().forEach(this::resolve);
            n.getExtendedTypes().forEach(this::resolve);
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("ClassOrInterfaceDeclaration: " + e.toString() + " " + n.toString());
        }
    }

    private void resolve(ClassOrInterfaceType n) {
        try {
            ResolvedReferenceType rdecl = n.resolve();
            System.out.printf("c: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("ClassOrInterfaceDeclaration: " + e.toString() + " " + n.toString());
        }
    }

    @Override
    public void visit(EnumDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(EnumDeclaration n) {
        try {
            ResolvedReferenceTypeDeclaration rdecl = n.resolve();
            System.out.printf("e: %s", rdecl.getQualifiedName());

            n.getImplementedTypes().forEach(this::resolve);
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("EnumDeclaration: " + e.toString() + " " + n.toString());
        }
    }

    @Override
    public void visit(AnnotationDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(AnnotationDeclaration n) {
        try {
            ResolvedReferenceTypeDeclaration rdecl = n.resolve();
            System.out.printf("a: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("AnnotationDeclaration: " + e.toString() + " " + n.toString());
        }
    }


    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(ConstructorDeclaration n) {
        try {
            ResolvedConstructorDeclaration rdecl = n.resolve();
            System.out.printf("   m: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.error("ConstructorDeclaration: " + e.toString() + " " + symbol);
        }
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(MethodDeclaration n) {
        try {
            ResolvedMethodDeclaration rdecl = n.resolve();
            logger.printf("   m: %s", rdecl.getQualifiedName());

            ResolvedType rtype = rdecl.getReturnType();
            for(int i=0; i<rdecl.getNumberOfParams(); ++i) {
                ResolvedParameterDeclaration rparam = rdecl.getParam(i);
            }
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.error("MethodDeclaration: " + e.toString() + " " + symbol);
        }
    }

    @Override
    public void visit(TypeParameter n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }
    private void resolve(TypeParameter n) {
        try {
            ResolvedTypeVariable rtype = n.resolve();
            logger.printf("t: %s", rtype.describe());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            // logger.error("TypeParameter: " + e.toString() + " " + n.toString());
        }
    }

    @Override
    public void visit(Parameter n, Void arg) {
        super.visit(n, arg);
    }

    // --

    @Override
    public void visit(ClassExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(MethodCallExpr n) {
        try {
            ResolvedMethodDeclaration rdecl = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.getNameAsString();
            // logger.errorf("MethodCallExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        catch (RuntimeException | StackOverflowError e) {
            String symbol = n.getNameAsString();
            // logger.errorf("MethodCallExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
    }

    // ---

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        try {
            if (tsx().isNamespace(n.toString())) return;
            ResolvedReferenceType rrt = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            if (JPUtils.isTypeParameter(n)) return;
            String symbol = n.getNameAsString();
            logger.errorf("ClassOrInterfaceType: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayType n, Void arg) {
        try {
            if (tsx().isNamespace(n.toString())) return;
            ResolvedArrayType rrt = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.errorf("ArrayType: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(VarType n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(VarType n) {
        try {
            ResolvedType rrt = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.errorf("VarType: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }

    }

    // ---

    @Override
    public void visit(NameExpr n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(NameExpr n) {
        try {
            ResolvedValueDeclaration rdecl = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.errorf("NameExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        catch (RuntimeException e) {
            String symbol = n.toString();
            logger.errorf("NameExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
    }

    private ResolvedDeclaration resolveByContext(String name) {
        if (namedImports.containsKey(name))
            return new ReferencedTypeDeclaration(namedImports.get(name));
    }

}
