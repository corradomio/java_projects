package jext.io.file;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;

/*
    <parent>
        <filename name="_pattern_1_"/>
        <filename name="_pattern_2_"/>
    </parent>
 */

public class PatternSet {

    private FilePatterns patterns = new FilePatterns();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectElements(selected, "filename")
                .forEach(incl -> {
                    String pattern = XPathUtils.getValue(incl, "@name");
                    addPattern(pattern);
                });
    }

    public void addPattern(String pattern) {
        patterns.add(new FilePattern(pattern));
    }

    // public boolean accept(File baseDir, File file) {
    //     return patterns.accept(baseDir, file);
    // }

    public boolean accept(String name) {
        return patterns.accept(name);
    }
}
