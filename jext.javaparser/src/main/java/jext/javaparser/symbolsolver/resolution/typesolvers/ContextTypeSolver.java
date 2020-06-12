package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.model.typesystem.ReferenceTypeImpl;
import jext.javaparser.symbolsolver.namespacemodel.NamespaceDeclaration;
import jext.util.JavaUtils;
import jext.util.PathUtils;

import javax.annotation.Nullable;
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

    private static final String JAVA_LANG = "java.lang";

    private CompilationUnit cu;
    private final Map<String, String> imports = new HashMap<>();
    private final List<String> starImports = new ArrayList<>();
    private String namespace;

    // cache used to speedup the resolver algorithm
    private final Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
        alreadySolved = new HashMap<>();
    // cache used to patch a problem with the parser that it is unable
    // to distinguish between a type and a namespace
    private final Map<String, NamespaceDeclaration>
        namespaces = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ContextTypeSolver() {
        this("Root");
    }

    public ContextTypeSolver(String name) {
        super(name);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Set the current package
     * @param n package declaration
     */
    public ContextTypeSolver setPackage(PackageDeclaration n) {
        this.namespace = n.getNameAsString();
        return this;
    }

    /**
     * Add an import declaration
     * @param n import declaration
     */
    public ContextTypeSolver addImport(ImportDeclaration n) {
        String importName = n.getNameAsString();
        if (n.isAsterisk())
            this.starImports.add(this.starImports.size(), namespaceOf(importName));
        if (!n.isStatic())
            this.imports.put(nameOf(importName), importName);
        return this;
    }

    /**
     * Add the default imports: the imports from the current package and
     * from the default package "java.lang". These must be the last imports
     * to add.
     */
    public ContextTypeSolver addDefaultImports() {
        this.starImports.add(this.starImports.size(), JAVA_LANG);
        this.starImports.add(this.starImports.size(), this.namespace);
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Set the compilationUnit used to resolve symbols.
     * The typeSolver is assigned to the cu
     * @param cu compilationUnit to use
     */
    public ContextTypeSolver setCu(CompilationUnit cu) {
        this.cu = cu;
        cu.getStorage().ifPresent(storage -> {
            this.name = PathUtils.getNameWithoutExt(storage.getFileName());
        });
        setSymbolResolver(cu);
        setContext();
        return this;
    }

    /**
     * Detach the typeSolver from the compilationUnit
     */
    public void detach() {
        cu.removeData(Node.SYMBOL_RESOLVER_KEY);
    }

    private void setSymbolResolver(CompilationUnit cu) {
        // Inject inside cu THIS typeSolver
        SymbolResolver symbolResolver = new JavaSymbolSolver(this);
        cu.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);
    }

    private void setContext() {
        // Set the context used for the symbol resolution:
        // 1) current package
        // 2) imports
        // 3) default imports
        if (namespace != null)
            return;
        if (cu == null)
            throw new IllegalStateException("Missing CompilationUnit. Use 'setCu(cu)");

        namespace = "";
        cu.getPackageDeclaration().ifPresent(this::setPackage);
        cu.getImports().forEach(this::addImport);
        addDefaultImports();
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    @Override
    public ContextTypeSolver add(TypeSolver typeSolver) {
        // used to ensure the correct result type (THIS class)
        super.add(typeSolver);
        return this;
    }

    // ----------------------------------------------------------------------
    // resolve
    // ----------------------------------------------------------------------

    /**
     * Try to solve the type using the standard library. If the library is not
     * able to solve it, it is tried a 'direct' method
     * @param n type to solve
     * @return a resolved type with full qualified name or null
     */
    @Nullable
    public ResolvedType resolve(Type n) {
        try {
            return n.resolve();
        }
        catch(UnsolvedSymbolException | UnsupportedOperationException e) {

        }
        return resolve(n.toString(), n);
    }

    /**
     * Try to solve the name expression as a type using the standard library.
     * If the library is not able to solve it, it is tried a 'direct' method
     * @param n name to solve as type
     * @return a resolved type with full qualified name or null
     */
    @Nullable
    public ResolvedType resolve(NameExpr n) {
        try {
            ResolvedValueDeclaration rvd = n.resolve();
            return rvd.getType();
        }
        catch(UnsolvedSymbolException | UnsupportedOperationException e) {

        }
        return resolve(n.getName().asString(), n);
    }

    /**
     * Try to solve 'symbol' using 'context' as context inside the AST
     * @param symbol symbol to solve
     * @param context context to use
     * @return a resolved type with full qualified name or null
     */
    @Nullable
    public ResolvedType resolve(String symbol, Node context) {
        setContext();

        String stripped = strip(symbol);
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        solved = tryToSolveUsingContext(stripped, context);

        if (solved.isSolved()) {
            addSolved(solved.getCorrespondingDeclaration().getQualifiedName(), solved);
            return new ReferenceTypeImpl(solved.getCorrespondingDeclaration(), this);
        }

        // unable to solve
        return null;
    }

    /**
     * Check is the symbol is a 'package name'.
     * This method is used to speedup the resolution of a symbol when it is sure that
     * the symbol 'can be' a namespace.
     *
     * The 'namespaces' is filled ONLY if there are resolved symbols.
     *
     * @param symbol symbol to check
     * @return is the symbol is a 'package name'
     */
    public boolean isNamespace(String symbol) {
        return namespaces.containsKey(symbol);
    }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    /**
     * Try to resolve 'name, a 'full qualified symbol', using the registered solvers
     *
     * @param name full qualified symbol to resolve
     * @return a reference to the resolved symbol
     */
    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {

        // 0) if already solved
        if (alreadySolved.containsKey(name))
            return alreadySolved.get(name);

        // 1) skip it is a namespace
        if (namespaces.containsKey(name))
            return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        // 2) try to solve using the standard methods
        SymbolReference<ResolvedReferenceTypeDeclaration> solved = tryToSolveUsingSolvers(name);

        // 3) it is not possible to solve the symbol using the context HERE
        //    because we must use NOT only the imports but also the definitions of
        //    the inner classes

        // 4) register the solved symbol AND the namespaces
        addSolved(name, solved);

        return solved;
    }

    private void addSolved(String name, SymbolReference<ResolvedReferenceTypeDeclaration> solved) {
        if (!solved.isSolved()) return;
        if (alreadySolved.containsKey(name)) return;

        alreadySolved.put(name, solved);

        String namespace = JavaUtils.namespaceOf(name);
        while(namespace.length() > 0) {
            if (namespaces.containsKey(namespace)) break;
            namespaces.put(namespace, new NamespaceDeclaration(namespace));
            namespace = JavaUtils.namespaceOf(namespace);
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    // Default implementation using the registered typeSolvers
    private SymbolReference<ResolvedReferenceTypeDeclaration>
    tryToSolveUsingSolvers(String namespace, String name) {
        return tryToSolveUsingSolvers(String.format("%s.%s", namespace, name));
    }

    // Default implementation using the registered typeSolvers
    private SymbolReference<ResolvedReferenceTypeDeclaration>
    tryToSolveUsingSolvers(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        if (namespaces.containsKey(name))
            return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        if (alreadySolved.containsKey(name))
            return alreadySolved.get(name);

        // first, check standard solvers
        for(TypeSolver typeSolver : elements) {
            solved = typeSolver.tryToSolveType(name);
            if (solved.isSolved())
                return solved;
        }
        return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);
    }

    // ----------------------------------------------------------------------

    // Resolution using the current context
    private SymbolReference<ResolvedReferenceTypeDeclaration>
    tryToSolveUsingContext(String name, Node context) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        // 0) check if it is a namespace -> skip
        if (namespaces.containsKey(name))
            return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        // 1) try to solve using the full imports
        if (imports.containsKey(name))
            return tryToSolveUsingSolvers(imports.get(name));
            // return SymbolReference.solved(new SymbolOnlyDeclaration(imports.get(name)));

        // 2) try to resolve using startImports (it contains also the current package
        //    AND "java.lang")
        for(String namespace : starImports) {
            solved = tryToSolveUsingSolvers(namespace, name);
            if (solved.isSolved())
                return solved;
        }

        // 3) try to resolve using current namespaces stack (inner classes)
        Stack<String> contextStack = getContextStack(context);
        for(String namespace : contextStack) {
            solved = tryToSolveUsingSolvers(namespace, name);
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

    private static String namespaceOf(String fullName) {
        int pos = fullName.lastIndexOf('.');
        return pos > 0 ? fullName.substring(0, pos) : "";
    }

    private static String nameOf(String fullName) {
        int pos = fullName.lastIndexOf('.');
        return fullName.substring(pos+1);
    }

    private static String strip(String signature) {
        if (signature.contains("<"))
            signature = signature.substring(0, signature.indexOf('<'));
        if (signature.contains("["))
            signature = signature.substring(0, signature.indexOf('['));
        return signature;
    }

}
