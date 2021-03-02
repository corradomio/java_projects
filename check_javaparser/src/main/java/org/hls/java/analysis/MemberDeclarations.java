package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExtWrapper;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverWithResolve;
import jext.lang.JavaUtils;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MemberDeclarations extends BaseVoidVisitorAdapter {

    private static final int NAME_LENGTH = 16;

    private File source;

    private String packageName;
    private Map<String, String> namedImports = new HashMap<>();
    private List<String> starImports = new ArrayList<>();

    public void analyze(CompilationUnit cu, TypeSolver ts) {
        cu.getStorage().ifPresent(storage ->
            source = storage.getPath().toFile());

        if (ts instanceof TypeSolverWithResolve)
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
    // BEFORE ImportDeclaration
    // AFTER  PackageDeclaration
    //

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
        // logger.printf("imp: %s", n.getNameAsString());
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

        // logger.printf("pkg: %s", packageName);
        super.visit(n, arg);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    //
    //  BodyDeclaration
    //      CallableDeclaration
    //          ConstructorDeclaration
    //          MethodDeclaration
    //      TypeDeclaration
    //          EnumDeclaration
    //          AnnotationDeclaration
    //          ClassOrInterfaceDeclaration
    //      InitializerDeclaration
    //      FieldDeclaration
    //      EnumConstantDeclaration
    //      AnnotationMemberDeclaration.

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(ConstructorDeclaration n) {
        try {
            ResolvedConstructorDeclaration rdecl = n.resolve();
            logger.printf("   m: %s", rdecl.getQualifiedName());

            for(int i=0; i<rdecl.getNumberOfParams(); ++i) {
                ResolvedParameterDeclaration rparam = rdecl.getParam(i);
            }
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.getDeclarationAsString();
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
            logger.printf("   m: %s (%s)", rdecl.getQualifiedName(), rdecl.getSignature());

            ResolvedType rtype = rdecl.getReturnType();
            for(int i=0; i<rdecl.getNumberOfParams(); ++i) {
                ResolvedParameterDeclaration rparam = rdecl.getParam(i);
            }
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.getDeclarationAsString();
            logger.error("MethodDeclaration: " + e.toString() + " " + symbol);
        }
    }

    // ----------------------------------------------------------------------

    @Override
    public void visit(FieldDeclaration n, Void args) {
        try {
            ResolvedFieldDeclaration rdecl = n.resolve();
            logger.printf("   f: %s", rdecl.getName());

        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.error("MethodDeclaration: " + e.toString() + " " + symbol);
        }
    }

}
