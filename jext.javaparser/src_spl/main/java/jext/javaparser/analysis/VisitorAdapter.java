package jext.javaparser.analysis;

import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class VisitorAdapter<R, A> extends GenericVisitorAdapter<R, A> {

    public VisitorAdapter() {
        super();
    }
}
