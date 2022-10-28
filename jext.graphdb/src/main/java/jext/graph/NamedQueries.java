package jext.graph;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedQueries {

    public static  Map<String/*version*/, Map<String/*name*/, String/*body*/>> load(String path) {
        return load(new File(path));
    }

    public static Map<String/*version*/, Map<String/*name*/, String/*body*/>> load(File file) {
        Map<String/*version*/, Map<String/*name*/, String/*body*/>> namedQueries = new HashMap<>();
        try {
            Element root = XPathUtils.parse(file).getDocumentElement();
            List<Element> queries = XPathUtils.selectElements(root, "/configuration/system/graphdb/namedqueries/query");
            queries.forEach(elt -> {
                String name = elt.getAttribute("name");
                String body = elt.getTextContent();
                String version = elt.getAttribute("version");

                add(namedQueries, version, name, body);
            });
        }
        catch (Exception e) {
            // return Collections.emptyMap();
        }

        return namedQueries;
    }

    private static void add(Map<String/*version*/, Map<String/*name*/, String/*body*/>> namedQueries,
                            String version, String name, String body) {
        if (!namedQueries.containsKey(version))
            namedQueries.put(version, new HashMap<>());
        namedQueries.get(version).put(name, body);
    }
}
