package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;

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

            // logger.printf("%s: %s", prefix, rdecl.getQualifiedName());
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

            // logger.printf("e: %s", rdecl.getQualifiedName());
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

            // logger.printf("a: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("AnnotationDeclaration: " + e.toString() + " " + n.toString());
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
