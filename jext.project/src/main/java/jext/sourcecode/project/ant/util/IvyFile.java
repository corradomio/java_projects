package jext.sourcecode.project.ant.util;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IvyFile {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private File ivyFile;
    private Element module;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public IvyFile(File ivyFile) {
        this.ivyFile = ivyFile;

        if (ivyFile.exists())
        try {
            module = XPathUtils.parse(ivyFile).getDocumentElement();
        } catch (Exception e) {
            Logger.getLogger(IvyFile.class).errorf("Unable to parse %s: %s", ivyFile, e);
        }

        if (module == null)
        try {
            module = XPathUtils.parse("<ivy-module/>").getDocumentElement();
        }
        catch (Exception e) { }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public List<MavenCoords> getDependencyCoords() {
        List<MavenCoords> dependencies = new ArrayList<>();
        XPathUtils.selectElements(module, "dependencies/dependency")
                .forEach(dep -> {
                    String groupId = XPathUtils.getValue(dep, "@org");
                    String artifactId = XPathUtils.getValue(dep, "@name");
                    String version = XPathUtils.getValue(dep, "@version");
                    dependencies.add(new MavenCoords(groupId, artifactId, version));
                });
        return dependencies;
    }
}
