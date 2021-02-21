package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.ContextTypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExt;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverExtWrapper;
import jext.javaparser.util.JPUtils;

import java.io.File;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class SolveSymbolsVisitor extends BaseVoidVisitorAdapter {

    private static Set<String> unsolved = new ConcurrentSkipListSet<>();
    private File source;
    private String namespace;

    public void analyze(CompilationUnit cu, TypeSolver ts) {
        this.cu = cu;
        if (ts instanceof TypeSolverExt)
            this.ts = (TypeSolverExt) ts;
        else
            this.ts = new TypeSolverExtWrapper(ts);

        try {
            attach();
            super.analyze(cu);
        }
        finally {
            detach();
        }
    }

    private void attach() {
        SymbolResolver symbolResolver = new JavaSymbolSolver(ts);
        cu.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);
        cu.getStorage().ifPresent(storage -> source = storage.getPath().toFile());
    }

    private void detach() {
        cu.removeData(Node.SYMBOL_RESOLVER_KEY);
        JPUtils.removeTypeSolver(ts);
    }

    @Override
    public void visit(PackageDeclaration n, Void arg) {
        try {
            namespace = n.getNameAsString();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {

        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ImportDeclaration n, Void arg) {
        if (n.isAsterisk()) {

        }
        else if (n.isStatic()) {

        }
        else {

        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        try {
            if (ts.isNamespace(n.toString())) return;
            ResolvedReferenceType rrt = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            // e.printStackTrace();
            String symbol = n.toString();
            if (!unsolved.contains(symbol) && !JPUtils.isTypeParameter(n) && !JPUtils.isMethodReferenceExpr(n)) {
                try { n.resolve(); } catch(Exception t){ }
                logger.error("ClassOrInterfaceType: " + e.toString() + " " + symbol);
                logger.error("... " + source.getAbsolutePath());
                unsolved.add(symbol);
            }
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        try {
            ResolvedReferenceTypeDeclaration rdecl = n.resolve();
            logger.printf("c: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("ClassOrInterfaceDeclaration: " + e.toString() + " " + n.toString());
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        try {
            ResolvedConstructorDeclaration rdecl = n.resolve();
            logger.printf("   m: %s", rdecl.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.toString();
            logger.error("MethodDeclaration: " + e.toString() + " " + symbol);
        }
        super.visit(n, arg);
    }
    @Override
    public void visit(MethodDeclaration n, Void arg) {
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
        super.visit(n, arg);
    }

    @Override
    public void visit(TypeParameter n, Void arg) {
        try {
            // ResolvedTypeVariable rrt = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("TypeParameter: " + e.toString() + " " + n.toString());
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(Parameter n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VarType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        try {
            ResolvedMethodDeclaration rdecl = n.resolve();
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            String symbol = n.getNameAsString();
            logger.errorf("MethodCallExpr: %s %s\n%s", e.toString(), symbol,
                source.getAbsolutePath());
        }
        catch (RuntimeException | StackOverflowError e) {
            String symbol = n.getNameAsString();
            logger.errorf("MethodCallExpr: %s %s\n%s", e.toString(), symbol,
                source.getAbsolutePath());
        }
        super.visit(n, arg);
    }

}
