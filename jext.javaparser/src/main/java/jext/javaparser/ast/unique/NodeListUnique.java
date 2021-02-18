package jext.javaparser.ast.unique;

import com.github.javaparser.ast.NodeList;
import jext.javaparser.ast.UniqueSymbols;

public class NodeListUnique extends NoneUnique {

    public NodeListUnique(UniqueSymbols us) {
        super(us);
    }

    @Override
    public void analyze(Object o) {
        NodeList nl = (NodeList) o;
        for (int i=0; i<nl.size(); ++i) {
            us.analyze(nl.get(i));
        }
    }
}
