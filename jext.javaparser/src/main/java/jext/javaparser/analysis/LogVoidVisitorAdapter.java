package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import jext.logging.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LogVoidVisitorAdapter<A> extends VoidVisitorWithDefaults<A> {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected CompilationUnit cu;
    protected String fileName;
    protected Set<Class<?>> exclude = new HashSet<>();
    protected Set<Class<?>> include = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LogVoidVisitorAdapter() {

    }

    public LogVoidVisitorAdapter<A> exclude(Class<?>... classes) {
        exclude.addAll(Arrays.asList(classes));
        return this;
    }

    public LogVoidVisitorAdapter<A> include(Class<?>... classes) {
        include.addAll(Arrays.asList(classes));
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public LogVoidVisitorAdapter analyze(CompilationUnit cu) {
        this.cu = cu;
        this.fileName = "";

        visit(cu, null);
        return this;
    }

    // ----------------------------------------------------------------------
    // Overrides
    // ----------------------------------------------------------------------

    public void defaultAction(Node n, A arg) {
        if (n instanceof Name) return;
        if (n instanceof SimpleName) return;

        Class<?> c = n.getClass();

        if (!exclude.isEmpty()) {
            if (exclude.contains(c))
                return;
        }
        else if (!include.isEmpty()) {
            if (!include.contains(c))
                return;
        }
        System.out.printf("[%s] %s\n", getClassName(n), toString(n));
    }

    public void defaultAction(NodeList n, A arg) {
        n.forEach(e ->
            defaultAction((Node)e, arg));
    }

    // ----------------------------------------------------------------------

    private static String getClassName(Node n) {
        String className = n.getClass().getName();
        if (className.startsWith("com.github.javaparser.ast."))
            return className.substring("com.github.javaparser.ast.".length());
        else
            return className;
    }

    private static String toString(Node n) {
        String s = n.toString().trim();
        s = s.replace('\t', ' ')
            .replace('\n', '^')
            .replace('\r', ' ');
        if (s.length() > 100)
            s = s.substring(0, 100) + "...";
        return s;
    }
}
