package jext.javaparser.util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UniqueSymbols extends VoidVisitorAdapter<Void> {

    private Map<String, String> symbols = new ConcurrentHashMap<>();

    public void analyze(CompilationUnit cu) {
        visit(cu, null);
    }

    @Override
    public void visit(Name n, Void arg) {
        makeUnique(n);
        super.visit(n, arg);
    }
    @Override
    public void visit(SimpleName n, Void arg) {
        makeUnique(n);
        super.visit(n, arg);
    }
    @Override
    public void visit(MethodReferenceExpr n, Void arg) {
        makeUnique(n);
        super.visit(n, arg);
    }

    private void makeUnique(Node n) {
        try {
            Field field = n.getClass().getDeclaredField("identifier");
            field.setAccessible(true);
            String symbol = (String) field.get(n);
            field.set(n, unique(symbol));
        } catch (IllegalAccessException | NoSuchFieldException e) {

        }
    }

    private String unique(String symbol) {
        if (!symbols.containsKey(symbol))
            symbols.put(symbol, symbol);
        return symbols.get(symbol);
    }

}
