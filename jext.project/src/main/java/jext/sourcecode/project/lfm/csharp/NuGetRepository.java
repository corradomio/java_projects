package jext.sourcecode.project.lfm.csharp;

/*
    NuGet Server API
        https://docs.microsoft.com/en-us/nuget/api/overview

    Service Index:
        https://api.nuget.org/v3/index.json.

    Resources and schema
        Resource name               Required    Description
        PackageBaseAddress          yes         Get package content (.nupkg).
        PackageDetailsUriTemplate   no          Construct a URL to access a package details web page.
        RegistrationsBaseUrl        yes         Get package metadata.
        SearchQueryService          yes         Filter and search for packages by keyword.


    Semantic Version for .NET
        https://www.nuget.org/packages/semver/
        https://semver.org/
 */
/*
    ServiceIndex
    ------------

    {
      "version": "3.0.0",
      "resources": [
        ...
        {
          "@id": "https://api.nuget.org/v3/registration5-semver1/",
          "@type": "RegistrationsBaseUrl",
          "comment": "Base URL of Azure storage where NuGet package registration info is stored"
        },
        {
          "@id": "https://api.nuget.org/v3-flatcontainer/",
          "@type": "PackageBaseAddress/3.0.0",
          "comment": "Base URL of where NuGet packages are stored, in the format https://api.nuget.org/v3-flatcontainer/{id-lower}/{version-lower}/{id-lower}.{version-lower}.nupkg"
        },
        ...
      ],
      "@context": {
        "@vocab": "http://schema.nuget.org/services#",
        "comment": "http://www.w3.org/2000/01/rdf-schema#comment"
      }
    }

 */

import jext.sourcecode.project.util.BaseLibraryRepository;

public class NuGetRepository extends BaseLibraryRepository {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    //private static final Logger logger = Logger.getLogger(NuGetRepository.class);
    //
    //private static final String SERVICE_INDEX_URL = "https://api.nuget.org/v3/index.json";
    //private static final String PACKAGE_ADDRESS_BASE = "PackageBaseAddress/3.0.0";
    //private static final long MAX_VERSIONS_AGE = 24L*60*60*1000L;   // 1 day in milliseconds
    //private static final int BUFFER_SIZE = 1024;
    //
    //private boolean initialized = false;
    //private File nugetDirectory;
    //private String packageAddressBase;
    //
    //private enum ArtifactType {
    //    VERSIONS,
    //    ARTIFACT,
    //    INVALID
    //}
    //
    //private static class ServiceIndex extends HashMap<String, Object> {
    //
    //    String getValue(String key) {
    //        List<Map<String, Object>> resources = (List<Map<String, Object>>) get("resources");
    //        for (Map<String, Object> entry : resources) {
    //            String type = (String) entry.get("@type");
    //            if (key.equals(type))
    //                return (String) entry.get("@id");
    //        }
    //
    //        throw new RuntimeException("Invalid key " + key);
    //    }
    //}

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    //public NuGetRepository() {
    //    setInfo("NuGet", "https://www.nuget.org/");
    //    setDownloadDirectory(new File("."));
    //}

