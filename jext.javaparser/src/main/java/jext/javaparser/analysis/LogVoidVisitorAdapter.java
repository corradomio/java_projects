package jext.javaparser.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import jext.logging.Logger;

public class LogVoidVisitorAdapter<A> extends ContextVisitorAdapter {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected CompilationUnit cu;
    protected String fileName;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LogVoidVisitorAdapter() {

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

    public void defaultAction(Node n, Void arg) {
        if (n instanceof Name) return;
        if (n instanceof SimpleName) return;
        if (n instanceof Modifier) return;

        if (n instanceof BodyDeclaration)
            spacesOne();
        else
            spaces();

        System.out.printf("[%s] %s\n", getClassName(n), toString(n));
    }

    public void defaultAction(NodeList n, Void arg) {
        ++depth;
        n.forEach(e ->
            defaultAction((Node)e, arg));
        --depth;
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

    // ----------------------------------------------------------------------

    private int depth = 0;

    private void spaces() {
        for (int i=0; i<depth; ++i)
            System.out.print("  ");
    }

    private void spacesOne() {
        for (int i=0; i<depth-1; ++i)
            System.out.print("  ");
    }

    protected void enter(ClassOrInterfaceDeclaration n) {
        enter();
    }

    protected void exit(ClassOrInterfaceDeclaration n) {
        exit();
    }

    protected void enter(ConstructorDeclaration n) {
        enter();
    }

    protected void exit(ConstructorDeclaration n) {
        exit();
    }

    protected void enter(MethodDeclaration n) {
        enter();
    }

    protected void exit(MethodDeclaration n) {
        exit();
    }

    protected void enter(ObjectCreationExpr n) {
        enter();
    }

    protected void exit(ObjectCreationExpr n) {
        exit();
    }

    private void enter() {
        ++depth;
    }

    private void exit() {
        --depth;
    }

}
