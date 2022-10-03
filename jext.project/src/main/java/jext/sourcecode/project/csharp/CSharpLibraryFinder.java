package jext.sourcecode.project.csharp;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Version;
import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.libraries.CSharpRuntimeLibrary;
import jext.sourcecode.project.csharp.libraries.NuGetLibrary;
import jext.util.HashMap;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CSharpLibraryFinder implements LibraryFinder {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(CSharpLibraryFinder.class);

    private Project project;
    private Map<Name, Library> libraries = new HashMap<>();
    private NuGetDownloader downloader = new NuGetDownloader();
    private Map<String, Library> runtimeLibraries = new HashMap<>();
    private Library defaultRuntimeLibrary;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpLibraryFinder() {

    }

    @Override
    public LibraryFinder newFinder(Project project) {
        CSharpLibraryFinder lfinder = new CSharpLibraryFinder();
        lfinder.setLibraries(libraries, runtimeLibraries, defaultRuntimeLibrary);
        lfinder.setDownloader(downloader.newDownloader());
        lfinder.setProject(project);
        return lfinder;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    private void setLibraries(
        Map<Name, Library> libraries,
        Map<String, Library> rtLibraries,
        Library rtLibraryDefault
    ) {
        this.libraries.putAll(libraries);
        this.runtimeLibraries.putAll(rtLibraries);
        this.defaultRuntimeLibrary = rtLibraryDefault;
    }

    private void setDownloader(LibraryDownloader downloader) {
        this.downloader = (NuGetDownloader) downloader;
    }

    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public String getLanguage() {
        return CSharpConstants.CSHARP;
    }

    @Override
    public LibraryDownloader getDownloader() {
        return downloader;
    }

    @Override
    public Library getLibrary(MavenCoords coords) {
        // [downloadDir]/<artifactId>/<version>
        String relativePath = String.format(
                "%s/%s",
                coords.artifactId.toLowerCase(),
                coords.version.toLowerCase());

        File libraryDirectory = new File(downloader.getDownloadDirectory(), relativePath);
        return new NuGetLibrary(coords, libraryDirectory);
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return downloader.getLatestVersion(coords);
    }

    // ----------------------------------------------------------------------
    // Check maven coordinates
    // ----------------------------------------------------------------------

    public MavenCoords normalize(MavenCoords coords) {
        String version = coords.version;

        // if version contains some strange character, replace it with ""
        if (version.contains("$") || version.contains("(") || version.contains(","))
            version = "";

        // no version -> latest
        if (version.isEmpty())
            version = getLatestVersion(coords);

        if (!version.isEmpty())
            return MavenCoords.of(coords, version);
        else
            return coords;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // private static Pattern DOTNET_VERSION  = Pattern.compile("([0-9]+\\.[0-9]+)\\.[0-9]+");

    public void setNamedLibrary(String libraryName, String version, File libraryDirectory) {
        setNamedLibrary(libraryName, version, Collections.singletonList(libraryDirectory));
    }

    public void setNamedLibrary(String libraryName, String version, List<File> libraryFiles) {
        String[] names = libraryName.split(",");

        // check directories
        libraryFiles.forEach(libraryFile -> {
            if (!libraryFile.exists())
                logger.errorf("Runtime library %s:%s: Invalid directory %s", libraryName, version, libraryFile);
        });

        for (String name : names) {
            name = name.trim();

            CSharpRuntimeLibrary rtLibrary = new CSharpRuntimeLibrary(name, version, libraryFiles);

            runtimeLibraries.put(name, rtLibrary);
            if (defaultRuntimeLibrary == null)
                defaultRuntimeLibrary = rtLibrary;
        }
    }

    @Override
    public Library getRuntimeLibrary(String libraryName) {
        Library rtLibrary = runtimeLibraries.get(libraryName);
        if (rtLibrary == null)
            rtLibrary = findBestRuntimeLibrary(libraryName);
        if (rtLibrary == null)
            rtLibrary = selectDefaultRuntimeLibrary(libraryName);

        return rtLibrary;
    }

    private Library findBestRuntimeLibrary(String libraryName) {
        String version = libraryVersion(libraryName);
        if (version.isEmpty())
            return null;

        Version libVersion = Version.of(version);
        Version selectedVersion = Version.of("10000.0");
        Library selectedLibrary = null;

        for(Library rtlib : getRuntimeLibraries()) {
            Version thisVersion = Version.of(rtlib.getVersion());

            // this version must be greater than library version and lower than the current
            // selected version
            if (thisVersion.compareTo(libVersion) > 0 && thisVersion.compareTo(selectedVersion) < 0) {
                selectedVersion = thisVersion;
                selectedLibrary = rtlib;
            }
        }

        if (selectedLibrary != null)
            logger.warnf("Requested %s, selected %s", libraryName, selectedLibrary.getName().getName());

        return selectedLibrary;
    }

    private Library selectDefaultRuntimeLibrary(String libraryName) {
        logger.warnf("Unable to retrieve the runtime library %s. Used the default %s",
                libraryName, defaultRuntimeLibrary.getName().getFullName());
        return defaultRuntimeLibrary;
    }

    // used internally
    public Library getRTLibrary(String libraryName) {
        return runtimeLibraries.get(libraryName);
    }

    @Override
    public Collection<Library> getRuntimeLibraries() {
        return runtimeLibraries.values();
    }

    // ----------------------------------------------------------------------
    // Utilities
    // ----------------------------------------------------------------------
    /*
                net40
                net45
                net451
                net461
                net472
                net5
                net5.0
                net6
                net6.0
                net6.0-android
                net6.0-ios
                net6.0-maccatalyst
                net6.0-tizen
                net6.0-windows
                net6.0-windows10.0.19041
                net6.0-windows7.0
                net7.0
                netcoreapp2.0
                netcoreapp2.1
                netcoreapp3.1
                netcoreapp6.0
                netstandard1.3
                netstandard2.0
                netstandard2.1
     */

    public static String libraryVersion(String libraryName) {
        int p;
        String version = libraryName.toLowerCase();

        // netstandard2.1
        if (version.startsWith("netstandard"))
            version = version.substring("netstandard".length());
        // netcoreapp6.0
        else if (version.startsWith("netcoreapp"))
            version = version.substring("netcoreapp".length());
        // net6.0
        // net6.0-android
        // net472
        else if (version.startsWith("net"))
            version = version.substring("net".length());

        // .NET Framework
        p = version.indexOf("net framework");
        if (p != -1)
            version = version.substring(p+"net framework".length());
        // .NET Standard
        p = version.indexOf("net standard");
        if (p != -1)
            version = version.substring(p+"net standard".length());
        // .NET Core
        p = version.indexOf("net core");
        if (p != -1)
            version = version.substring(p+"net core".length());

        // net6.0-maccatalyst
        p = version.indexOf('-');
        version = p > 0 ? version.substring(0, p) : version;

        // check for version containing '$' or '(',')'
        if (version.contains("$") || version.contains("("))
            return "";

        // remove extra spaces
        version = version.trim();

        // net6.0
        // net472
        if (version.contains(".") || version.length() == 1)
            return version;

        // 47
        // 471
        if (version.startsWith("1") || version.startsWith("2") || version.startsWith("3") || version.startsWith("4")) {
            if (version.length() == 2)
                return version.charAt(0) + "." + version.charAt(1);
            else
                return version.charAt(0) + "." + version.charAt(1) + "." + version.charAt(2);
        }

        return version;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
