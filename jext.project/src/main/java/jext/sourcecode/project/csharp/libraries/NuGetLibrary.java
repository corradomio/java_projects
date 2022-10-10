package jext.sourcecode.project.csharp.libraries;

import jext.maven.MavenCoords;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.lfm.LibraryLicense;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.csharp.NuGetDownloader;
import jext.sourcecode.project.csharp.util.DotNetResolver;
import jext.sourcecode.project.java.maven.MavenName;
import jext.sourcecode.project.lfm.LicenseFinder;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NuGetLibrary extends CSharpLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String NUSPEC = ".nuspec";

    private MavenCoords coords;
    private NuGetDownloader downloader;
    private LibraryLicense license;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NuGetLibrary(MavenCoords coords, NuGetDownloader downloader) {
        super(MavenName.of(coords));
        this.libraryFile = downloader.getDownloadDirectory(coords);
        this.version = coords.version;
        this.libraryType = LibraryType.REMOTE;
        this.coords = coords;
        this.downloader = downloader;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return this.libraryFile.exists();
    }

    @Override
    public List<File> getFiles() {
        if (libraryFiles == null) {
            download();
            populate();
        }
        return libraryFiles;
    }

    private void download() {
        if (!libraryFile.exists())
            downloader.checkArtifact(coords);
    }

    /*
        Problem:
        the directory lib contains a list of subdirectories as, for example:

        lib/
            net20
            net35
            net45
            net472
            netstandard1.0
            netstandard1.3
            netstandard2.0
            netcoreapp3.0
            netcoreapp3.1
            net6.0

        In theory, it is necessary to select the correct implementation.
        This depends on the project, however, to simplify it will be selected
        the library for the latest SDK version
     */
    private void populate() {
        File lib = new File(libraryFile, "lib");

        // select the latest .NET framework
        DotNetResolver dnr = new DotNetResolver(lib);
        String dotnet = dnr.select();

        File selectedImpl = new File(lib, dotnet);
        this.libraryFiles = FileUtils.listFiles(selectedImpl, ".dll");
    }

    // ----------------------------------------------------------------------
    // License
    // ----------------------------------------------------------------------

    @Override
    public LibraryLicense getLibraryLicense() {
        if (license == null)
            license = LicenseFinder.findLicense(libraryFile);
        return license;
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------
    /*
        *.nuspec file
        * -----------

        <?xml version="1.0" encoding="utf-8"?>
        <package xmlns="http://schemas.microsoft.com/packaging/2013/05/nuspec.xsd">
          <metadata>
            <id>Serilog.Extensions.Logging</id>
            <version>3.1.0</version>
            <authors>Microsoft,Serilog Contributors</authors>
            <requireLicenseAcceptance>false</requireLicenseAcceptance>
            <license type="expression">Apache-2.0</license>
            <licenseUrl>https://licenses.nuget.org/Apache-2.0</licenseUrl>
            <icon>serilog-extension-nuget.png</icon>
            <projectUrl>https://github.com/serilog/serilog-extensions-logging</projectUrl>
            <description>Low-level Serilog provider for Microsoft.Extensions.Logging</description>
            <tags>serilog Microsoft.Extensions.Logging</tags>
            <repository type="git" url="https://github.com/serilog/serilog-extensions-logging" />
            <dependencies>
              <group targetFramework=".NETStandard2.0">
                <dependency id="Microsoft.Extensions.Logging" version="2.0.0" exclude="Build,Analyzers" />
                <dependency id="Serilog" version="2.9.0" exclude="Build,Analyzers" />
              </group>
            </dependencies>
          </metadata>
        </package>
     */

    public Set<Library> getDependencies() {

        // select the unique file the selected extension
        File nuspecFile = FileUtils.findFile(libraryFile, NUSPEC);
        if (nuspecFile == null)
            return Collections.emptySet();

        Set<Library> deplibs = new HashSet<>();

        try {
            Element root = XPathUtils.parse(nuspecFile).getDocumentElement();

            XPathUtils.selectElements(root, "metadata/dependencies/group")
            .forEach(group -> {
            XPathUtils.selectElements(group, "dependency")
            .forEach(dependency -> {
                // <dependency id="Microsoft.Extensions.Diagnostics.HealthChecks" version="5.0.1" exclude="Build,Analyzers" />
                String id = XPathUtils.getValue(dependency, "@id", "");
                String version = XPathUtils.getValue(dependency, "@version", "");

                if (id.isEmpty() || version.isEmpty())
                    return;

                MavenCoords coords = MavenCoords.of(id, version);

                Library deplib = new NuGetLibrary(coords, downloader);

                deplibs.add(deplib);
            });
            });

        }
        catch (Exception e) {
            logger.error(e, e);
        }

        return deplibs;
    }


    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
