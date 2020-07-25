package jext.buildtools.scan.rules;

import jext.buildtools.maven.MavenCoords;
import jext.buildtools.scan.util.FileSets;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
        <dependencies>
            <!-- module -->
            <dependency module="moduleName"/>
            <!-- all files in 'libs' with extension '.jar' -->
            <dependency name="libs" includes="*.jar"/>
            <!-- all files in 'libs' directory -->
            <dependency name="libs"/>

            <!-- maven coords -->
            <dependency maven="groupId:artifactId[:version]"/>
            <!-- ivy syntax -->
            <dependency org="groupId" name="artifactId" rev="version"/>
            <!-- maven syntax -->
            <dependency>
                <groupId>groupId</groupId>
                <artifactId>artifactId</artifactId>
                <version>version</version>
            </dependency>
        </dependencies>
 */

public class Dependencies {

    private List<String> modules = new ArrayList<>();
    private FileSets fileSets = new FileSets();
    private List<MavenCoords> maven = new ArrayList<>();

    public void  configure(Element elt, String xpath) {
        Element selected = (Element) XPathUtils.selectNode(elt, xpath);
        if (selected == null) return;
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
                        fileSets.configureFileSet(dep);
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

    public List<File> getLocalDependencies(File baseDir) {
        return fileSets.getFiles(baseDir);
    }

    public List<MavenCoords> getMavenDependencies() {
        return maven;
    }

    private MavenCoords getMavenCoords(Element dep) {
        // direct maven coordinates
        String coords = XPathUtils.getValue(dep, "@maven", null);
        if (coords != null)
            return new MavenCoords(coords);

        // Ivy syntax
        String groupId = XPathUtils.getValue(dep, "@org", null);
        String artifactId;
        String version;
        if (groupId != null) {
            artifactId = XPathUtils.getValue(dep, "@name");
            version = XPathUtils.getValue(dep, "@rev", null);
            return new MavenCoords(groupId, artifactId, version);
        }
        // Maven syntax
        else {
            groupId = XPathUtils.getValue(dep, "groupId");
            artifactId = XPathUtils.getValue(dep, "artifactId");
            version = XPathUtils.getValue(dep, "@version", null);
        }

        return new MavenCoords(groupId, artifactId, version);
    }

}
