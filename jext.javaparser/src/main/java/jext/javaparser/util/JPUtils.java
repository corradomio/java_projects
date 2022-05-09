package jext.javaparser.util;

import com.github.javaparser.ParseResult;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodLikeDeclaration;
import com.github.javaparser.resolution.types.ResolvedIntersectionType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedUnionType;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.cache.Cache;
import jext.javaparser.exception.ResolveTimeoutException;
import jext.javaparser.symbolsolver.resolution.typesolvers.BaseTypeSolver;
import jext.java.JavaUtils;
import jext.util.Assert;
import jext.util.PropertiesUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class JPUtils {

    // private static Logger logger = Logger.getLogger(JPUtils.class);
    // private static final Class<?> INITIALIZER_DECLARATION = InitializerDeclaration.class;
    // private static final Class<?> METHOD_DECLARATION = MethodDeclaration.class;
    // private static final Class<?> CONSTRUCTOR_DECLARATION = ConstructorDeclaration.class;
    // private static final Class<?> OBJECT_CREATION_EXPR = ObjectCreationExpr.class;
    // private static final Class<?> CLASS_OR_INTERFACE = ObjectCreationExpr.class;

    // public static void setSymbolSolver(CompilationUnit cu, TypeSolver ts) {
    //     SymbolResolver symbolResolver = new JavaSymbolSolver(ts);
    //     cu.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);
    // }

    public static ClassOrInterfaceDeclaration findMainType(List<ClassOrInterfaceDeclaration> cids) {
        for (ClassOrInterfaceDeclaration cid : cids) {

            if (cid.isInnerClass() || cid.isLocalClassDeclaration() || !cid.isPublic())
                continue;
            return cid;
        }
        return cids.get(0);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // public static ResolvedType getDeclaringType(FieldDeclaration fdecl) {
    //     return declaringTypeOf(fdecl);
    // }
    // public static ResolvedType getDeclaringType(MethodDeclaration mdecl) {
    //     return declaringTypeOf(mdecl);
    // }

    // public static ResolvedType declaringTypeOf(Node decl) {
    //     Node declaringClass = decl;
    //     while (declaringClass != null) {
    //         if (declaringClass instanceof ClassOrInterfaceDeclaration)
    //             break;
    //         if (declaringClass instanceof ObjectCreationExpr)
    //             break;
    //
    //         declaringClass = declaringClass.getParentNode().get();
    //     }
    //     String fullQualifiedName;
    //     if (declaringClass == null) {
    //         fullQualifiedName = JavaConstants.JAVA_LANG_OBJECT;
    //     }
    //     else if (declaringClass instanceof ClassOrInterfaceDeclaration) {
    //         ClassOrInterfaceDeclaration cidecl = (ClassOrInterfaceDeclaration) declaringClass;
    //         fullQualifiedName = cidecl.getFullyQualifiedName().get();
    //     }
    //     else if (declaringClass instanceof ObjectCreationExpr) {
    //         ObjectCreationExpr oce = (ObjectCreationExpr) declaringClass;
    //         fullQualifiedName = oce.getType().getNameWithScope();
    //     }
    //
    //     else {
    //         fullQualifiedName = JavaConstants.JAVA_LANG_OBJECT;
    //     }
    //
    //     return new ReferencedTypeUse(fullQualifiedName);
    // }

    // public static ResolvedType getDeclaringType(ResolvedMethodDeclaration rdecl) {
    //     String packageName = rdecl.getPackageName();
    //     String className = rdecl.getClassName();
    //
    //     return new ReferencedTypeUse(JavaUtils.qualifiedName(packageName, className));
    // }

    public static int getTypeParametersCount(ClassOrInterfaceType n) {
        if (!n.getTypeArguments().isPresent())
            return 0;
        else
            return n.getTypeArguments().get().size();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static class Cleanup {
        private String decl;
        private int p;
        private int len;

        private Cleanup(String decl) {
            this.decl = decl;
        }

        private String cleanup() {
            removeBlockCommenst();
            removeLineComments();
            removeExtraSpaces();
            simplifyWildcards();
            replaceDotsWithArray();
            removeAnnotations();
            cleanupAnonymous();
            return decl;
        }

        private void removeBlockCommenst() {
            int p = decl.indexOf("/*");
            while (p != -1) {
                int e = decl.indexOf("*/", p+1)+2;
                decl = decl.substring(0, p) + " " + decl.substring(e);
                p = decl.indexOf("/*");
            }
        }

        private void removeLineComments() {
            int p = decl.indexOf("//");
            while (p != -1) {
                int e = decl.indexOf("\n", p);
                if (e != -1)
                    decl = decl.substring(0, p) + decl.substring(e);
                else
                    decl = decl.substring(0, p);
                p = decl.indexOf("//");
            }
        }

        private void removeExtraSpaces() {
            decl = decl.replace('\n', ' ');
            decl = decl.replace('\t', ' ');
            // int p = decl.indexOf("  ");
            // while (p != -1) {
            //     decl = decl.replace("  ", " ");
            //     p = decl.indexOf("  ");
            // }
            // p = decl.indexOf("( ");
            // while (p != -1) {
            //     decl = decl.replace("( ", "(");
            //     p = decl.indexOf("( ");
            // }
            // p = decl.indexOf(" )");
            // while (p != -1) {
            //     decl = decl.replace(" )", ")");
            //     p = decl.indexOf(" )");
            // }
        }

        private void simplifyWildcards() {
            // simplify multiple '<? extends ...>' -> '<?>'
            int p = decl.indexOf('?');
            while (p != -1) {
                simplifyWildcard(p);
                p = decl.indexOf('?', p+1);
            }
        }

        private void simplifyWildcard(int s) {
            this.p = s;
            this.len = this.decl.length();
            int depth = 0;
            while (true) {
                char c = ch();
                if (c == 0)
                    break;
                if (c == '>') {
                    if (depth == 0)
                        break;
                    else
                        depth -= 1;
                }
                else if (c == '<') {
                    depth += 1;
                }
            }
            if (s+1 != p-1) {
                decl = decl.substring(0, s+1) + decl.substring(p-1);
            }
        }

        private void replaceDotsWithArray() {
            int p = decl.indexOf("...");
            while (p != -1) {
                decl = decl.replace("...", "[]");
                p = decl.indexOf("...");
            }
        }

        private void removeAnnotations() {
            if (decl.contains("@interface"))
                return;

            int p = decl.indexOf("@");
            while (p != -1) {
                removeAnnotation(p);
                p = decl.indexOf("@");
            }
        }

        private void removeAnnotation(int s) {
            this.p = s;
            this.len = this.decl.length();

            //  Remove annotation
            //
            //      @ ID1 ( ...( ... ) ... ) ID2
            //      @ ID1 ID2
            //
            //  states
            //      0: start
            //      1: in ID1
            //      2: after ID1
            //          '(' -> depth + 1
            //          ')' -> depth - 1
            //      3: after ID1 or after last ')'
            int state = 0;
            int depth = 0;

            while (true) {
                char c = ch();
                if (c == 0)
                    break;
                if (state == 0) {
                    if (c == '@')
                        continue;
                    if (" \n\r\t".indexOf(c) != -1)
                        continue;
                    else {
                        p -= 1;
                        state = 1;
                    }
                }
                else if (state == 1) {
                    // in ID1
                    if (" \n\r\t".indexOf(c) != -1) {
                        state = 2;
                    }
                    else if (c == '(') {
                        state = 2;
                        depth += 1;
                    }
                }
                else if (state == 2) {
                    // in '   ( ... )'
                    if (" \n\r\t".indexOf(c) != -1) {
                        continue;
                    }
                    else if (c == '(') {
                        depth += 1;
                    }
                    else if (c == ')') {
                        depth -= 1;
                    }
                    else if (depth == 0) {
                        p -= 1;
                        state = 3;
                    }
                }
                else {
                    p -= 1;
                    decl = decl.substring(0, s) + decl.substring(p);
                    break;
                }
            }
        }

        private void cleanupAnonymous() {
            int o = decl.indexOf("new ");
            while (o != -1) {
                Assert.nop();
                break;
            }
        }

        private char ch() {
            if (p < len)
                return decl.charAt(p++);
            else
                return 0;
        }
    }

    public static String getDeclarationAsString(CallableDeclaration<?> n) {
        String decl = n.getDeclarationAsString();

        // cleanup declaration
        return new Cleanup(decl).cleanup();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static List<ResolvedType> getTypeElements(ResolvedUnionType rtype) {
        try {
            Field elements = rtype.getClass().getDeclaredField("elements");
            elements.setAccessible(true);
            return (List<ResolvedType>) elements.get(rtype);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return Collections.emptyList();
        }
    }

    public static List<ResolvedType> getTypeElements(ResolvedIntersectionType rtype) {
        try {
            Field elements = rtype.getClass().getDeclaredField("elements");
            elements.setAccessible(true);
            return (List<ResolvedType>) elements.get(rtype);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return Collections.emptyList();
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static CompilationUnit getCompilationUnit(Node n) {
        Optional<Node> optNode = n.getParentNode();
        while (optNode.isPresent()) {
            Node node = optNode.get();
            Class<?> declClass = node.getClass();
            if (declClass.equals(CompilationUnit.class))
                return (CompilationUnit) node;
            optNode = node.getParentNode();
        }
        throw new RuntimeException("Unable to find the CompilationUnit node");
    }

    public static Optional<Node> findParentDeclaration(Node n) {
        Optional<Node> optDecl = n.getParentNode();
        while(optDecl.isPresent()) {
            Node declaration = optDecl.get();
            Class<?> declClass = declaration.getClass();
            // Class | Interface
            if (declClass.equals(ClassOrInterfaceDeclaration.class))
                return Optional.of(declaration);
            // Local Class
            if (declClass.equals(LocalClassDeclarationStmt.class))
                return Optional.of(declaration);

            // new Type(){ ... }
            if (declClass.equals(ObjectCreationExpr.class))
                return Optional.of(declaration);
            // Enum
            if (declClass.equals(EnumDeclaration.class))
                return Optional.of(declaration);

            optDecl = declaration.getParentNode();
        }
        return Optional.empty();
    }

    // public static boolean isTypeParameter(NameExpr n) {
    //     String symbol = n.getNameAsString();
    //     return isTypeParameter(symbol, n);
    // }

    // public static boolean isTypeParameter(ClassOrInterfaceType n) {
    //     String symbol = n.getNameAsString();
    //     return isTypeParameter(symbol, n);
    // }

    public static boolean isTypeParameter(String symbol, Node n) {
        // Note: this test reduce A LOT the speed of analysis
        // We use a simple heuristic: a symbol can be a type parameter is it is composed by
        // 1 or 2 characters

        // return symbol.length() < 3;

        Optional<Node> optParent = n.getParentNode();
        while(optParent.isPresent()) {
            Node node = optParent.get();

            if (node instanceof ClassOrInterfaceDeclaration) {
                ClassOrInterfaceDeclaration decl = ((ClassOrInterfaceDeclaration) node).asClassOrInterfaceDeclaration();
                for (TypeParameter p : decl.getTypeParameters())
                    if (p.getNameAsString().equals(symbol))
                        return true;
            }
            if (node instanceof MethodDeclaration) {
                MethodDeclaration decl = ((MethodDeclaration) node).asMethodDeclaration();
                for (TypeParameter p : decl.getTypeParameters())
                    if (p.getNameAsString().equals(symbol))
                        return true;
            }
            if (node instanceof ConstructorDeclaration) {
                ConstructorDeclaration decl = ((ConstructorDeclaration) node).asConstructorDeclaration();
                for (TypeParameter p : decl.getTypeParameters())
                    if (p.getNameAsString().equals(symbol))
                        return true;
            }

            optParent = node.getParentNode();
        }
        return false;
    }

    public static boolean isMethodReferenceExpr(ClassOrInterfaceType n) {
        Optional<Node> optParent = n.getParentNode();
        while(optParent.isPresent()) {
            Node node = optParent.get();

            if (node instanceof MethodReferenceExpr)
                return true;

            optParent = node.getParentNode();
        }
        return false;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Optional<ClassOrInterfaceDeclaration> findClassOrInterfaceDeclaration(Node n) {
        Optional<Node> optDecl = n.getParentNode();
        while(optDecl.isPresent()) {
            Node declaration = optDecl.get();
            Class<?> declClass = declaration.getClass();
            if (declClass.equals(ClassOrInterfaceDeclaration.class))
                return Optional.of((ClassOrInterfaceDeclaration) declaration);
            if (declClass.equals(LocalClassDeclarationStmt.class))
                return Optional.of(((LocalClassDeclarationStmt) declaration).getClassDeclaration());

            if (declClass.equals(ObjectCreationExpr.class))
                return Optional.empty();
            if (declClass.equals(EnumDeclaration.class))
                return Optional.empty();

            optDecl = declaration.getParentNode();
        }
        return Optional.empty();
    }

    private  static Optional<ClassOrInterfaceDeclaration> findParentClassOrInterfaceDeclaration(Node n) {
        Optional<Node> optDecl = n.getParentNode();
        while(optDecl.isPresent()) {
            Node declaration = optDecl.get();
            Class<?> declClass = declaration.getClass();
            if (declClass.equals(ClassOrInterfaceDeclaration.class))
                return Optional.of((ClassOrInterfaceDeclaration) declaration);
            if (declClass.equals(LocalClassDeclarationStmt.class))
                ; //return Optional.of(((LocalClassDeclarationStmt) declaration).getClassDeclaration());
            // if (declClass.equals(ObjectCreationExpr.class))
            //     return Optional.empty();

            optDecl = declaration.getParentNode();
        }
        return Optional.empty();
    }

    public static Optional<String> getFullyQualifiedName(TypeDeclaration cid) {
        Optional<String> oqname = cid.getFullyQualifiedName();
        if (oqname.isPresent()) return oqname;

        String name = cid.getNameAsString();
        Optional<ClassOrInterfaceDeclaration> ocid = findParentClassOrInterfaceDeclaration(cid);
        if (!ocid.isPresent())
            return Optional.empty();
        oqname = getFullyQualifiedName(ocid.get());
        return Optional.of(JavaUtils.qualifiedName(oqname.get(), name));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static String getSignature(CallableDeclaration cd) {
        String signature = cd.getSignature().asString();
        return normalizeSignature(signature);
    }

    // public static String getSignature(MethodDeclaration md) {
    //     String signature = md.getSignature().asString();
    //     return normalizeSignature(signature);
    // }

    public static String getSignature(ResolvedMethodLikeDeclaration rmd) {
        try {
            String signature = rmd.getSignature();
            return normalizeSignature(signature);
        }
        catch (UnsupportedOperationException | UnsolvedSymbolException | ResolveTimeoutException e) {
            // logger.errorf("Unable to resolve %s: %s (%s)", rmd.toString(), e.getClass().getName(), e.getMessage());
        }
        catch (RuntimeException e) {
            //
        }
        return normalizeSignature(rmd.getQualifiedName());
    }

    public static String getSignature(ResolvedMethodDeclaration rmd) {
        try {
            String signature = rmd.getSignature();
            return normalizeSignature(signature);
        }
        catch (UnsupportedOperationException | UnsolvedSymbolException | ResolveTimeoutException e) {
            // logger.errorf("Unable to resolve %s: %s (%s)", rmd.toString(), e.getClass().getName(), e.getMessage());
        }
        catch (RuntimeException e) {
            //
        }
        return normalizeSignature(rmd.getQualifiedName());
    }

    public static String getSignature(ResolvedConstructorDeclaration rcd) {
        try {
            // A signature with a ".../n" is the constructor for a anonymous class
            String signature = rcd.getSignature();
            if (signature.contains("/")) {
                int pos = signature.lastIndexOf('/');
                int bgn = signature.lastIndexOf('.');
                signature = signature.substring(0, pos) + signature.substring(bgn, pos);
            }
            return normalizeSignature(signature);
        }
        catch (UnsupportedOperationException | UnsolvedSymbolException | ResolveTimeoutException e) {
            //logger.errorf("Unable to resolve %s: %s (%s)", rcd.toString(), e.getClass().getName(), e.getMessage());
        }
        catch (RuntimeException e) {

        }
        return normalizeSignature(rcd.getQualifiedName());
    }

    private static String normalizeSignature(String signature) {
        // if (signature.contains("..."))
        //     signature = signature.replace("...", "[]");
        return signature;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static Map<TypeSolver, ?> getJavaParserFacadeTypeSolversMap() {
        try {
            Field instancesField = JavaParserFacade.class.getDeclaredField("instances");
            instancesField.setAccessible(true);
            return (Map<TypeSolver, ?>)instancesField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeTypeSolver(TypeSolver ts) {
        synchronized (JavaParserFacade.class) {
            Map<TypeSolver, ?> tsmap = getJavaParserFacadeTypeSolversMap();
            tsmap.remove(ts);
        }
        if (ts instanceof BaseTypeSolver)
            ((BaseTypeSolver)ts).getElements().forEach(JPUtils::removeTypeSolver);
    }

    public static void removeTypeSolvers(String prefix) {
        List<TypeSolver> toRemove = new ArrayList<>();
        synchronized (JavaParserFacade.class) {
            Map<TypeSolver, ?> tsmap = getJavaParserFacadeTypeSolversMap();
            for(TypeSolver ts : tsmap.keySet())
                if (ts instanceof BaseTypeSolver)
                    if (((BaseTypeSolver)ts).getName().contains(prefix))
                        toRemove.add(ts);
            for (TypeSolver ts : toRemove)
                tsmap.remove(ts);
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static class JavaParserFacadeCache implements Cache<Object, Object> {
        private Map<TypeSolver, ?> tsmap;

        JavaParserFacadeCache(Map<TypeSolver, ?> tsmap) {
            this.tsmap = tsmap;
        }

        @Override
        public String getId() {
            return Integer.toHexString(JavaParserFacade.class.getName().hashCode());
        }

        @Override
        public String getName() {
            return JavaParserFacade.class.getName();
        }

        @Override
        public Properties getProperties() {
            return PropertiesUtils.empty();
        }

        @Override
        public long size() {
            return tsmap.size();
        }

        @Override
        public boolean containsKey(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object get(Object key, Callable<Object> callable) {
            throw new UnsupportedOperationException();
        }

        // @Override
        // public Object get(Object key, Function<Object, Object> function) {
        //     throw new UnsupportedOperationException();
        // }

        // @Override
        // public Object getOrDefault(Object key, Object defaultValue) {
        //     throw new UnsupportedOperationException();
        // }

        @Override
        public Object getChecked(Object key, Callable<Object> callable) throws ExecutionException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object get(Object o) {
            throw new UnsupportedOperationException();
        }

        // @Override
        // public Optional<Object> getIfPresent(Object key) {
        //     return Optional.empty();
        // }

        @Override
        public void put(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {

        }
    }

    public static Cache<?, ?> getJavaParserFacadeCache() {
        return new JavaParserFacadeCache(getJavaParserFacadeTypeSolversMap());
    }

    // ----------------------------------------------------------------------
    // Problems
    // ----------------------------------------------------------------------

    public static String getProblemMessages(ParseResult<CompilationUnit> result) {
        List<Problem> problems = result.getProblems();
        if (problems.isEmpty())
            return "no problems";
        if (problems.size() == 1)
            return result.getProblem(0).getMessage();

        StringBuilder sb = new StringBuilder();
        for (Problem problem : result.getProblems()) {
            if (sb.length() > 0)
                sb.append("\n");
            sb.append(problem.getMessage());
        }
        return sb.toString();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
