package jext.buildtools.scan.util;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;

public class PatternSet {

    private FilePatterns patterns = new FilePatterns();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectNodes(selected, "filename")
                .forEach(incl -> {
                    String name = XPathUtils.getValue(incl, "@name");
                    this.patterns.add(new FilePattern(name));
                });
    }

    public boolean accept(File baseDir, File file) {
        return patterns.accept(baseDir, file);
    }

    public boolean accept(String name) {
        return patterns.accept(name);
    }
}
