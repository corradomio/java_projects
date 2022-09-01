package jext.sourcecode.project.csharp.util;

import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.util.FileUtils;
import jext.util.Properties;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ".csproj" file
 *
 * XML file
 */
public class CSharpProjectFile {

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private static final String EMPTY_POM = "<Project/>";
    private static Logger logger = Logger.getLogger(MavenDownloader.class);

    private final File csprojFile;
    private final File solutionHome;
    private Element project;

    // used jext.util.Properties because this class is able to resolve automatically
    // ${...} (Java) and $(...) (C#)
    private Properties properties = new Properties();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpProjectFile(File solutionHome, File csprojFile) {
        this.solutionHome = solutionHome;
        this.csprojFile = csprojFile;
        populate();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public List<PackageReference> getPackageReferences() {
        List<PackageReference> references = new ArrayList<>();

        try {
            // <Project>
            Element project = XPathUtils.parse(csprojFile).getDocumentElement();

            // list of <ItemGroup>
            XPathUtils.selectElements(project, "ItemGroup").forEach(itemGroup -> {
                // list of <PackageReference>
                XPathUtils.selectElements(itemGroup, "PackageReference").forEach(packageReference -> {
                    // <PackageReference Include="Microsoft.Data.Sqlite">
                    //     <Version>3.1.3</Version>
                    // </PackageReference>
                    //
                    //  or
                    //
                    //  <PackageReference Include="Microsoft.Data.Sqlite" Version="3.1.3">
                    //
                    //  Note: skip
                    //
                    //  <PackageReference Update="NETStandard.Library" PrivateAssets="all" />
                    String include = XPathUtils.getValue(packageReference, "@Include");

                    String version = XPathUtils.getValue(packageReference, "@Version");
                    if (version.isEmpty())
                        version = XPathUtils.getValue(packageReference, "Version");

                    // skip 'PackageReference' that doesn't refer to external libraries
                    if (include.isEmpty())
                        return;

                    // if version contains '$', in theroy it is necessary to resolve the macro.
                    references.add(new PackageReference(include, version));
                });
            });
        }
        catch (Exception e) {
            logger.warnf("Unable to parse %s", FileUtils.getAbsolutePath(csprojFile));
        }
        return references;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void populate() {
        if (this.project != null)
            return;

        try {
            this.project = XPathUtils.parse(csprojFile).getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            logger.errorf("Unable to parse %s: %s", csprojFile, e);
        }
        try {
            this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
        }
        catch (Exception e) {
            // never raised
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
