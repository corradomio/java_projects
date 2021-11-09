package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserEnumDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserInterfaceDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.model.typesystem.ReferenceTypeImpl;
import jext.javaparser.resolution.ReferencedTypeUse;
import jext.lang.JavaUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class ContextTypeSolver extends CompositeTypeSolver {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static final String DEFAULT = "default";

    private CompilationUnit cu;
    private final Map<String, String> namedImports = new HashMap<>();
    private final List<String> starImports = new ArrayList<>();
    private String namespace;
    private File file;

    // cache used to patch a problem with the parser that it is unable
    // to distinguish between a type and a namespace
    // private final Map<String, NamespaceDeclaration> namespaces = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ContextTypeSolver() {
        this(DEFAULT);
    }

    public ContextTypeSolver(String name) {
        super(name);
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public ContextTypeSolver setCu(CompilationUnit cu) {
        this.cu = cu;
        this.namespace = "";

        cu.getPackageDeclaration().ifPresent(this::setPackage);
        cu.findAll(ImportDeclaration.class).forEach(this::addImport);
        cu.getStorage().ifPresent(storage -> file = storage.getPath().toFile());
        addDefaultImports();

        return this;
    }

    private void setPackage(PackageDeclaration n) {
        this.namespace = n.getNameAsString();
    }

    private void addImport(ImportDeclaration n) {
        String qualifiedName = n.getNameAsString();
        if (n.isStatic()) {
            qualifiedName = JavaUtils.namespaceOf(qualifiedName);
            this.namedImports.put(JavaUtils.nameOf(qualifiedName), qualifiedName);
        }
        else if (n.isAsterisk()) {
            // '*' already stripped
            this.starImports.add(qualifiedName);
        }
        else {
            this.namedImports.put(JavaUtils.nameOf(qualifiedName), qualifiedName);
        }
    }

    /**
     * Add the default imports: the imports from the current package and
     * from the default package "java.lang". These must be the last imports
     * to add.
     */
    private void addDefaultImports() {
        if (!this.namespace.isEmpty())
            this.starImports.add(this.namespace);       // import <currentPackage>.*
        this.starImports.add(JavaUtils.JAVA_LANG);      // import java.lang.*
        this.starImports.add(JavaUtils.ROOT);           // import <rootPackage>.*
    }

    // ----------------------------------------------------------------------
    // resolve type
    // ----------------------------------------------------------------------

    public  Optional<ResolvedType> resolve(ClassOrInterfaceType n) {
        String name = n.getNameAsString();
        return resolve(name, n);
    }

    public  Optional<ResolvedType> resolve(NameExpr n) {
        String name = n.getNameAsString();
        return resolve(name, n);
    }

    public  Optional<ResolvedType> resolve(FieldAccessExpr n) {
        String name = n.toString();
        return resolve(name, n);
    }

    public  Optional<ResolvedType> resolve(String name, Node n) {
        if (!JavaUtils.isClassName(name) || JavaUtils.isConstant(name))
            return Optional.empty();

        Optional<ResolvedType> resolved = Optional.empty();
        if (!resolved.isPresent())
            resolved = resolveUsingNamedImports(name);
        if (!resolved.isPresent())
            resolved = resolveUsingStarImports(name);
        if (!resolved.isPresent())
            resolved = resolveUsingLocalClasses(name, n);
        if (!resolved.isPresent())
            resolved = resolveUsingCurrentPackage(name, n);
        if (resolved.isPresent())
            return resolved;
        else
            return Optional.empty();
    }

    // --

    private Optional<ResolvedType> resolveUsingNamedImports(String name) {
        if (namedImports.containsKey(name)) {
            String resolved = namedImports.get(name);
            return Optional.of(new ReferencedTypeUse(resolved));
        }
        return Optional.empty();
    }

    private Optional<ResolvedType> resolveUsingStarImports(String name) {
        for(String namespace : starImports) {
            String qualifiedName = JavaUtils.qualifiedName(namespace, name);
            SymbolReference<ResolvedReferenceTypeDeclaration>
                solved = getRoot().tryToSolveType(qualifiedName);
            if (solved.isSolved()) {
                // resolvedSymbols.resolved(solved.getCorrespondingDeclaration());
                return Optional.of(new ReferencedTypeUse(solved.getCorrespondingDeclaration().getQualifiedName()));
            }
        }
        return Optional.empty();
    }

    private Optional<ResolvedType> resolveUsingLocalClasses(String name, Node n) {
        try {
            for (ClassOrInterfaceDeclaration c : cu.findAll(ClassOrInterfaceDeclaration.class))
                if (c.isInterface() && name.equals(c.getNameAsString()))
                        return Optional.of(
                        ReferenceTypeImpl.undeterminedParameters(new JavaParserInterfaceDeclaration(c, this), this)
                        );
                else if (!c.isInterface() && name.equals(c.getNameAsString()))
                        return Optional.of(
                        ReferenceTypeImpl.undeterminedParameters(new JavaParserClassDeclaration(c, this), this)
                        );
                    // try {
                    //     return Optional.of(
                    //         ReferenceTypeImpl.undeterminedParameters(new JavaParserClassDeclaration(c, this), this)
                    //     );
                    // }
                    // catch (IllegalArgumentException e) {
                    //     return Optional.of(
                    //         ReferenceTypeImpl.undeterminedParameters(new JavaParserInterfaceDeclaration(c, this), this)
                    //     );
                    // }
            for (EnumDeclaration e : cu.findAll(EnumDeclaration.class))
                if (name.equals(e.getNameAsString()))
                    return Optional.of(
                        ReferenceTypeImpl.undeterminedParameters(new JavaParserEnumDeclaration(e, this), this)
                    );
        }
        catch (IllegalArgumentException e) {
            logger.errorf("IllegalArgumentException: %s, symbol: %s, node: %s\n %s", e.getMessage(), name, n.toString(),
                file);
        }
        return Optional.empty();
    }

    private Optional<ResolvedType> resolveUsingCurrentPackage(String name, Node n) {
        return Optional.empty();
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    /**
     * Try to resolve 'name', a 'full qualified symbol', using the registered solvers
     *
     * @param name full qualified symbol to resolve
     * @return a reference to the resolved symbol
     */
    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        // 0) sometimes, the typesolver try to solve a generic type (with "<...>").
        // This step remove the "<...>"
        if (!JavaUtils.isPlainSignature(name))
            name = JavaUtils.toPlainSignature(name);

        // 1) try to solve using the standard methods
        solved = tryToSolveUsingSolvers(name);
        if (solved.isSolved()) {
            return solved;
        }

        // 2) try to solve using context
        solved = tryToSolveUsingContext(name, this.cu);
        if (solved.isSolved()) {
            return solved;
        }

        // 3) it is not possible to solve the symbol using the context HERE
        //    because we must use NOT only the imports but also the definitions of
        //    the inner classes

        return solved;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    // Default implementation using the registered typeSolvers
    private SymbolReference<ResolvedReferenceTypeDeclaration>
    tryToSolveUsingSolvers(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        // 2) check using the registered solvers
        for(TypeSolver typeSolver : elements) {
            solved = typeSolver.tryToSolveType(name);
            if (solved.isSolved())
                return solved;
        }

        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

    // Resolution using the current context
    private SymbolReference<ResolvedReferenceTypeDeclaration>
    tryToSolveUsingContext(String name, Node context) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        // 1) try to solve using the full imports
        if (namedImports.containsKey(name))
            return tryToSolveUsingSolvers(namedImports.get(name));

        // 2) try to resolve using startImports (it contains also the current package
        //    AND "java.lang")
        for(String namespace : starImports) {
            solved = tryToSolveUsingSolvers(JavaUtils.qualifiedName(namespace, name));
            if (solved.isSolved())
                return solved;
        }

        // 3) try to resolve using current namespaces stack (inner classes)
        //    TODO: this part MUST BE IMPROVED
        Stack<String> contextStack = getContextStack(context);
        for(String namespace : contextStack) {
            solved = tryToSolveUsingSolvers(JavaUtils.qualifiedName(namespace, name));
            if (solved.isSolved())
                return solved;
        }

        // 4) none to do
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

    // Context stack
    public static Stack<String> getContextStack(Node n) {
        Stack<String> context = new Stack<>();

        if (n == null)
            return context;

        Optional<Node> c = n.getParentNode();
        while (c.isPresent()) {
            n = c.get();
            if (n.getClass().equals(ClassOrInterfaceDeclaration.class)) {
                ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration)n;
                cid.getFullyQualifiedName().ifPresent(context::push);
            }
            c = n.getParentNode();
        }

        return context;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
