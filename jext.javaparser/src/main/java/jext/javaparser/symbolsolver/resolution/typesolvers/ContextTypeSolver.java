package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.lang.JavaUtils;

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

    // private static Logger logsolver = Logger.getLogger(ContextTypeSolver.class);

    private static final String DEFAULT = "default";

    // private CompilationUnit cu;
    // private File filename;
    private final Map<String, String> imports = new HashMap<>();
    private final List<String> starImports = new ArrayList<>();
    private String namespace;
    private String cacheName;

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

    public ContextTypeSolver add(TypeSolver ts) {
        super.add(ts);
        return this;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // /**
    //  * Set the current package
    //  * @param n package declaration
    //  */
    // public ContextTypeSolver setPackage(PackageDeclaration n) {
    //     this.namespace = n.getNameAsString();
    //     return this;
    // }

    // /**
    //  * Add an import declaration
    //  * @param n import declaration
    //  */
    // public ContextTypeSolver addImport(ImportDeclaration n) {
    //     String importName = n.getNameAsString();
    //     if (n.isStatic()) {
    //         importName = JavaUtils.namespaceOf(importName);
    //         this.imports.put(JavaUtils.nameOf(importName), importName);
    //     }
    //     else if (n.isAsterisk()) {
    //         // '*' already stripped
    //         this.starImports.add(importName);
    //     }
    //     else {
    //         this.imports.put(JavaUtils.nameOf(importName), importName);
    //     }
    //     return this;
    // }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // /**
    //  * Add the default imports: the imports from the current package and
    //  * from the default package "java.lang". These must be the last imports
    //  * to add.
    //  */
    // private void addDefaultImports() {
    //     this.starImports.add(JavaUtils.JAVA_LANG);       // import java.lang.*
    //     this.starImports.add(this.namespace);  // import <currentPackage>.*
    //     this.starImports.add(JavaUtils.ROOT);    // import <rootPackage>.*
    // }

    // ----------------------------------------------------------------------
    // resolve type
    // ----------------------------------------------------------------------

    // /**
    //  * Try to solve the type using the standard library. If the library is not
    //  * able to solve it, tries a 'direct' method
    //  * @param n type to solve
    //  * @return a resolved type with full qualified name or null
    //  */
    // @Nullable
    // public ResolvedType resolve(Type n) {
    //     try {
    //         // setTimestamp();
    //
    //         ResolvedType rt;
    //         if (n.isPrimitiveType())
    //             rt = ResolvedPrimitiveType.byName(n.asPrimitiveType().getType().name());
    //         else if (n.isClassOrInterfaceType())
    //             rt = resolve(n.asClassOrInterfaceType().getNameAsString(), n);
    //         else
    //             rt = n.resolve();
    //
    //         return rt;
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", n.toString());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (RuntimeException e) {
    //
    //     }
    //     finally {
    //         // clearTimestamp();
    //     }
    //
    //     return null;
    // }

    // /**
    //  * Try to solve the name expression as a type using the standard library.
    //  * If the library is not able to solve it, tries a 'direct' method
    //  * @param n name to solve as type
    //  * @return a resolved type with full qualified name or null
    //  */
    // public ResolvedType resolve(NameExpr n) {
    //
    //     try {
    //         // setTimestamp();
    //
    //         // ResolvedValueDeclaration rvd = n.resolve();
    //         // return rvd.getType();
    //
    //         return super.resolve(n);
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", n.toString());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (UnsupportedOperationException | UnsolvedSymbolException | ResolveTimeoutException e) {
    //         //logger.errorf("Unable to resolve %s: %s (%s)", n.toString(), e.getClass().getName(), e.getMessage());
    //     }
    //     catch (RuntimeException e) {
    //
    //     }
    //     finally {
    //         // clearTimestamp();
    //     }
    //     return resolve(n.getName().asString(), n);
    // }

    // /**
    //  * Try to solve 'symbol' using 'context' as context inside the AST
    //  * @param symbol symbol to solve
    //  * @param context context to use
    //  * @return a resolved type with full qualified name or null
    //  */
    // @Nullable
    // public ResolvedType resolve(String symbol, Node context) {
    //
    //     String stripped = JavaUtils.toPlainSignature(symbol);
    //     SymbolReference<ResolvedReferenceTypeDeclaration> solved;
    //
    //     solved = tryToSolveUsingContext(stripped, context);
    //
    //     if (solved.isSolved()) {
    //         addSolved(solved.getCorrespondingDeclaration().getQualifiedName(), solved);
    //         return new ReferenceTypeImpl(solved.getCorrespondingDeclaration(), this);
    //     }
    //
    //     // unable to solve
    //     return null;
    // }

    // ----------------------------------------------------------------------
    // resolve method declaration
    // ----------------------------------------------------------------------

    // @Nullable
    // public ResolvedMethodDeclaration resolve(MethodCallExpr mce) {
    //     try {
    //         // setTimestamp();
    //
    //         // ResolvedMethodDeclaration rmd = mce.resolve();
    //         // return rmd;
    //
    //         return super.resolve(mce);
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", mce.toString());
    //     }
    //     catch (UnsolvedSymbolException | UnsupportedOperationException | ResolveTimeoutException
    //         | MethodAmbiguityException e) {
    //         //logger.errorf("Unable to resolve %s: %s (%s)", mce.toString(), e.getClass().getName(), e.getMessage());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (RuntimeException e) {
    //         String message = e.getMessage();
    //         if (message == null)
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(), mce.toString(), e.getMessage(), filename);
    //         else if (message.contains("cannot be resolved"))
    //             ;
    //         else if (message.contains("Unable to calculate"))
    //             ;
    //         else if (message.contains("Error calculating"))
    //             ;
    //         else
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(),  mce.toString(), e.getMessage(), filename);
    //     }
    //     // catch (Throwable t) {
    //     //     logger.errorf("[%s] %s: %s - %s", t.getClass().getSimpleName(), mce.toString(), t.getMessage(), filename);
    //     // }
    //     finally {
    //         // clearTimestamp();
    //     }
    //
    //     // If scope it is not present, this means that the class is the CURRENT class
    //     // (I hope!)
    //     if (!mce.getScope().isPresent())
    //         return null;
    //
    //     // Skip "NameExpr" nodes because their resolution requires A LOT of type
    //     Expression expr = mce.getScope().get();
    //     // if (expr instanceof NameExpr)
    //     //     return null;
    //
    //     try {
    //         // setTimestamp();
    //
    //         ResolvedType rt = expr.calculateResolvedType();
    //         String typeName = rt.describe();
    //
    //         int nArguments = mce.getArguments().size();
    //         String methodName = mce.getNameAsString();
    //
    //         return new ReferencedMethodDeclaration(typeName, methodName, nArguments);
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", expr.toString());
    //     }
    //     catch (UnsolvedSymbolException | UnsupportedOperationException | ResolveTimeoutException
    //         | MethodAmbiguityException e) {
    //         //logger.errorf("Unable to resolve %s: %s (%s)", expr.toString(), e.getClass().getName(), e.getMessage());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (RuntimeException e) {
    //         String message = e.getMessage();
    //         // Throwable cause = e.getCause();
    //         if (message == null)
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(), mce.toString(), e.getMessage(), filename);
    //         else if (message.contains("cannot be resolved"))
    //             ;
    //         else if (message.contains("Unable to calculate"))
    //             ;
    //         else if (message.contains("Error calculating"))
    //             ;
    //         else
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(),  mce.toString(), e.getMessage(), filename);
    //     }
    //     // catch (Throwable t) {
    //     //     logger.errorf("[%s] %s: %s - %s", t.getClass().getSimpleName(), mce.toString(), t.getMessage(), filename);
    //     // }
    //     finally {
    //         // clearTimestamp();
    //     }
    //
    //     logsolver.warnft("Unable to solve call %s", mce.toString());
    //
    //     return null;
    // }

    // @Nullable
    // public ResolvedConstructorDeclaration resolve(ObjectCreationExpr oce) {
    //     try {
    //         // setTimestamp();
    //
    //         // ResolvedConstructorDeclaration rcd = oce.resolve();
    //         // return rcd;
    //
    //         return super.resolve(oce);
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", oce.toString());
    //     }
    //     catch (UnsolvedSymbolException | UnsupportedOperationException | ResolveTimeoutException
    //         | MethodAmbiguityException | IllegalArgumentException | IndexOutOfBoundsException e) {
    //         //logger.errorf("Unable to resolve %s: %s (%s)", oce.toString(), e.getClass().getName(), e.getMessage());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (RuntimeException e) {
    //         String message = e.getMessage();
    //         if (message == null)
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(), oce.toString(), e.getMessage(), filename);
    //         else if (message.contains("cannot be resolved"))
    //             ;
    //         else if (message.contains("Unable to calculate"))
    //             ;
    //         else if (message.contains("Error calculating"))
    //             ;
    //         else
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(),  oce.toString(), e.getMessage(), filename);
    //     }
    //     // catch (Throwable t) {
    //     //     logger.errorf("[%s] %s: %s - %s", t.getClass().getSimpleName(), oce.toString(), t.getMessage(), filename);
    //     // }
    //     finally {
    //         // clearTimestamp();
    //     }
    //
    //     // If scope it is not present, this means that the class is the CURRENT class
    //     // (I hope!)
    //     if (!oce.getScope().isPresent())
    //         return null;
    //
    //     // Skip "NameExpr" nodes because their resolution requires A LOT of type
    //     Expression expr = oce.getScope().get();
    //     // if (expr instanceof NameExpr)
    //     //     return null;
    //
    //     try {
    //         // setTimestamp();
    //
    //         ResolvedType rt = expr.calculateResolvedType();
    //         String typeName = rt.describe();
    //
    //         int nArguments = oce.getArguments().size();
    //         String methodName = oce.getTypeAsString();
    //
    //         return new ReferenceConstructorDeclaration(typeName, methodName, nArguments);
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", expr.toString());
    //     }
    //     catch (UnsolvedSymbolException | UnsupportedOperationException | ResolveTimeoutException
    //         | MethodAmbiguityException e) {
    //         //logger.errorf("Unable to resolve %s: %s (%s)", oce.toString(), e.getClass().getName(), e.getMessage());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (RuntimeException e) {
    //         String message = e.getMessage();
    //         // Throwable cause = e.getCause();
    //         if (message == null)
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(), oce.toString(), e.getMessage(), filename);
    //         else if (message.contains("cannot be resolved"))
    //             ;
    //         else if (message.contains("Unable to calculate"))
    //             ;
    //         else if (message.contains("Error calculating"))
    //             ;
    //         else
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(),  oce.toString(), e.getMessage(), filename);
    //     }
    //     // catch (Throwable t) {
    //     //     logger.errorf("[%s] %s: %s - %s", t.getClass().getSimpleName(), oce.toString(), t.getMessage(), filename);
    //     // }
    //     finally {
    //         // clearTimestamp();
    //     }
    //
    //     logsolver.warnft("Unable to solve call %s", oce.toString());
    //
    //     return null;
    // }

    // @Nullable
    // public ResolvedMethodDeclaration resolve(MethodReferenceExpr mre) {
    //     try {
    //         // setTimestamp();
    //
    //         // ResolvedMethodDeclaration rmd =  mre.resolve();
    //         // return rmd;
    //
    //         return super.resolve(mre);
    //     }
    //     catch (StackOverflowError e) {
    //         logger.errorf("StackOverflow on %s", mre.toString());
    //     }
    //     catch (UnsolvedSymbolException | UnsupportedOperationException | ResolveTimeoutException
    //         | MethodAmbiguityException | IllegalArgumentException | IndexOutOfBoundsException e) {
    //         //logger.errorf("Unable to resolve %s: %s (%s)", mre.toString(), e.getClass().getName(), e.getMessage());
    //     }
    //     catch (OutOfMemoryError | ResolveAbortedException e) {
    //         throw e;
    //     }
    //     catch (RuntimeException e) {
    //         String message = e.getMessage();
    //         if (message == null)
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(), mre.toString(), e.getMessage(), filename);
    //         else if (message.contains("cannot be resolved"))
    //             ;
    //         else if (message.contains("Unable to calculate"))
    //             ;
    //         else if (message.contains("Error calculating"))
    //             ;
    //         else
    //             logger.errorf("[%s] %s: %s - %s", e.getClass().getSimpleName(),  mre.toString(), e.getMessage(), filename);
    //     }
    //     // catch (Throwable t) {
    //     //     logger.errorf("[%s] %s: %s - %s", t.getClass().getSimpleName(), mre.toString(), t.getMessage(), filename);
    //     // }
    //     finally {
    //         // clearTimestamp();
    //     }
    //
    //     logsolver.warnft("Unable to solve call %s", mre.toString());
    //
    //     return null;
    // }

    // ----------------------------------------------------------------------
    // tryToSolveType
    // ----------------------------------------------------------------------

    private Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
    getAlreadySolved() {
        String key = getName();
        Cache<String, Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>>
            cache = CacheManager.getCache(String.format("%s.alreadySolved", this.cacheName));
        Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
            alreadySolved = cache.get(key, () -> new HashMap<>());
        return alreadySolved;
    }

    /**
     * Try to resolve 'name', a 'full qualified symbol', using the registered solvers
     *
     * @param name full qualified symbol to resolve
     * @return a reference to the resolved symbol
     */
    @Override
    public SymbolReference<ResolvedReferenceTypeDeclaration> tryToSolveType(String name) {

        // 0) sometimes, the typesolver try to solve a generic type (with "<...>").
        // This step remove the "<...>"
        if (!JavaUtils.isPlainSignature(name))
            name = JavaUtils.toPlainSignature(name);

        // 1) if already solved
        Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
            alreadySolved = getAlreadySolved();
        if (alreadySolved.containsKey(name))
            return alreadySolved.get(name);

        // 2) skip if it is a namespace
        // if (this.nssolver.isNamespace(name))
        //     return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        // 3) try to solve using the standard methods
        SymbolReference<ResolvedReferenceTypeDeclaration> solved = tryToSolveUsingSolvers(name);

        // 4) it is not possible to solve the symbol using the context HERE
        //    because we must use NOT only the imports but also the definitions of
        //    the inner classes

        // 5) register the solved symbol AND the namespaces (IF solved)
        addSolved(name, solved);

        return solved;
    }

    private void addSolved(String name, SymbolReference<ResolvedReferenceTypeDeclaration> solved) {

        if (!solved.isSolved()) return;

        Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
            alreadySolved = getAlreadySolved();

        // register the solved symbol
        alreadySolved.put(name, solved);

        // register the namespaces
        // Warn: there is a problem with the 'inner classes' BUT, because it is not simple
        // to understand if a qualified name is a namespace OR a class, FOR NOW we suppose
        // that this approach is good enough
        // String namespace = JavaUtils.namespaceOf(name);
        // while(namespace.length() > 0) {
        //     if (namespaces.containsKey(namespace)) break;
        //     namespaces.put(namespace, new NamespaceDeclaration(namespace));
        //     namespace = JavaUtils.namespaceOf(namespace);
        // }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    // Default implementation using the registered typeSolvers
    private SymbolReference<ResolvedReferenceTypeDeclaration>
    tryToSolveUsingSolvers(String name) {
        SymbolReference<ResolvedReferenceTypeDeclaration> solved;

        // 0) check if it is a namespace -> skip
        // if (this.nssolver.isNamespace(name))
        //     return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        // 1) speedup: check if the symbol was already solved
        Map<String, SymbolReference<ResolvedReferenceTypeDeclaration>>
            alreadySolved = getAlreadySolved();
        if (alreadySolved.containsKey(name))
            return alreadySolved.get(name);

        // 2) check using the registered solvers
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
        // if (this.nssolver.isNamespace(name))
        //     return SymbolReference.unsolved(ResolvedReferenceTypeDeclaration.class);

        // 1) try to solve using the full imports
        if (imports.containsKey(name))
            return tryToSolveUsingSolvers(imports.get(name));

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
    // resolve method declaration
    // ----------------------------------------------------------------------

    public String getTypeAsString(String name) {
        if (imports.containsKey(name))
            return imports.get(name);
        if (!starImports.isEmpty())
            return String.format("%s.%s", starImports.toString(), name);
        return name;
    }

    // ----------------------------------------------------------------------
    // canSolve
    // ----------------------------------------------------------------------

    // private static class Timestamp {
    //     private long timestamp;
    //     private long timeout = 500;
    //     private AtomicInteger tsdepth = new AtomicInteger();
    //
    //     void set() {
    //         if (tsdepth.getAndIncrement() == 0)
    //             this.timestamp = System.currentTimeMillis();
    //     }
    //
    //     boolean timeout() {
    //         return (timestamp > 0) && ((System.currentTimeMillis()-timestamp) > timeout);
    //     }
    //
    //     void clear() {
    //         if (tsdepth.decrementAndGet() == 0)
    //             this.timestamp = 0;
    //     }
    // }
    //
    // private transient boolean aborted;
    // private Timestamp timestamp = new Timestamp();
    //
    // public void abort() {
    //     this.aborted = true;
    // }
    //
    // public void setTimestamp() {
    //     timestamp.set();
    // }
    //
    // public void clearTimestamp() {
    //     timestamp.clear();
    // }

}
