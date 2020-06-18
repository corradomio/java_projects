package jext.buildtools.scan.rules;

import jext.buildtools.maven.MavenCoords;
import jext.buildtools.scan.util.FileSets;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dependencies {

    private List<String> modules = new ArrayList<>();
    private FileSets local = new FileSets();
    private List<MavenCoords> maven = new ArrayList<>();

    public void  configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        XPathUtils.selectNodes(selected, "dependency")
                .forEach(dep -> {
                    String value;
                    value = XPathUtils.getValue(dep, "module", null);
                    if (value != null) {
                        modules.add(value);
                        return;
                    }
                    value = XPathUtils.getValue(dep, "name", null);
                    if (value != null) {
                        local.configureFileSet(dep);
                        return;
                    }
                    else {
                        MavenCoords coords = getMavenCoords(dep);
                        if (coords.isValid())
                            maven.add(coords);
                    }

                });
    }

    public List<String> getModuleDependencies() {
        return modules;
    }

    public List<File> getFiles(File baseDir) {
        return local.getFiles(baseDir);
    }

    public List<MavenCoords> getMavenDependencies() {
        return maven;
    }

    private MavenCoords getMavenCoords(Element dep) {
        String coords = XPathUtils.getValue(dep, "@maven", null);
        if (coords != null)
            return new MavenCoords(coords);

        String groupId = XPathUtils.getValue(dep, "@org", null);
        String artifactId;
        String version;
        if (groupId != null) {
            artifactId = XPathUtils.getValue(dep, "@name");
            version = XPathUtils.getValue(dep, "@rev", null);
            return new MavenCoords(groupId, artifactId, version);
        }
        else {
            groupId = XPathUtils.getValue(dep, "groupId");
            artifactId = XPathUtils.getValue(dep, "artifactId");
            version = XPathUtils.getValue(dep, "@version", null);
        }

        return new MavenCoords(groupId, artifactId, version);
    }

}
