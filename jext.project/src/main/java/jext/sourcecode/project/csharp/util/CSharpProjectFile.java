package jext.sourcecode.project.csharp.util;

import jext.logging.Logger;
import jext.maven.MavenDownloader;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    private static Logger logger = Logger.getLogger(CSharpProjectFile.class);

    private final File projectFile;
    private final File currentHome;
    private final File solutionHome;
    private final String solutionDir;
    private Element project;

    // used jext.util.Properties because this class is able to resolve automatically
    // ${...} (Java) and $(...) (C#) references
    private final jext.util.Properties properties = new jext.util.Properties();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpProjectFile(File solutionHome, File projectFile) {
        this.solutionHome = solutionHome;
        this.projectFile = projectFile;
        this.currentHome = projectFile.getParentFile();
        this.solutionDir = solutionHome.getAbsolutePath() + "/";
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public List<DotNetPackageReference> getPackageReferences() {
        populate();

        List<DotNetPackageReference> references = new ArrayList<>();

        try {
            // <Project>
            Element project = XPathUtils.parse(projectFile).getDocumentElement();

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

                    // resolve the package version
                    String resolvedVersion = properties.resolve(version);

                    if (resolvedVersion.contains("$")) {
                        logger.warnf("Unable to resolve %s:%s (%s)", include, version, resolvedVersion);
                    }

                    // if version contains '$', in theroy it is necessary to resolve the macro.
                    references.add(new DotNetPackageReference(include, resolvedVersion));
                });
            });
        }
        catch (Exception e) {
            // never raised
        }
        return references;
    }

    public Properties getProperties() {
        populate();
        return properties;
    }

    public void addProperties(Properties properties) {
        this.properties.putAll(properties);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void populate() {
        if (this.project != null)
            return;

        parseFile();
        scanForProperties();
        scanForImports();
    }

    private void parseFile() {
        if (!projectFile.exists()) {
            try {
                this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
                return;
            }
            catch (Exception e) {
                // never raised
            }
        }
        try {
            this.project = XPathUtils.parse(projectFile).getDocumentElement();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            logger.errorf("Unable to parse %s: %s", projectFile, e);
        }
        try {
            this.project = XPathUtils.parse(EMPTY_POM).getDocumentElement();
        }
        catch (Exception e) {
            // never raised
        }
    }

    /*
        The properties are saved inside the file in a tag:

        <Project>
            <PropertyGroup ...>
                <PropertyName>PropertyValue</PropertyName>
                ...
            </PropertyGroup>
        </Project>

        However, the file can import other files using

        <Project>
            <Import Project="path" .../>
        <Project>

        Note that path can be:

            path ::=
                "..\Lucene.Net.CodeAnalysis\Version.props"      a relative path
                "$(SolutionDir).build/nuget.props"              where SolutionDir is a predefined property

     */
    private void scanForProperties() {
        try {
            // <Project>
            Element project = XPathUtils.parse(projectFile).getDocumentElement();

            // list of <PropertyGroup>
            XPathUtils.selectElements(project, "PropertyGroup").forEach(propertyGroup -> {
                // list of <PropertyName>
                XPathUtils.selectElements(propertyGroup, "*").forEach(propertyNode -> {
                    // <PropertyName>PropertyValue</PropertyName>

                    String name = propertyNode.getNodeName();
                    String value = propertyNode.getTextContent();

                    this.properties.setProperty(name, value);
                });
            });
        }
        catch (Exception e) {
            // never raised
        }
    }

    /*
        Default import files:
            https://docs.microsoft.com/en-us/visualstudio/msbuild/customize-your-build?view=vs-2022

        solutionHome/Directory.Build.props

     */
    // private void scanForDefaultProperties() {
    //     if (projectFile.getName().equals(DIRECTORY_BUILD_PROPS))
    //         return;
    //
    //     File directoryBuildPropsFile = new File(solutionHome, DIRECTORY_BUILD_PROPS);
    //     CSharpProjectFile importedFile = new CSharpProjectFile(solutionHome, directoryBuildPropsFile);
    //     Properties importedProperties = importedFile.getProperties();
    //
    //     this.properties.putAll(importedProperties);
    // }

    private void scanForImports() {
        try {
            // <Project>
            Element project = XPathUtils.parse(projectFile).getDocumentElement();

            // list of <Import>
            XPathUtils.selectElements(project, "Import").forEach(importNode -> {
                File importFile;
                String importPath = importNode.getAttribute("Project").replace("\\", "/");

                // can be a relative path, an absolute path or a path using $(SolutionDir)

                // check for '$(SolutionDir)...'
                if (importPath.startsWith("$(SolutionDir)")) {
                    importPath = importPath.replace("$(SolutionDir)", solutionDir);
                    importFile = new File(importPath);
                }
                //  check for '/...' and 'c:/...'
                //  absolute path
                else if (importPath.startsWith("/") || importPath.indexOf(":/") == 1) {
                    importFile = new File(importPath);
                }
                // relative path
                else {
                    importFile = new File(currentHome, importPath);
                }

                // load the imported file
                CSharpProjectFile importedFile = new CSharpProjectFile(solutionHome, importFile);
                Properties importedProperties = importedFile.getProperties();

                this.properties.putAll(importedProperties);
            });
        }
        catch (Exception e) {
            // never raised
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
