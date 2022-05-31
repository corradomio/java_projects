package jext.sourcecode.project.csharp.util;

import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * ".csproj" file
 *
 * XML file
 */
public class CSharpProjectFile {

    private static final String EMPTY_POM = "<Project/>";
    private static Logger logger = Logger.getLogger(MavenDownloader.class);

    private File csprojFile;
    private Element project;

    public CSharpProjectFile(File csprojFile) {
        this.csprojFile = csprojFile;
        loadContent();
    }

    private void loadContent() {
        try {
            this.project = XPathUtils.parse(csprojFile).getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            logger.errorf("Unable to parse %s: %s", csprojFile, e);
        }
        try {
            this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException e) { }
    }
}
