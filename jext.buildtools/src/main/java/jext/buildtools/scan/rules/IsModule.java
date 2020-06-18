package jext.buildtools.scan.rules;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IsModule {

    // list of files or relative paths that must be present
    // in a directory because the directory can be considered
    // a 'module'
    private List<String> names = new ArrayList<>();

    public void configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectNodes(selected, "filename")
                .forEach(incl -> {
                    String pattern = XPathUtils.getValue(incl, "@name");
                    this.names.add(pattern);
                });
    }

    public boolean accept(File file) {
        if (!file.isDirectory())
                return false;
        String dirName = file.getName();
        for (String pname : names) {
            pname = pname.replace("${dirName}", dirName);
            if (new File(file, pname).exists())
                return true;
        }
        return false;
    }
}
