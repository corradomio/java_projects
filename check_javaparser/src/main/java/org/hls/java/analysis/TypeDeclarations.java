package org.hls.java.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.analysis.BaseVoidVisitorAdapter;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExtWrapper;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverWithResolve;
import jext.javaparser.util.JPUtils;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.NoSuchElementException;

public class TypeDeclarations extends BaseVoidVisitorAdapter {

    private static final int NAME_LENGTH = 16;

    private File source;

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
            ResolvedReferenceTypeDeclaration rdecl = n.resolve();
            if (JPUtils.isInnerClass(rdecl))
                logger.warnf("c: %s", rdecl.getQualifiedName());
            else
                logger.printf("c: %s", rdecl.getQualifiedName());

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
            if (JPUtils.isInnerClass(rdecl))
                logger.warnf("e: %s", rdecl.getQualifiedName());
            else
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
            if (JPUtils.isInnerClass(rdecl))
                logger.warnf("a: %s", rdecl.getQualifiedName());
            else
                logger.printf("a: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("AnnotationDeclaration: " + e.toString() + " " + n.toString());
        }
    }

}
