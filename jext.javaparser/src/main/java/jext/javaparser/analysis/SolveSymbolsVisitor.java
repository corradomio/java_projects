package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
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
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.resolution.types.ResolvedArrayType;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.util.JPUtils;

import java.util.NoSuchElementException;

public class SolveSymbolsVisitor extends ContextVisitorAdapter {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SolveSymbolsVisitor() {

    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void analyze(CompilationUnit cu, TypeSolver ts) {
        super.analyze(cu, ts);
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
            logger.printf("c: %s", rdecl.getQualifiedName());

            n.getImplementedTypes().forEach(this::resolve);
            n.getExtendedTypes().forEach(this::resolve);
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.error("ClassOrInterfaceDeclaration: " + e.toString() + " " + symbol);
        }
    }

    private void resolve(ClassOrInterfaceType n) {
        try {
            ResolvedReferenceType rdecl = n.resolve();
            logger.printf("c: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.error("ClassOrInterfaceDeclaration: " + e.toString() + " " + symbol);
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
            logger.printf("e: %s", rdecl.getQualifiedName());

            n.getImplementedTypes().forEach(this::resolve);
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.getNameAsString();
            super.resolveType(symbol, n);
            // logger.error("EnumDeclaration: " + e.toString() + " " + symbol);
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
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.error("AnnotationDeclaration: " + e.toString() + " " + symbol);
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
            logger.printf("   m: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.error("ConstructorDeclaration: " + e.toString() + " " + symbol);
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
            String symbol = n.getNameAsString();
            super.resolveType(symbol, n);
            // logger.error("MethodDeclaration: " + e.toString() + " " + symbol);
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
            String symbol = n.toString();
            // super.resolveType(symbol, n);
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
            logger.printf("   call: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.getNameAsString();
            super.resolveType(symbol, n);
            // logger.errorf("MethodCallExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        catch (RuntimeException | StackOverflowError e) {
            String symbol = n.getNameAsString();
            super.resolveType(symbol, n);
            // logger.errorf("MethodCallExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
    }

    // ---

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        try {
            if (tsx().isNamespace(n.toString())) return;
            ResolvedReferenceType rdecl = n.resolve();
            logger.printf("   ctype: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            if (JPUtils.isTypeParameter(n)) return;
            String symbol = n.getNameAsString();
            super.resolveType(symbol, n);
            // logger.errorf("ClassOrInterfaceType: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayType n, Void arg) {
        try {
            if (tsx().isNamespace(n.toString())) return;
            ResolvedArrayType rdecl = n.resolve();
            logger.printf("   ctype: %s", rdecl.getComponentType().describe());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.errorf("ArrayType: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
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
            ResolvedType rdecl = n.resolve();
            logger.printf("   vtype: %s", rdecl.describe());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.errorf("VarType: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }

    }

    // ---

    @Override
    public void visit(NameExpr n, Void arg) {
        resolve(n);
        super.visit(n, arg);
    }

    private void resolve(NameExpr n) {
        RuntimeException ex = null;

        try {
            ResolvedValueDeclaration rdecl = n.resolve();
            logger.printf("   nexpr: %s", rdecl.getType().describe());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.errorf("NameExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }
        catch (RuntimeException e) {
            String symbol = n.toString();
            super.resolveType(symbol, n);
            // logger.errorf("NameExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        }

        // if (ex != null)
        // try {
        //     resolveByContext(n.getNameAsString(), ex);
        // }
        // catch (UnsolvedSymbolException e) {
        //     String symbol = n.toString();
        //     super.resolveType(symbol, n);
        //     logger.errorf("NameExpr: %s %s\n%s", e.toString(), symbol, source.getAbsolutePath());
        // }
    }

    // private ResolvedDeclaration resolveByContext(String name, RuntimeException e) {
    //     if (JavaUtils.isIdentifier(name))
    //         throw e;
    //
    //     if (namedImports.containsKey(name))
    //         return new ReferencedTypeDeclaration(namedImports.get(name));
    //
    //     for (String namespace : starImports) {
    //         String fullName = JavaUtils.qualifiedName(namespace, name);
    //         SymbolReference<ResolvedReferenceTypeDeclaration> solved = ts.getRoot().tryToSolveType(fullName);
    //         if (solved.isSolved())
    //             return solved.getCorrespondingDeclaration();
    //     }
    //
    //     throw new  UnsolvedSymbolException(name);
    // }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
