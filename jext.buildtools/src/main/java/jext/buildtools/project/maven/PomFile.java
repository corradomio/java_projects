package jext.buildtools.project.maven;

import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Properties;

public class PomFile {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    static final String EMPTY_POM =
            "<?xml version='1.0' encoding='UTF-8'?>\n" +
                    "<project xmlns='http://maven.apache.org/POM/4.0.0'/>";

    public static final String POM = "pom.xml";

    private static final String PARENT_POM = "../pom.xml";
    private static final String DM_RELOCATION = "distributionManagement/relocation";
    private static final String DM_DEPENDENCIES = "dependencyManagement/dependencies/dependency";
    private static final String DEPENDENCIES = "dependencies/dependency";
    private static final String REPOSITORIES = "repositories/repository";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private File pomFile;
    private Element project;
    private Properties properties;

    public PomFile(File pomFile) {
        if (pomFile.isDirectory())
            pomFile= new File(pomFile, "pom.xml");
        this.pomFile = pomFile;
        try {
            this.project = XPathUtils.parse(pomFile).getDocumentElement();
        } catch (Exception e) { }
        if (this.project == null)
        try {
            this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
        } catch (Exception e) { }

    }
}