    //public void initialize() throws IOException {
    //    if (initialized)
    //        return;
    //    else
    //        initialized = true;
    //
    //    createDownloadDirectory();
    //    retrieveServiceIndex();
    //}

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    //public Versions getArtifactVersions(MavenCoords coords) throws IOException {
    //    // the package can not exist
    //    // the file can be corrupted
    //    initialize();
    //    Versions versions = new Versions();
    //
    //    File versionsFile  = composeFile(coords, ArtifactType.VERSIONS);
    //    String versionsUrl = composeUrl(coords, ArtifactType.VERSIONS);
    //
    //    if (!versionsFile.exists() || isObsolete(versionsFile)) {
    //        downloadFile(versionsUrl, versionsFile);
    //        validateFile(versionsFile, ArtifactType.VERSIONS);
    //    }
    //
    //    /*
    //        {
    //            "versions": [
    //                "3.5.8",
    //                "4.0.1",
    //                "4.0.2",
    //                "6.0.1-beta1",
    //                "7.0.1-beta1",
    //                "7.0.1-beta2",
    //                "7.0.1-beta3",
    //                "7.0.1",
    //                "13.0.1",
    //                "13.0.2-beta1",
    //                "13.0.2-beta2"
    //            ]
    //        }
    //     */
    //
    //    // check if the file exists -> return the empty list of versions
    //    if (!versionsFile.exists())
    //        return versions;
    //
    //    Map<String, Object> jversions = JSONUtils.load(versionsFile, HashMap.class);
    //    List<String> vlist = (List<String>) jversions.get("versions");
    //    for(String version : vlist)
    //        versions.add(version);
    //
    //    return versions;
    //}
    //
    //private static boolean isObsolete(File file) {
    //    long currentTimestamp = System.currentTimeMillis();
    //    long fileTimestamp = file.lastModified();
    //    return (currentTimestamp - fileTimestamp) > MAX_VERSIONS_AGE;
    //}
    //
    //// ----------------------------------------------------------------------
    //
    //public Optional<File> downloadArtifact(MavenCoords coords) throws IOException {
    //    initialize();
    //
    //    File  artifactFile = composeFile(coords, ArtifactType.ARTIFACT);
    //    String artifactUrl = composeUrl(coords, ArtifactType.ARTIFACT);
    //
    //    if (!artifactFile.exists()) {
    //        downloadFile(artifactUrl, artifactFile);
    //        validateFile(artifactFile, ArtifactType.ARTIFACT);
    //    }
    //    uncompressArtifact(artifactFile);
    //
    //    return artifactFile.exists() ? Optional.of(artifactFile) : Optional.empty();
    //}
    //
    //private static void uncompressArtifact(File artifactFile) throws IOException {
    //    File libDirectory = new File(artifactFile.getParentFile(), "lib");
    //    if (!artifactFile.exists() ||  libDirectory.exists())
    //        return;
    //
    //    File artifactDirectory = artifactFile.getParentFile();
    //    Archives.uncompress(artifactFile, artifactDirectory);
    //}

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    //private void createDownloadDirectory() {
    //    nugetDirectory = new File(download, ".nuget");
    //    if (!nugetDirectory.exists())
    //        FileUtils.mkdirs(nugetDirectory);
    //}
    //
    //private void retrieveServiceIndex() throws IOException {
    //    File serviceIndexFile = new File(nugetDirectory, "serviceIndex.json");
    //    if (!serviceIndexFile.exists())
    //        downloadFile(SERVICE_INDEX_URL, serviceIndexFile);
    //    if (!serviceIndexFile.exists())
    //        throw new FileNotFoundException(serviceIndexFile.getAbsolutePath());
    //
    //    ServiceIndex serviceIndex = JSONUtils.load(serviceIndexFile, ServiceIndex.class);
    //    packageAddressBase = serviceIndex.getValue(PACKAGE_ADDRESS_BASE);
    //}
    //
    //private File composeFile(MavenCoords coords, ArtifactType type) {
    //    String relativePath = "";
    //    switch (type) {
    //        case VERSIONS:
    //            relativePath = String.format("%1$s/versions.json",
    //                    coords.artifactId.toLowerCase());
    //            break;
    //        case ARTIFACT:
    //            relativePath = String.format("%1$s/%2$s/%1$s.%2$s.nupkg",
    //                    coords.artifactId.toLowerCase(),
    //                    coords.version.toLowerCase());
    //            break;
    //        case INVALID:
    //            relativePath = String.format("%1$s/%2$s/%1$s.%2$s.nupkg.invalid",
    //                    coords.artifactId.toLowerCase(),
    //                    coords.version.toLowerCase());
    //            break;
    //        default:
    //            throw new UnsupportedOperationException(type.toString());
    //    }
    //
    //    return new File(nugetDirectory, relativePath);
    //}
    //
    //private String composeUrl(MavenCoords coords, ArtifactType type) {
    //    String url;
    //    switch (type) {
    //        case VERSIONS:
    //            url = String.format("%1$s/%2$s/index.json",
    //                    packageAddressBase,
    //                    coords.artifactId.toLowerCase());
    //            break;
    //        case ARTIFACT:
    //            url =  String.format("%1$s/%2$s/%3$s/%2$s.%3$s.nupkg",
    //                    packageAddressBase,
    //                    coords.artifactId.toLowerCase(),
    //                    coords.version.toLowerCase());
    //            break;
    //        default:
    //            throw new UnsupportedOperationException(type.toString());
    //    }
    //
    //    return normalizeUrl(url);
    //}
    //
    //private static String normalizeUrl(String url) {
    //    int p = url.indexOf("://") + 3;
    //
    //    p = url.indexOf("//", p);
    //    while (p != -1) {
    //        url = url.substring(0, p) + url.substring(p+1);
    //        p = url.indexOf("//", p);
    //    }
    //    return url;
    //}
    //
    //private void downloadFile(String fileUrl, File filePath) throws IOException {
    //    FileUtils.mkdirs(filePath.getParentFile());
    //
    //    try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
    //         FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
    //        byte[] dataBuffer = new byte[BUFFER_SIZE];
    //        int bytesRead;
    //        while ((bytesRead = in.read(dataBuffer, 0, BUFFER_SIZE)) != -1) {
    //            fileOutputStream.write(dataBuffer, 0, bytesRead);
    //        }
    //    }
    //}
    //
    //public static void validateFile(File file, ArtifactType type) {
    //    boolean valid = true;
    //    switch (type) {
    //        case VERSIONS:
    //            valid = FileValidator.validate(file, FileValidator.Type.JSON);
    //            break;
    //        case ARTIFACT:
    //            valid = FileValidator.validate(file, FileValidator.Type.ZIP);
    //        default:
    //            break;
    //    }
    //    if (!valid) {
    //        logger.errorf("Invalid %s file %s", type, file.getAbsolutePath());
    //        FileUtils.delete(file);
    //    }
    //}

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
}
