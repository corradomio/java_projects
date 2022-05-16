package jext.graph.named;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedIndices {

    public static NamedIndices load(File nindicesFile) throws ParserConfigurationException, IOException, SAXException {
        Digester d = new Digester();

        NamedIndices nindices = new NamedIndices();

        addRules(d, nindices);

        d.push(nindices);

        d.parse(nindicesFile);

        return nindices;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final String NO_VERSION = "";

    private Map<String/*version*/, Map<String, NamedIndex>> namedIndices = new HashMap<>();
    private List<NamedIndex> indices = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NamedIndices() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public List<NamedIndex> getIndices(String version) {
        List<NamedIndex> selected = new ArrayList<>();

        for(NamedIndex nindex : indices)
            if (nindex.hasVersion(version))
                selected.add(nindex);

        return selected;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private static void addRules(Digester d, NamedIndices nindices) {
        d.addObjectCreate("graphdb/indices/index", NamedIndex.class);
        d.addSetProperties("graphdb/indices/index");
        d.addCallMethod("graphdb/indices/index", "setBody", 1);
        d.addCallParam("graphdb/indices/index", 0);
        d.addSetNext("graphdb/indices/index", "addNamedIndex");
    }

    public void addNamedIndex(NamedIndex nindex) {
        String version = nindex.getVersion();
        if (!namedIndices.containsKey(version))
            namedIndices.put(version, new HashMap<>());
        namedIndices.get(version).put(nindex.getName(), nindex);
        indices.add(nindex);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
