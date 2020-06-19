package jext.buildtools.scan.rules;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public class Repositories {

    private Map<String, String> repositories = new HashMap<>();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectNodes(selected, "repository")
                .forEach(repo -> {
                    String name = XPathUtils.getValue(repo, "@name");
                    String url = XPathUtils.getValue(repo, "@name");
                    repositories.put(name, url);
                });
    }

    public Map<String,String> getRepositories() {
        return repositories;
    }
}
