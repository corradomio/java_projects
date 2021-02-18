package jext.javaparser.ast.unique;

import com.github.javaparser.ast.NodeList;
import jext.javaparser.ast.UniqueSymbols;

import java.util.ArrayList;

public class ArrayListUnique extends NoneUnique {

    public ArrayListUnique(UniqueSymbols us) {
        super(us);
    }

    @Override
    public void analyze(Object o) {
        ArrayList al = (ArrayList) o;
        for(Object e : al)
            us.analyze(e);
    }
}

