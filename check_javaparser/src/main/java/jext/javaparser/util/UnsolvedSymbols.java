package jext.javaparser.util;

import com.github.javaparser.ast.Node;
import jext.logging.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnsolvedSymbols {

    private Logger logger = Logger.getLogger(UnsolvedSymbols.class);

    private Map<File, Set<String>> fileUnsolvedSymbols = new HashMap<>();



    public synchronized void unsolved(String symbol, Node n, File file) {
        if (!fileUnsolvedSymbols.containsKey(file))
            fileUnsolvedSymbols.put(file, new HashSet<>());
        Set<String> unsolvedSymbols = fileUnsolvedSymbols.get(file);
        if (unsolvedSymbols.contains(symbol))
            return;

        logger.errorf("%s: unresolved %s\n  %s", n.getClass().getName(), symbol, file);
        unsolvedSymbols.add(symbol);
    }
}
