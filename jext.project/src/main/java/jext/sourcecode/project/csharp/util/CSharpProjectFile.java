package jext.sourcecode.project.csharp.util;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
    <?xml version="1.0" encoding="utf-8"?>
    <Project ToolsVersion="12.0" DefaultTargets="Build" xmlns="...">
        <PropertyGroup ...> ... </>
        ...
        <ItemGroup>
            <Reference Include="PresentationCore" />
            <Reference Include="PresentationFramework" />
            <Reference Include="System.Core" />
            <Reference Include="System.Drawing" />
            ...
            <PackageReference Include="Microsoft.Data.Sqlite">
                <Version>3.1.3</Version>
            </PackageReference>
            <PackageReference Include="Microsoft.Data.Sqlite" Version="3.1.3">

            <Reference Include="FastColoredTextBox">
                <HintPath>..\DLL\FastColoredTextBox.dll</HintPath>
            </Reference>
        </ItemGroup>
    </Project>
 */

public class CSharpProjectFile {

    public static class LocalReference {
        public final String name;
        public final File file;

        private LocalReference(String name, File file) {
            this.name = name;
            this.file = file;
        }
    }

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

    public List<MavenCoords> getPackageReferences() {
        populate();

        List<MavenCoords> references = new ArrayList<>();

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

                    // resolve the macros
                    String resolvedVersion = properties.resolve(version);

                    if (resolvedVersion.contains("$")) {
                        logger.warnf("Unable to resolve %s:%s (%s)", include, version, resolvedVersion);
                        return;
                    }

                    references.add(MavenCoords.of(include, resolvedVersion));
                });
            });
        }
        catch (Exception e) {
            // never raised
        }
        return references;
    }

    public List<LocalReference> getLocalReferences() {
        populate();

        List<LocalReference> references = new ArrayList<>();

        try {
            // <Project>
            Element project = XPathUtils.parse(projectFile).getDocumentElement();

            // list of <ItemGroup>
            XPathUtils.selectElements(project, "ItemGroup").forEach(itemGroup -> {
                // list of <PackageReference>
                XPathUtils.selectElements(itemGroup, "Reference").forEach(packageReference -> {
                    // <Reference Include="FastColoredTextBox">
                    //     <HintPath>..\DLL\FastColoredTextBox.dll</HintPath>
                    // </Reference>
                    String include = XPathUtils.getValue(packageReference, "@Include");
                    // skip 'PackageReference' that doesn't refer to external libraries
                    if (include.isEmpty())
                        return;

                    String path = XPathUtils.getValue(packageReference, "HintPath");
                    if (path.isEmpty())
                        return;

                    // resolve the macros
                    String resolvedPath = properties.resolve(path);
                    File file;

                    if (resolvedPath.contains("$")) {
                        logger.warnf("Unable to resolve %s:%s (%s)", include, path, resolvedPath);
                        return;
                    }

                    if (FileUtils.isAbsolute(resolvedPath))
                        file = new File(resolvedPath);
                    else
                        file = new File(this.projectFile.getParentFile(), resolvedPath).getAbsoluteFile();

                    if (!file.exists()) {
                        logger.warnf("Unable to find %s:%s (%s)", include, path, file);
                        return;
                    }

                    references.add(new LocalReference(include, file));
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
