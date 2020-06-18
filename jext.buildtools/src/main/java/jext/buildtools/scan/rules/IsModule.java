package jext.buildtools.scan.util;

import jext.util.Wildcard;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IsModule {

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
