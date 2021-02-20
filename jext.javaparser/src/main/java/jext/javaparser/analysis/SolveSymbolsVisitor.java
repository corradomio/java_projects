package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
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
            ResolvedReferenceTypeDeclaration rrtd = n.resolve();
            //System.out.println(rrtd.getQualifiedName());
        }
        catch (UnsolvedSymbolException | UnsupportedOperationException | NoSuchElementException e) {
            logger.error("ClassOrInterfaceDeclaration: " + e.toString() + " " + n.toString());
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
}
