package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.analysis.ContextVisitorAdapter;
import jext.javaparser.util.JPUtils;

import java.util.NoSuchElementException;

public class TypeDeclarations extends ContextVisitorAdapter {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public TypeDeclarations() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public BaseVoidVisitorAdapter analyze(CompilationUnit cu, TypeSolver ts) {
        return super.analyze(cu, ts);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(ClassOrInterfaceDeclaration n) {
        try {
            String prefix = "c";
            ResolvedReferenceTypeDeclaration rdecl = n.resolve();
            if (rdecl.isInterface())
                prefix = "i";
            else if (rdecl.isEnum())
                prefix = "e";
            else
                prefix = "c";

            // if (JPUtils.isInnerClass(rdecl))
            //     logger.warnf("%s: %s (inner)", prefix, rdecl.getQualifiedName());
            // else
                logger.printf("%s: %s", prefix, rdecl.getQualifiedName());

            // n.getImplementedTypes().forEach(this::resolve);
            // n.getExtendedTypes().forEach(this::resolve);
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
            // if (JPUtils.isInnerClass(rdecl))
            //     logger.warnf("e: %s", rdecl.getQualifiedName());
            // else
                logger.printf("e: %s", rdecl.getQualifiedName());

            // n.getImplementedTypes().forEach(this::resolve);
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
            // if (JPUtils.isInnerClass(rdecl))
            //     logger.warnf("a: %s", rdecl.getQualifiedName());
            // else
                logger.printf("a: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("AnnotationDeclaration: " + e.toString() + " " + n.toString());
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
