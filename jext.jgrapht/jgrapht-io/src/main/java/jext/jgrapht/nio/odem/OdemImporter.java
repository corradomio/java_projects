package jext.jgrapht.nio.odem;

import jext.jgrapht.Graphs;
import jext.xml.XPathUtils;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/*
    ODEM format used in UML diagrams

    <ODEM>
        <context>
            <container>
                <namespace>
                    <type>
                        <dependencies>
                            <dependency-on>
*/

public class OdemImporter <V, E> implements GraphImporter<V, E> {

    private Graph<V, E> graph;
    private final boolean asInteger;
    private final Map<String, Integer> map;

    public OdemImporter() {
        this(false);
    }

    public OdemImporter(boolean asInteger) {
        this.asInteger = asInteger;
        if (asInteger)
            this.map = new HashMap<>();
        else
            this.map = Collections.emptyMap();
    }

    @Override
    public void importGraph(Graph<V, E> graph, Reader reader) {
        this.graph = graph;
        try {
            Element root = XPathUtils.parse(reader).getDocumentElement();

            XPathUtils.selectElements(root, "context")
            .forEach(context -> {
                XPathUtils.selectElements(context, "container")
                .forEach(container -> {
                    XPathUtils.selectElements(container, "namespace")
                    .forEach(namespace -> {
                        XPathUtils.selectElements(namespace, "type")
                        .forEach(type -> {
                            String source = type.getAttribute("name");
                            XPathUtils.selectElements(type, "dependencies/depends-on")
                            .forEach(dependsOn ->{
                                String target = dependsOn.getAttribute("name");
                                addEdge(source, target);

                                // System.out.printf("%s -> %s\n", source, target);
                            });
                        });
                    });
                });
            });

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private void addEdge(String source, String target) {
        if (asInteger) {
            Integer sid = addVertex(source);
            Integer tid = addVertex(target);

            Graphs.addEdge(graph, (V) sid, (V) tid);
        }
        else {
            Graphs.addEdge(graph, (V) source, (V) target);
        }
    }

    private Integer addVertex(String vertex) {
        if (!map.containsKey(vertex)) {
            Integer vid = map.size();
            graph.addVertex((V) vid);
        }
        return map.get(vertex);
    }

}
