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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
    private boolean asInteger;
    private boolean addMissing;
    private Map<String, Integer> map = Collections.emptyMap();
    private Set<String> vertices = new HashSet<>();

    public OdemImporter() {

    }

    public OdemImporter<V, E> asInteger() {
        this.asInteger = true;
        this.map = new HashMap<>();
        return this;
    }

    public OdemImporter<V, E> addMissing() {
        this.addMissing = true;
        return this;
    }

    @Override
    public void importGraph(Graph<V, E> graph, Reader reader) {
        this.graph = graph;
        try {
            Element root = XPathUtils.parse(reader).getDocumentElement();

            // add vertices
            XPathUtils.selectElements(root, "context")
            .forEach(context -> {
                XPathUtils.selectElements(context, "container")
                .forEach(container -> {
                    XPathUtils.selectElements(container, "namespace")
                    .forEach(namespace -> {
                        XPathUtils.selectElements(namespace, "type")
                        .forEach(type -> {
                            String source = type.getAttribute("name");
                            addVertex(source);
                        });
                    });
                });
            });

            // add edges
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
                            });
                        });
                    });
                });
            });

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private void addVertex(String vertex) {
        if (vertices.contains(vertex))
            return;
        if (asInteger) {
            Integer vid = map.size();
            graph.addVertex((V) vid);
            graph.addVertex((V) vid);
        }
        else {
            graph.addVertex((V) vertex);
        }
        vertices.add(vertex);
    }

    private void addEdge(String source, String target) {
        if (!addMissing && (!vertices.contains(source) || !vertices.contains(target)))
            return;

        addVertex(source);
        addVertex(target);

        if (asInteger) {
            Integer sid = map.get(source);
            Integer tid = map.get(target);
            Graphs.addEdge(graph, (V) sid, (V) tid);
        }
        else {
            Graphs.addEdge(graph, (V) source, (V) target);
        }
    }

}
