package jext.javaparser.ast.unique;

import jext.javaparser.ast.Unique;
import jext.javaparser.ast.UniqueSymbols;

public class NoneUnique implements Unique {

    public static NoneUnique INSTANCE = new NoneUnique(null);
    public static NoneUnique instance() { return INSTANCE; }

    protected UniqueSymbols us;

    public NoneUnique(UniqueSymbols us) {
        this.us = us;
    }

    @Override
    public void analyze(Object o) {

    }
}
