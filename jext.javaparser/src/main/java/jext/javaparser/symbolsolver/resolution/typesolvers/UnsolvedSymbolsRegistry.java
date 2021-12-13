package jext.javaparser.symbolsolver.resolution.typesolvers;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import jext.javaparser.resolution.ReferencedTypeDeclaration;
import jext.lang.JavaUtils;
import jext.logging.Logger;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UnsolvedSymbolsRegistry {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(UnsolvedSymbolsRegistry.class);

    private Map<String, ResolvedReferenceTypeDeclaration> symbols = new TreeMap<>();
    private Set<String> namespaces = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public UnsolvedSymbolsRegistry() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public boolean contains(String name) {
        return symbols.containsKey(name);
    }

    public ResolvedReferenceTypeDeclaration get(String name) {
        return symbols.get(name);
    }

    public boolean isNamespace(String name) {
        return namespaces.contains(name);
    }

    public synchronized boolean register(String name, int nTypeParameters) {
        ResolvedReferenceTypeDeclaration rrtd = symbols.get(name);

        if (rrtd != null && (nTypeParameters == 0 || rrtd.getTypeParameters().size() == nTypeParameters))
            return false;

        symbols.put(name, new ReferencedTypeDeclaration(name, nTypeParameters));

        if (name.indexOf('.') >= 0)
            logger.infof("Registered %s/%d", name, nTypeParameters);
        else
            logger.warnf("Registered %s/%d", name, nTypeParameters);

        String namespace = JavaUtils.namespaceOf(name);
        while(!namespace.isEmpty()) {
            // if it is already registered, it is not necessary to continue
            if (namespaces.contains(namespace))
                break;

            // check is 'namespace' follows the Java 'namespace's rules':
            // it is a list of identifiers composed by lowercase letters and separated
            // by a '.'. Note that they are just syntactic rules !
            if (JavaUtils.isNamespace(namespace))
                namespaces.add(namespace);
            namespace = JavaUtils.namespaceOf(namespace);
        }
        return true;
    }
}
