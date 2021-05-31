package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
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
import jext.javaparser.analysis.ContextVisitorAdapter;

import java.util.NoSuchElementException;

public class MemberDeclarations extends ContextVisitorAdapter {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MemberDeclarations() {

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

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
