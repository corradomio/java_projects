package jext.maven;

import jext.cache.Cache;
import jext.cache.CacheManager;
import jext.exception.MultipleException;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.JarUtils;
import jext.util.StringUtils;
import jext.util.concurrent.Parallel;
import jext.util.concurrent.Serial;
import jext.xml.XPathUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MavenDownloader implements MavenConst {

    private static final String DOT_INVALID = ".invalid";

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger slog = Logger.getLogger(MavenDownloader.class);

    private Logger logger = Logger.getLogger(MavenDownloader.class);

    // ----------------------------------------------------------------------

    /** List of repositories to try */
    protected final Set<String> repoUrls = new HashSet<>();

    /**List of flatten directories where to search other missing libraries */
    protected final List<File> localDirs = new ArrayList<>();

    /** Directory where to download the files */
    protected File downloadDir = new File(".m2/repository");

    /** wait a little between two consecutive downloads (milliseconds) */
    protected long downloadTimeout = 500;

    /** time between two checks of the same file (metadata & versions) (seconds) */
    protected long checkTimeout = 24*60*60;

    /** parallel downloads */
    protected int parallelDownloads = 5;

    // ----------------------------------------------------------------------

    // <a href="2.0.0/" title="2.0.0/">2.0.0/</a> 2015-01-13 13:40         -
    // to extract the version AND the yyy-mm-dd
    private static final Pattern HREF_VERSION_TITLE =
            // Pattern.compile("<a href=\"([^\"]+)/\" title=\"[^\"]+\">.*</a>\\s+([0-9\\-]+).*")
            Pattern.compile("<a href=['\"]([^'\"]+)/['\"] title=['\"][^'\"]+['\"]>.*</a>.*")
            ;
    private static final Pattern HREF_VERSION_LINE =
            // Pattern.compile("<a href=\"([^\"]+)/\" title=\"[^\"]+\">.*</a>\\s+([0-9\\-]+).*")
            Pattern.compile(".*<a href=['\"][^'\"]+/['\"]>(.*)</a>.*")
            ;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     * Default Maven downloader. It uses the default repository
     */
    public MavenDownloader() {
        addRepository("https://repo.maven.apache.org/maven2");
    }

    // /**
    //  * Create a clone of the current downloader, useful to add new custom repositories
    //  */
    // public MavenDownloader newDownloader() {
    //     return new MavenDownloader()
    //         .setDownloadDirectory(downloadDir)
    //         .addRepositories(repoUrls)
    //         .addLocalDirectories(localDirs)
    //         .setDownloadTimeout(downloadTimeout)
    //         .setCheckTimeout(checkTimeout)
    //         .setParallelDownloads(parallelDownloads)
    //         .initialize()
    //         ;
    // }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    /** Directory where to download the artifacts */
    public void setDownloadDirectory(File downloadDir) {
        this.downloadDir = downloadDir;
    }

    // --

    /** Repository where to search the artifacts */
    public void addRepository(String repoUrl) {
        // remove the last "/"
        if (repoUrl.endsWith("/"))
            repoUrl = StringUtils.substring(repoUrl,0, -1);
        // check the protocol: for now it supports only "http(s)://"
        if (repoUrl.startsWith("http"))
            this.repoUrls.add(repoUrl);
        else
            logger.errorf("Unsupported Maven repository %s", repoUrl);
    }

    /** Repositories where to search the artifacts */
    public void addRepositories(Collection<String> repoUrls) {
        repoUrls.forEach(this::addRepository);
    }

    // --

    /** Directory where to search missing libraries */
    public void addLocalDirectory(File localDir) {
        this.localDirs.add(localDir);
    }

    /** Directories where to search missing libraries */
    public void addLocalDirectories(Collection<File> localDirs) {
        localDirs.forEach(this::addLocalDirectory);
    }

    // --

    /**  Timeout to wait before a connection starts */
    public void setDownloadTimeout(long millis) {
        this.downloadTimeout = millis;
    }

    /**
     * Sometimes a server doesn't works. In this case it is created a 'flag' file (*.invalid)
     * to skip another check the next time. However the flag is not forever. It has a expiration time
     * (for default 24 hours). After this time, it will be deleted.
     */
    public void setCheckTimeout(long seconds) {
        this.checkTimeout = seconds;
    }

    /** Parallel downloads permitted */
    public void setParallelDownloads(int parallelDownloads) {
        this.parallelDownloads = parallelDownloads;
    }

    // --

    /** Create download directory if it doesn't exist. */
    public void initialize() {
        if (!downloadDir.exists() && !downloadDir.mkdirs())
            logger.warnf("Unable to create the directory %s", downloadDir);
    }

    // ----------------------------------------------------------------------
    // Compatibility
    // ----------------------------------------------------------------------

    /**
     * Retrieve the '.pom' file from the coordinates
     */
    public File getPomFile(MavenCoords coords) {
        coords = normalize(coords);

        // compose the local file
        File pomFile = getFile(coords, MavenType.POM);
        // if (!pomFile.exists())
        //     downloadFile(coords, MavenType.POM);
        return pomFile;
    }

    /**
     * Retrieve the '.jar' file from the coordinates
     */
    public File getArtifact(MavenCoords coords) {
        coords = normalize(coords);

        Optional<File> localFile = getInFlattenDirs(coords, MavenType.ARTIFACT);
        if (localFile.isPresent())
            return localFile.get();

        // compose the local file
        File artifactFile = getFile(coords, MavenType.ARTIFACT);
        // if (!artifactFile.exists())
        //     downloadFile(coords, MavenType.ARTIFACT);

        // if the file is an '.aar' file, extract all '.jar's
        if (artifactFile.getName().endsWith(".aar"))
            artifactFile = JarUtils.extractJarFromAar(artifactFile);

        return artifactFile;
    }

    // ----------------------------------------------------------------------
    // Version & Relocation
    // ----------------------------------------------------------------------

    /** Attach the version if not already present */
    public MavenCoords getVersioned(MavenCoords coords) {
        if (coords.hasVersion())
            return coords;
        String version = getLatestVersion(coords);
        coords = coords.merge(version);
        return coords;
    }

    /** Relocate the coordinates */
    private MavenCoords getRelocated(MavenCoords coords) {
        MavenPom pom = getMavenPom(coords);
        if (MavenPom.isInvalid(pom))
            return coords;
        else if (pom.isRelocated())
            return pom.getRelocated();
        else
            return coords;
    }

    /**
     * Normalize the Maven coordinates.
     * There are TWO things to check:
     *
     * 1) if 'coords' has the version attached
     * 2) it 'coords' is relocated!
     *
     * For this reason it is necessary to normalize ALL 'coords'
     */
    private MavenCoords normalize(MavenCoords coords) {
        Cache<MavenCoords, MavenCoords> cache = CacheManager.getCache("maven.normalizedCoords",
            MavenCoords.class, MavenCoords.class);
        return cache.get(coords, () -> {
            MavenCoords lcoords = coords;
            if (!lcoords.hasVersion())
                lcoords = getVersioned(lcoords);
            if (!lcoords.hasVersion())
                return lcoords;
            lcoords = getRelocated(lcoords);
            return lcoords;
        });
    }

    // ----------------------------------------------------------------------
    // Components & Dependencies
    // ----------------------------------------------------------------------

    // Used to scan at a maximum depth
    private static class Entry {
        final Entry parent;
        final MavenDependency dcoords;
        final int depth;

        Entry(MavenDependency dcoords) {
            this.parent = null;
            this.depth = 0;
            this.dcoords = dcoords;
        }
        Entry(MavenDependency dcoords, Entry parent) {
            this.parent = parent;
            this.dcoords = dcoords;
            this.depth = parent.depth+1;
        }
    }

    /**
     * List of 'dependencies'
     */
    public List<MavenCoords> getDependencies(MavenCoords coords, int maxDepth) {
        coords = getVersioned(coords);

        if (!coords.hasVersion()) {
            logger.warnf("Missing version in %s", coords);
            return Collections.emptyList();
        }

        // 1) not recursive
        final MavenPom pom = getPom(coords);
        if (MavenPom.isInvalid(pom))
            return Collections.emptyList();

        if (maxDepth == 0)
            return pom.getDependencies()
                .stream()
                .map(dcoords -> dcoords.coords)
                .collect(Collectors.toList());

        // 2) recursive
        Queue<Entry> toVisit = new LinkedList<>();
        toVisit.add(new Entry(new MavenDependency(coords)));
        Set<MavenDependency> visited = new HashSet<>();

        while (!toVisit.isEmpty()) {
            Entry entry = toVisit.remove();
            int edepth = entry.depth;

            if (edepth > maxDepth)
                continue;

            MavenDependency dcoords = entry.dcoords;

            // if already visited, skip
            if (visited.contains(dcoords))
                continue;

            // if scope is NOT compile and depth > 0, skip
            if (edepth > 0 && !dcoords.scopeCompile())
                continue;

            final MavenPom dpom = getPom(dcoords.coords);
            if (MavenPom.isInvalid(dpom)) {
                continue;
            }
            else if (dpom.isPomPackaging()) {
                dpom.getComponents()
                    .forEach(dep -> {
                        toVisit.add(new Entry(dep, entry));
                    });
            }
            else {
                visited.add(dcoords);
            }

            dpom.getDependencies()
                .forEach(dep -> {
                    toVisit.add(new Entry(dep, entry));
                });
        }

        return visited
            .stream()
            .map(dcoords -> dcoords.coords)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Artifacts
    // ----------------------------------------------------------------------

    /**
     * Retrieve the list of artifacts (list of files), contained inside the
     * library specified by coordinates.
     *
     * If packaging is 'jar' of the default, there is only one file.
     * If packaging is 'pom', the library is a collection of other libraries
     *
     * @param coords Maven coordinates
     * @return list of artifacts
     */
    public List<File> getArtifacts(MavenCoords coords) {
        return getArtifacts(Collections.singletonList(coords));
    }

    /**
     * List of artifacts realetd to the libraries list
     */
    public List<File> getArtifacts(List<MavenCoords> coordsList) {
        List<File> artifacts = new ArrayList<>();
        for(MavenCoords coords : coordsList) {
            MavenPom pom = getPom(coords);
            if (MavenPom.isInvalid(pom))
                continue;
            if (pom.isPomPackaging())
                continue;

            File artifactFile = getArtifact(coords);
            if (artifactFile.exists())
                artifacts.add(artifactFile);
            // else
            //     logger.warnf("Artifact for %s doesn't exists", coords);
        }
        return artifacts;
    }

    public MavenPom getPom(MavenCoords coords) {
        // normalize the coordinates
        coords = normalize(coords);
        // retrieve the MavenPom object
        return getMavenPom(coords);
    }

    // MavenPom object from the coordinates
    private MavenPom getMavenPom(MavenCoords coords) {
        if (!coords.hasVersion())
            return MavenPom.invalid();

        Cache<MavenCoords, MavenPom> cache = CacheManager.getCache("maven.pom", MavenCoords.class, MavenPom.class);
        MavenPom pom = cache.get(coords, () -> {
            // try {
                File pomFile = getFile(coords, MavenType.POM);
                if (!pomFile.exists())
                    downloadFile(coords, MavenType.POM);
                if (!pomFile.exists()) {
                    return MavenPom.invalid();
                }
                return new MavenPom(pomFile, this);
            // }
            // catch (Throwable t) {
            //     logger.error(t, t);
            //     return MavenPom.invalid();
            // }
        });

        return pom; // pom == MavenPom.invalid() ? null : pom;
    }

    /**
     * Maven coordinates for the latest version of the library
     */
    public MavenCoords getLatest(MavenCoords coords) {
        String latestVersion =  getLatestVersion(coords);
        if (!latestVersion.isEmpty())
            return MavenCoords.of(coords, latestVersion);
        else
            return coords;
    }

    /**
     * Latest version or "" if it is not possible to retrieve a value
     */
    public String getLatestVersion(MavenCoords coords) {
        File versionsFile = getFile(coords, MavenType.VERSIONS);
        Version latestVersions = findVersionsVersion(versionsFile);
        // if (versionsFile.exists())
        //     return findVersionsVersion(versionsFile);

        File metadataFile = getFile(coords, MavenType.METADATA);
        Version latestMetadata = findMetadataVersion(metadataFile);
        // if (metadataFile.exists())
        //     return findMetadataVersion(metadataFile);

        int cmp = latestMetadata.compareTo(latestVersions);
        if (cmp < 0 )
            return latestMetadata.get();
        else
            return latestVersions.get();
    }

    private Version findMetadataVersion(File metadataFile) {
        if (!metadataFile.exists())
            return Version.NO_VERSION;

        /*
            <metadata>
                ...
                <versioning>
                    <latest>1.78</latest>           // optional
                    <release>1.78</release>         // optional
                    <versions>
                        <version>1.0.3</version>
                        ...
                        <version>1.2.0</version>    // latest
                    </versions>
                </versioning>
            </metadata>

            The latest version in the last entry in <versions>
        */
        try {
            Element metadata = XPathUtils.parse(metadataFile).getDocumentElement();

            // check if "versioning/latest" is available
            String latestVersion = XPathUtils.getValue(metadata, "versioning/latest", NONE);
            if (!latestVersion.isEmpty())
                return Version.of(latestVersion);

            // check if "versioning/release" is available
            String releaseVersion = XPathUtils.getValue(metadata, "versioning/release", NONE);
            if (!releaseVersion.isEmpty())
                return Version.of(releaseVersion);

            // scan 'versioning/versions/version'
            // note: they can be NOT in ascendin ordered
            Versions versions = new Versions();
            XPathUtils.getValues(metadata, "versioning/versions/version").forEach(version -> {
                // skip versions that are not ""valid"" versions
                if (Version.isValid(version))
                    versions.add(version);
            });

            return versions.getLatestVersion();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            logger.errorf("%s: %s", metadataFile, e);
            return Version.NO_VERSION;
        }
    }

    private Version findVersionsVersion(File versionsFile) {
        if (!versionsFile.exists())
            return Version.NO_VERSION;

        /*
            <html>
                ...

            <a href="../">../</a>
            <a href="0.1/" title="0.1/">0.1/</a>   2005-09-20 05:46         -
            <a href="1.0/" title="1.0/">1.0/</a>   2005-09-20 05:46         -
            <a href="1.1/" title="1.1/">1.1/</a>   2005-10-11 00:02         -
            <a href="1.2/" title="1.2/">1.2/</a>   2006-03-20 01:31         -
                ...
            <a href="20030203.000550/" title="20030203.000550/">20030203.000550/</a>            -         -
            <a href="maven-metadata.xml" title="maven-metadata.xml">maven-metadata.xml</a>      2020-09-09 14:28       847

                ...
            </html>
         */

        Versions versions = new Versions();

        FileUtils.toStrings(versionsFile)
                .forEach(href -> {
                    String version;
                    Matcher m;

                    // check for  <a href="0.1/" title="0.1/">0.1/</a> ...
                    m = HREF_VERSION_TITLE.matcher(href);
                    if (m.matches()) {
                        version = stripSlash(m.group(1));
                        if (Version.isValid(version)) {
                            versions.add(version);
                            return;
                        }
                    }

                    // check for  ... <a href="0.1/">0.1/</a> ...
                    m = HREF_VERSION_LINE.matcher(href);
                    if (m.matches()) {
                        version = stripSlash(m.group(1));
                        if (Version.isValid(version)) {
                            versions.add(version);
                            return;
                        }
                    }

                });

        return versions.getLatestVersion();
    }

    private static String stripSlash(String v) {
        while (v.startsWith("/"))
            v = v.substring(1);
        while (v.endsWith("/"))
            v = v.substring(0, v.length()-1);
        return v;
    }

    // /**
    //  * List of available versions and publishing date
    //  * @param coords maven coordinates
    //  */
    // private Versions getVersions(MavenCoords coords) {
    //     /*
    //         <html>
    //             ...
    //
    //         <a href="../">../</a>
    //         <a href="0.1/" title="0.1/">0.1/</a>   2005-09-20 05:46         -
    //         <a href="1.0/" title="1.0/">1.0/</a>   2005-09-20 05:46         -
    //         <a href="1.1/" title="1.1/">1.1/</a>   2005-10-11 00:02         -
    //         <a href="1.2/" title="1.2/">1.2/</a>   2006-03-20 01:31         -
    //             ...
    //         <a href="20030203.000550/" title="20030203.000550/">20030203.000550/</a>            -         -
    //         <a href="maven-metadata.xml" title="maven-metadata.xml">maven-metadata.xml</a>      2020-09-09 14:28       847
    //
    //             ...
    //         </html>
    //      */
    //     Versions versions = new Versions();
    //
    //     File versionsFile = getFile(coords, MavenType.VERSIONS);
    //     // if (recheck(versionsFile))
    //     //     downloadFile(coords, MavenType.VERSIONS);
    //     if (!versionsFile.exists())
    //         return versions;
    //
    //     FileUtils.toStrings(versionsFile)
    //         .forEach(href -> {
    //             String version = null;
    //             String year = null;
    //             Matcher m = HREF_VERSION.matcher(href);
    //
    //             // check for
    //             //  <a href="0.1/" title="0.1/">0.1/</a>    2005-09-20 05:46         -
    //             if (m.matches()) {
    //                 version = m.group(1);
    //                 year = m.group(2);
    //             }
    //
    //             // skip if year is "-"
    //             if (MavenCoords.isValid(version) && MavenCoords.isValid(year))
    //                 versions.add(version, year);
    //         });
    //
    //     return versions;
    // }

    // private Versions readVersions(File versionsFile) {
    //     if (!versionsFile.exists())
    //         return Versions.noVersions;
    // }

    // ----------------------------------------------------------------------
    // MavenCoords -> File & Url
    // ----------------------------------------------------------------------

    private Optional<File> getInFlattenDirs(MavenCoords coords, MavenType type) {
        String relativePath = getRelativePath(coords, type);
        for (File flattenDir : localDirs) {
            File file = new File(flattenDir, relativePath);
            if (file.exists())
                return Optional.of(file);
        }
        return Optional.empty();
    }

    // Compose the file from the object type
    private File getFile(MavenCoords coords, MavenType type) {
        String relativePath = getRelativePath(coords, type);
        return new File(downloadDir, relativePath);
    }

    private String getRelativePath(MavenCoords coords, MavenType type) {
        String relativePath;
        String groupId = coords.groupId.replace('.', '/');
        String artifactId = coords.artifactId;
        String version = coords.version;

        switch (type) {
            case VERSIONS:
                // <groupId>/<artifactId>/<artifactId>.html
                relativePath = String.format("%1$s/%2$s/%2$s.html",
                    groupId,
                    artifactId);
                break;
            case METADATA:
                // <groupId>/<artifactId>/maven-metadata.xml
                relativePath = String.format("%s/%s/maven-metadata.xml",
                    groupId,
                    artifactId);
                break;
            case POM:
                // <groupId>/<artifactId>/<version>/<artifactId>-<version>.pom
                relativePath = String.format("%1$s/%2$s/%3$s/%2$s-%3$s.pom",
                    groupId,
                    artifactId,
                    version);
                break;
            case ARTIFACT:
                // <groupId>/<artifactId>/<version>/<artifactId>-<version>.<packaging>
                relativePath = String.format("%1$s/%2$s/%3$s/%2$s-%3$s.%4$s",
                    groupId,
                    artifactId,
                    version,
                    getPackaging(coords));
                break;
            // case INVALID:
            //     relativePath = String.format("%s/%s/flag.invalid",
            //         groupId,
            //         artifactId);
            //     break;

            default:
                throw new UnsupportedOperationException(type.toString());
        }
        return relativePath;
    }

    // Invalid file flag:  '<file>.invalid'
    private File getInvalidFlagFile(MavenCoords coords, MavenType type) {
        File file = getFile(coords, type);
        return new File(file.getParentFile(), file.getName() + DOT_INVALID);
    }

    // Relative artifact's url from the coordinates
    private String getUrl(String repoUrl, MavenCoords coords, MavenType type) {
        String groupId = coords.groupId.replace('.', '/');
        String artifactId = coords.artifactId;
        String version = coords.version;

        switch (type) {
            case VERSIONS:
                // <repository>/<groupId>/<artifactId>
                return String.format("%1$s/%2$s/%3$s",
                    repoUrl,
                    groupId,
                    artifactId);
            case METADATA:
                // <repository>/<groupId>/<artifactId>/maven-metadata.xml
                return String.format("%1$s/%2$s/%3$s/maven-metadata.xml",
                    repoUrl,
                    groupId,
                    artifactId);
            case POM:
                // <repository>/<groupId>/<artifactId>/<version>/<artifactId>-<version>.pom
                return String.format("%1$s/%2$s/%3$s/%4$s/%3$s-%4$s.pom",
                    repoUrl,
                    groupId,
                    artifactId,
                    version);
            case ARTIFACT:
                // <repository>/<groupId>/<artifactId>/<version>/<artifactId>-<version>.<packaging>
                return String.format("%1$s/%2$s/%3$s/%4$s/%3$s-%4$s.%5$s",
                    repoUrl,
                    groupId, artifactId, version,
                    getPackaging(coords));

            default:
                throw new UnsupportedOperationException(type.toString());
        }
    }

    private String getPackaging(MavenCoords coords) {
        MavenPom pom = getPom(coords);
        return pom.getPackaging();
    }

    // ----------------------------------------------------------------------
    // Download file
    // ----------------------------------------------------------------------

    private MultipleException downloadFile(MavenCoords coords, MavenType type) {

        MultipleException excpt = new MultipleException(String.format("Unable to download %s for %s", type, coords));

        // if 'coords' are marked as 'invalid', skip then (with timeout)
        if (isInvalidType(coords, type)) {
            excpt.addCause(new RuntimeException("Invalid Maven coordinates: " + coords.toString()));
            logger.warnc(coords.toString(), "Maven artifact %s invalid (because marked with '.invalid' flag file)", coords);
            return excpt;
        }

        File downloadedFile = getFile(coords, type);

        // if the ".pom" file defined a "downloadUrl", it downloads from this url
        if (type == MavenType.ARTIFACT && hasDownloadUrl(coords)) {
            // if the download is failed, try to download with the standard method
            if(!downloadUsingUrl(coords, excpt))
                downloadFromRepositories(coords, type, excpt);
        }
        else
            downloadFromRepositories(coords, type, excpt);

        // if it was not possible to download the file, mark 'coords' as invalid
        if (!downloadedFile.exists()) {
            //logger.errorf("Unable to download library %s for %s", type, coords);
            logger.error(excpt.getMessage());
            markAsInvalidType(coords, type);
        }

        return excpt;
    }

    private void downloadFromRepositories(MavenCoords coords, MavenType type, MultipleException excpt) {
        File downloadedFile = getFile(coords, type);
        File tempFile = getTemp(downloadedFile);

        for (String repoUrl : repoUrls) {
            try {
                String downloadUrl = getUrl(repoUrl, coords, type);

                try {
                    downloadFromUrl(downloadedFile, downloadUrl);
                } catch (IOException e) {
                    excpt.addCause(e);
                    continue;
                } catch (Throwable e) {
                    excpt.addCause(e);
                    break;
                }

                // check if the downloaded file has the correct type
                if (!isValidFileType(tempFile, type))
                    continue;

                // update the previous downloaded file
                renameTo(tempFile, downloadedFile);

                break;
            }
            finally {
                delete(tempFile);
            }
        }

        // if the file exists and it is invalid because too old, this means
        // that is was not possible to download the new one.
        // In this case, keep it and update the timestamp until the next
        // deadline
        if (downloadedFile.exists() && !isInvalidType(coords, type))
            touch(downloadedFile);
    }

    // Some pom files contain the "downloadUrl", the "url" where to download
    // the artifact
    private boolean hasDownloadUrl(MavenCoords coords) {
        MavenPom pom = getPom(coords);
        return pom != null && pom.hasDownloadUrl();
    }

    private boolean downloadUsingUrl(MavenCoords coords, MultipleException excpt) {
        MavenType type = MavenType.ARTIFACT;
        File downloadedFile = getFile(coords, type);
        File tempFile = getTemp(downloadedFile);

        MavenPom pom = getPom(coords);
        if (MavenPom.isInvalid(pom))
            return false;

        String downloadUrl = pom.getDownloadUrl();

        try {
            downloadFromUrl(downloadedFile, downloadUrl);

            // check if it is downloaded the correct file type
            if (!isValidFileType(tempFile, type))
                return false;

            renameTo(tempFile, downloadedFile);
        }
        catch (IOException e) {
            excpt.addCause(e);
        }
        finally {
            delete(tempFile);
        }

        return downloadedFile.exists();
    }

    private void downloadFromUrl(File downloadedFile, String downloadUrl) throws IOException {
        File tempFile = getTemp(downloadedFile);
        mkdirs(tempFile);
        delete(tempFile);

        // HttpClient is able to handle '301 Moved Permanently' !!!
        URL url = new URL(downloadUrl);

        logger.debugft("Try to download from %s", downloadUrl);

        RequestConfig globalConfig = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.DEFAULT)
            .build();
        CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(globalConfig)
            .build();
        RequestConfig localConfig = RequestConfig.copy(globalConfig)
            .setCookieSpec(CookieSpecs.STANDARD)
            .build();

        // HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(downloadUrl);
        httpGet.setConfig(localConfig);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() >= 400) {
            throw new IOException("Unable to download from " + downloadUrl + ": " + response.getStatusLine());
        }

        HttpEntity entity = response.getEntity();

        try (InputStream istream = entity.getContent()) {
            try (OutputStream ostream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                for(int len = istream.read(buffer); len > 0; len = istream.read(buffer)) {
                    ostream.write(buffer, 0, len);
                }
            }
        }
    }

    // ----------------------------------------------------------------------
    // Private Static methods
    // ----------------------------------------------------------------------

    // Check if there is the flag '.invalid' for the specified Maven type and
    // the validity interval of the flag hasn't expired.
    private boolean isInvalidType(MavenCoords coords, MavenType type) {
        File invalidFlagFile = getInvalidFlagFile(coords, type);
        if (!invalidFlagFile.exists())
            return false;

        long checkTimeoutMillis = this.checkTimeout*1000;
        if ((System.currentTimeMillis() - invalidFlagFile.lastModified()) < checkTimeoutMillis)
            return true;

        delete(invalidFlagFile);
        return false;
    }

    private void markAsInvalidType(MavenCoords coords, MavenType type) {
        File invalidFlagFile = getInvalidFlagFile(coords, type);
        try(Writer wrt = new FileWriter(invalidFlagFile)) {
            wrt.write("invalid " + new Date());
        }
        catch (Exception e) { }
    }

    // ----------------------------------------------------------------------
    // Private Static methods
    // ----------------------------------------------------------------------
    // Filesystem utility methods with checks
    //

    // used to check that the file downloaded is of the correct type.
    // Sometimes it is downloaded a file HTML without error codes
    private static boolean isValidFileType(File file, MavenType type) {
        if (file == null)
            return false;

        // file not existent
        if (!file.exists())
            return false;

        // empty file
        if (file.length() == 0)
            return false;

        // not a JAR (ZIP compressed) file
        if (type == MavenType.ARTIFACT && !"PK".equals(getBytes(file, 2)))
            return false;

        // not a XML POM file
        if (type == MavenType.POM) {
            try {
                Element root = XPathUtils.parse(file).getDocumentElement();
                return "project".equals(root.getTagName());
            }
            catch (ParserConfigurationException | IOException | SAXException t) {
                return false;
            }
        }

        // not a XML METADATA file
        if (type == MavenType.METADATA) {
            try {
                Element root = XPathUtils.parse(file).getDocumentElement();
                return "metadata".equals(root.getTagName());
            }
            catch (ParserConfigurationException | IOException | SAXException t) {
                return false;
            }
        }

        // not a HTML VERSIONS file
        if (type == MavenType.VERSIONS) {
            // file with less that 2 lines
            List<String> lines = readLines(file, 2);
            if (lines.size() < 2)
                return false;

            String l1 = lines.get(0);
            String l2 = lines.get(1);
            if (l1.startsWith("<html"))
                return true;
            if (l1.startsWith("<!DOC") && l2.startsWith("<html"))
                return true;
            else
                return false;
        }

        return true;
    }

    private static File getTemp(File file) {
        return new File(file.getParentFile(), file.getName() + ".temp");
    }

    private static void mkdirs(File file) {
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs())
            slog.errorf("Unable to create the directory %s", parentDir);
    }

    private static void delete(File file) {
        if (file != null)
            file.delete();
    }

    private static void renameTo(File fromFile,  File toFile) {
        toFile.delete();
        fromFile.renameTo(toFile);
    }

    private static void touch(File file) {
        file.setLastModified(System.currentTimeMillis());
    }

    private static String getBytes(File file, int len) {
        try(InputStream stream = new FileInputStream(file)) {
            byte[] b = new byte[len];
            int r = stream.read(b);
            if (r <= 1)
                return "";
            else
                return new String(b, 0, r, Charset.defaultCharset());
        }
        catch (Exception e) {
            slog.error(e, e);
            return "";
        }
    }

    private static List<String> readLines(File file, int nlines) {
        if (file == null || !file.exists())
            return Collections.emptyList();
        List<String> lines = new ArrayList<>();
        try(InputStream stream = new FileInputStream(file)) {
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            for(String line = r.readLine(); line != null && lines.size() != nlines; line = r.readLine()) {
                lines.add(line);
            }
        }
        catch (IOException e) {
            slog.errorf("Unable to read %s: %s", file, e);
        }
        return lines;
    }

    // ----------------------------------------------------------------------
    // Check artifacts
    // ----------------------------------------------------------------------

    /**
     * Try to download in parallel the artifacts:
     *
     *      [artifact].pom
     *      [artifact].jar  if it exists
     *
     * @param coordsList list of coordinates to check
     */
    public void checkArtifacts(Collection<MavenCoords> coordsList, boolean parallel) {
        logger.infof("Checking %d artifacts ...", coordsList.size());
        if (parallel)
            Parallel.forEach(coordsList, coords -> checkArtifact(coords));
        else
            Serial.forEach(coordsList, coords -> checkArtifact(coords));
        logger.infof("Checked %d artifacts", coordsList.size());
    }

    private void checkArtifact(MavenCoords coords) {

        MultipleException me = new MultipleException(String.format("Unable to download %s", coords));

        // .invalid'
        // deleteInvalidFile(coords, MavenType.METADATA);
        // deleteInvalidFile(coords, MavenType.VERSIONS);
        // deleteInvalidFile(coords, MavenType.POM);
        // deleteInvalidFile(coords, MavenType.ARTIFACT);

        // 'maven-metadata.xml'
        File metadataFile = getFile(coords, MavenType.METADATA);
        if (recheck(metadataFile))
            me.add(downloadFile(coords, MavenType.METADATA));

        // version file
        File versionsFile = getFile(coords, MavenType.VERSIONS);
        if (recheck(versionsFile))
            me.add(downloadFile(coords, MavenType.VERSIONS));

        // pom file
        File pomFile = getPomFile(coords);
        if (!isInvalid(pomFile) && !pomFile.exists())
            me.add(downloadFile(coords, MavenType.POM));

        // artifact
        File artifactFile = getArtifact(coords);
        if (!isInvalid(pomFile) && !artifactFile.exists())
            me.add(downloadFile(coords, MavenType.ARTIFACT));
    }

    private boolean isInvalid(File file) {
        File invalidFile = new File(file.getParent(), file.getName() + DOT_INVALID);
        if (!invalidFile.exists())
            return false;

        long checkTimeoutMillis = this.checkTimeout*1000;
        long delta = System.currentTimeMillis() - invalidFile.lastModified();
        if (delta < checkTimeoutMillis)
            return true;

        delete(invalidFile);
        return false;
    }

    // check if the file is expired
    private boolean recheck(File file) {
        if (isInvalid(file))
            return false;

        if (!file.exists())
            return true;

        long checkTimeoutMillis = this.checkTimeout*1000;
        long delta = System.currentTimeMillis() - file.lastModified();
        if (delta < checkTimeoutMillis)
            return false;
        else
            return true;
    }

    // private void deleteInvalidFile(MavenCoords coords, MavenType type) {
    //     File invalidFile = getInvalidFlagFile(coords, type);
    //     invalidFile.delete();
    // }

}
