package jext.buildtools.maven;

import jext.exception.InvalidValueException;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.xml.XPathUtils;
import org.w3c.dom.Element;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MavenDownloader implements MavenConst {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    public static Logger logger = Logger.getLogger(MavenDownloader.class);

    // ----------------------------------------------------------------------

    /** List of repositories to try */
    private final Set<String> repoUrls = new HashSet<>();

    /** Directory where to download the files */
    private File downloadDir = new File(".m2/repository");

    /** wait a little between two consecutive downloads (milliseconds) */
    private long downloadTimeout = 500;

    /** time between two checks of the same file (metadata & versions) (milliseconds) */
    private long checkTimeout = 24*60*60*1000;

    // ----------------------------------------------------------------------

    // <a href="2.0.0/" title="2.0.0/">2.0.0/</a> 2015-01-13 13:40         -
    // to extract the version AND the yyy-mm-dd
    private static final Pattern HREF_VERSION =
        Pattern.compile("<a href=\"([^\"]+)/\" title=\"[^\"]+\">.*</a>\\s+([0-9\\-]+).*");

    // timestamp last download
    private long downloadTimestamp = System.currentTimeMillis();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public MavenDownloader() {
        repoUrls.add("https://repo.maven.apache.org/maven2");
    }

    public MavenDownloader newDownloader() {
        return new MavenDownloader()
            .setDownload(downloadDir)
            .addRepositories(repoUrls)
            .setDownloadTimeout(downloadTimeout)
            .setCheckTimeout(checkTimeout)
            ;
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public MavenDownloader setDownload(File downloadDir) {
        this.downloadDir = downloadDir;
        return this;
    }

    public MavenDownloader addRepository(String repoUrl) {
        if (repoUrl.startsWith("http"))
            this.repoUrls.add(repoUrl);
        else
            logger.errorf("Unsupported Maven repository %s", repoUrl);
        return this;
    }

    public MavenDownloader addRepositories(Collection<String> repoUrls) {
        repoUrls.forEach(this::addRepository);
        return this;
    }

    public MavenDownloader setDownloadTimeout(long millis) {
        this.downloadTimeout = millis;
        return this;
    }

    public MavenDownloader setCheckTimeout(long millis) {
        this.checkTimeout = millis;
        return this;
    }

    public File getDownloadDir() {
        return downloadDir;
    }

    public MavenDownloader initialize() {
        if (!downloadDir.exists() && !downloadDir.mkdirs())
            logger.warnf("Unable to create directory %s", downloadDir);
        return this;
    }

    // ----------------------------------------------------------------------
    // Compatibility
    // ----------------------------------------------------------------------

    public boolean isPomPackaging(MavenCoords coords) {
        coords = normalize(coords);

        MavenPom pom = getPom(coords);
        return pom != null && pom.isPomPackaging();
    }

    public File getPomFile(MavenCoords coords) {
        coords = normalize(coords);

        File pomFile = getFile(coords, MavenType.POM);
        if (!pomFile.exists())
            downloadFile(coords, MavenType.POM);
        return pomFile;
    }

    public File getJarFile(MavenCoords coords) {
        coords = normalize(coords);

        File jarFile = getFile(coords, MavenType.JAR);
        if (!jarFile.exists())
            downloadFile(coords, MavenType.JAR);
        return jarFile;
    }

    // ----------------------------------------------------------------------
    // Version & Relocation
    // ----------------------------------------------------------------------

    /** Attach the version if not already present */
    public MavenCoords getVersioned(MavenCoords coords) {
        if (coords.hasVersion())
            return coords;
        String version = getLatestVersion(coords);
        return coords.merge(version);
    }

    /** Relocate the coordinates */
    public MavenCoords getRelocated(MavenCoords coords) {
        MavenPom pom = getPom_(coords);
        if (pom == null)
            return coords;
        else if (pom.isRelocated())
            return pom.getRelocated();
        else
            return coords;
    }

    private MavenCoords normalize(MavenCoords coords) {
        if (!coords.hasVersion())
            coords = getVersioned(coords);
        if (!coords.hasVersion())
            return coords;
        coords = getRelocated(coords);
        return coords;
    }

    // ----------------------------------------------------------------------
    // Components & Dependencies
    // ----------------------------------------------------------------------

    static class Entry {
        final MavenDep dcoords;
        final int depth;
        Entry(MavenDep dcoords, int d) {
            this.dcoords = dcoords;
            this.depth = d;
        }
    }

    /**
     * If the artifact is a 'jar', itself, if it is a 'pom', the list of
     * components coords (recursively)
     *
     * @param depth = 0 -> no recursion
     *              > 0 -> max depth
     *              < 0 -> infinite recursion
     */
    public List<MavenCoords> getComponentsCoords(MavenCoords coords, int depth) {
        coords = getVersioned(coords);

        if (!coords.hasVersion()) {
            logger.errorf("Missing version in %s", coords);
            return Collections.emptyList();
        }

        // check for 'relocated' coords
        MavenPom pom = getPom(coords);
        if (pom == null)
            return Collections.emptyList();

        List<MavenCoords> components = new ArrayList<>();

        // 1) is a 'jar'
        if (!pom.isPomPackaging()) {
            components.add(coords);
            return components;
        }

        // 2) is a 'pom' but not 'recursive'
        if (depth == 0)
            return pom.getComponents()
                .stream()
                .map(compo -> compo.coords)
                .collect(Collectors.toList());

        // 3) is a 'pom' and 'recursive' - breadth first (queue)
        Queue<Entry> toVisit = new LinkedList<>();
        toVisit.add(new Entry(new MavenDep(coords), 0));
        Set<MavenDep> visited = new HashSet<>();

        while(!toVisit.isEmpty()) {
            Entry entry = toVisit.remove();
            int edepth = entry.depth;

            if (edepth > depth)
                continue;

            // remove from the queue
            MavenDep dcoords = entry.dcoords;
            // if already visited, skip
            if (visited.contains(dcoords))
                continue;

            // check for relocated pom
            pom = getPom(dcoords.coords);
            if (pom == null)
                continue;

            // if 'pom', add components to toVisit
            // if 'jar', add to visited
            else if (pom.isPomPackaging())
                pom.getComponents()
                .forEach(compo -> {
                    toVisit.add(new Entry(compo, edepth+1));
                });
            else
                visited.add(dcoords);
        }

        return visited.stream()
            .map(dcoords -> dcoords.coords)
            .collect(Collectors.toList());
    }

    /**
     * List of 'dependencies'
     */
    public List<MavenCoords> getDependencies(MavenCoords coords, int depth) {
        coords = getVersioned(coords);

        if (!coords.hasVersion()) {
            logger.errorf("Missing version in %s", coords);
            return Collections.emptyList();
        }

        // 1) not recursive
        MavenPom pom = getPom(coords);
        if (pom == null)
            return Collections.emptyList();

        if (depth == 0)
            return pom.getDependencies()
                .stream()
                .map(dcoords -> dcoords.coords)
                .collect(Collectors.toList());

        // 2) recursive

        Queue<Entry> toVisit = new LinkedList<>();
        toVisit.add(new Entry(new MavenDep(coords), 0));
        Set<MavenDep> visited = new HashSet<>();

        while (!toVisit.isEmpty()) {
            Entry entry = toVisit.remove();
            int edepth = entry.depth;

            if (edepth > depth)
                continue;

            MavenDep dcoords = entry.dcoords;

            // if already visited, skip
            if (visited.contains(dcoords))
                continue;

            // if scope is NOT compile and depth > 0, skip
            if (edepth > 0 && !dcoords.scopeCompile())
                continue;

            pom = getPom(dcoords.coords);
            if (pom == null) {
                continue;
            }
            else if (pom.isPomPackaging()) {
                pom.getComponents()
                    .forEach(dep -> {
                        toVisit.add(new Entry(dep, edepth+1));
                    });
            }
            else {
                visited.add(dcoords);
            }

            pom.getDependencies()
                .forEach(dep -> {
                    toVisit.add(new Entry(dep, edepth+1));
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

    public List<File> getArtifacts(MavenCoords coords) {
        return getArtifacts(Collections.singletonList(coords));
    }

    public List<File> getArtifacts(List<MavenCoords> coordsList) {
        List<File> artifacts = new ArrayList<>();
        for(MavenCoords coords : coordsList) {
            MavenPom pom = getPom(coords);
            if (pom == null)
                continue;
            if (pom.isPomPackaging())
                continue;

            File artifact = getArtifact(coords);
            if (artifact.exists())
                artifacts.add(artifact);
            else
                logger.warnf("Artifact for %s doesn't exists", coords);
        }
        return artifacts;
    }

    public File getArtifact(MavenCoords coords) {
        // DEBUG
        if (coords.toString().contains("all-themes"))
            System.out.println("DEBUG all-themes");

        coords = normalize(coords);

        File artifact = getFile(coords, MavenType.JAR);
        if (!artifact.exists())
            downloadFile(coords, MavenType.JAR);
        return artifact;
    }

    @Nullable
    public MavenPom getPom(MavenCoords coords) {
        if (coords.toString().contains("all-th"))
            System.out.println("DEBUG");
        coords = normalize(coords);
        return getPom_(coords);
    }

    private MavenPom getPom_(MavenCoords coords) {
        if (!coords.hasVersion())
            throw new InvalidValueException(VERSION, coords.toString());

        File pomFile = getFile(coords, MavenType.POM);
        if (!pomFile.exists())
            downloadFile(coords, MavenType.POM);
        if (!pomFile.exists()) {
            //logger.errorf("Unable to find POM file for %s", coords);
            return null;
        }

        MavenPom pom = new MavenPom(pomFile, this);
        return pom;

        // if (!relocate)
        //     return pom;
        //
        // if (!pom.isRelocated())
        //     return pom;
        //
        // MavenCoords start = coords;
        // int relocated = 0;
        // while (pom != null && pom.isRelocated() && relocated < 3) {
        //     coords = pom.getRelocated();
        //     pom = getPom(coords);
        //     if (++relocated >= 3)
        //         logger.warnf("More than 3 relocations for %s", start);
        // }
        // return pom;
    }

    /**
     * Latest version or "" if it is not possible to retrieve a value
     */
    public String getLatestVersion(MavenCoords coords) {
        File metadataFile = getFile(coords, MavenType.METADATA);
        if (recheck(metadataFile))
            downloadFile(coords, MavenType.METADATA);
        if (metadataFile.exists())
            return findMetadataVersion(metadataFile);

        return NO_VERSION;
    }

    private String findMetadataVersion(File metadataFile) {
        /*
            <metadata>
                ...
                <versioning>
                    <latest>1.78</latest>           // optional
                    <release>1.78</release>         // optional
                    <versions>
                        <version>1.0.3</version>
                        <version>1.2.0</version>    // latest
                    </versions>
                </versioning>
            </metadata>

            The latest version in the last entry in <versions>
        */
        try {
            Element metadata = XPathUtils.parse(metadataFile).getDocumentElement();

            String[] latestVersion = new String[]{ NO_VERSION };

            XPathUtils.getValues(metadata, "versioning/versions/version").forEach(version -> {
                latestVersion[0] = version;
            });

            return latestVersion[0];

        } catch (Exception e) {
            logger.errorf("%s: %s", metadataFile, e);
            return NO_VERSION;
        }
    }

    /**
     * List of available versions and publishing date
     * @param coords maven coordinates
     */
    public Versions getVersions(MavenCoords coords) {
        Versions versions = new Versions();

        File versionsFile = getFile(coords, MavenType.VERSIONS);
        if (recheck(versionsFile))
            downloadFile(coords, MavenType.VERSIONS);
        if (!versionsFile.exists())
            return versions;

        FileUtils.toStrings(versionsFile)
            .forEach(href -> {
                String version = null;
                String year = null;
                Matcher m = HREF_VERSION.matcher(href);

                if (m.matches()) {
                    version = m.group(1);
                    year = m.group(2);
                }

                // skip the version if the year is "-"
                if (MavenCoords.isValid(version) && MavenCoords.isValid(year))
                    versions.add(version, year);
            });

        return versions;
    }

    // private String findVersionsVersion(File versionsFile) {
    //     // convert the file in a list of string and parse each line to extract the version and the date.
    //     //
    //     // The version file has the structure:
    //     //
    //     //      ...
    //     //      <a href="2.0.0/" title="2.0.0/">2.0.0/</a> 2015-01-13 13:40 ...
    //     //
    //     // The latest version is the last version in the file
    //     //
    //     String[] latestVersion = new String[]{ UNKNOWN_VERSION };
    //
    //     FileUtils.toStrings(versionsFile)
    //         .forEach(href -> {
    //             String version = null;
    //             String year = null;
    //             Matcher m = HREF_VERSION.matcher(href);
    //             if (m.matches()) {
    //                 version = m.group(1);
    //                 year = m.group(2);
    //             }
    //             // skip the version if the year is "-"
    //             if (version != null && version.length() > 0 && year != null && year.length() > 1)
    //                 latestVersion[0] = version;
    //         });
    //
    //     return latestVersion[0];
    // }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private boolean recheck(File file) {
        return !file.exists() || (System.currentTimeMillis() - file.lastModified()) > checkTimeout;
    }

    // ----------------------------------------------------------------------
    // MavenCoords -> File & Url
    // ----------------------------------------------------------------------

    private File getFile(MavenCoords coords, MavenType type) {
        String relativePath;
        String groupId = coords.groupId.replace('.', '/');
        String artifactId = coords.artifactId;
        String version = coords.version;

        switch (type) {
            case VERSIONS:
                relativePath = String.format("%1$s/%2$s/%2$s.html",
                    groupId,
                    artifactId);
                break;
            case METADATA:
                relativePath = String.format("%s/%s/maven-metadata.xml",
                    groupId,
                    artifactId);
                break;
            case POM:
                relativePath = String.format("%1$s/%2$s/%3$s/%2$s-%3$s.pom",
                    groupId,
                    artifactId,
                    version);
                break;
            case JAR:
                relativePath = String.format("%1$s/%2$s/%3$s/%2$s-%3$s.jar",
                    groupId,
                    artifactId, version);
                break;
            // case INVALID:
            //     relativePath = String.format("%s/%s/flag.invalid",
            //         groupId,
            //         artifactId);
            //     break;

            default:
                throw new UnsupportedOperationException(type.toString());
        }
        return new File(downloadDir, relativePath);
    }

    private File getNotValidFlagFile(MavenCoords coords, MavenType type) {
        File file = getFile(coords, type);
        return new File(file.getParentFile(), file.getName() + ".notvalid");
    }

    private String getUrl(String repoUrl, MavenCoords coords, MavenType type) {
        String groupId = coords.groupId.replace('.', '/');
        String artifactId = coords.artifactId;
        String version = coords.version;

        switch (type) {
            case VERSIONS:
                return String.format("%1$s/%2$s/%3$s",
                    repoUrl,
                    groupId,
                    artifactId);
            case METADATA:
                return String.format("%1$s/%2$s/%3$s/maven-metadata.xml",
                    repoUrl,
                    groupId,
                    artifactId);
            case POM:
                return String.format("%1$s/%2$s/%3$s/%4$s/%3$s-%4$s.pom",
                    repoUrl,
                    groupId,
                    artifactId,
                    version);
            case JAR:
                return String.format("%1$s/%2$s/%3$s/%4$s/%3$s-%4$s.jar",
                    repoUrl,
                    groupId,
                    artifactId,
                    version);

            default:
                throw new UnsupportedOperationException(type.toString());
        }
    }

    // ----------------------------------------------------------------------
    // Download file
    // ----------------------------------------------------------------------

    private void downloadFile(MavenCoords coords, MavenType type) {

        // if 'coords' are marked as 'invalid', skip (with timeout)
        if (isNotValidType(coords, type))
            return;

        String downloadUrl;
        URL urlc;

        File downloadedFile = getFile(coords, type);
        File tempFile = getTemp(downloadedFile);
        mkdirs(tempFile);

        for (String repoUrl : repoUrls) {
            try {
                downloadUrl = getUrl(repoUrl, coords, type);

                urlc = new URL(downloadUrl);

                delete(tempFile);

                try(ReadableByteChannel readableByteChannel = Channels.newChannel(urlc.openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                    FileChannel fileChannel = fileOutputStream.getChannel()) {
                    fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                    // it is NOT POSSIBLE to rename the file HERE because it is OPENED!!!!!
                }
                catch (FileNotFoundException e) {
                    // url not existent
                    continue;
                }
                catch (IOException e) {
                    String message = e.getMessage();
                    if (message.contains(" 403 ")) continue;
                    if (message.contains(" 401 ")) continue;
                    if (message.contains(" 301 ")) continue;
                    logger.errorf("%s: %s", downloadUrl, e);
                    continue;
                }
                catch (Throwable e) {
                    logger.errorf("%s: %s", downloadUrl, e);
                    continue;
                }

                if (!isValidFile(tempFile, type))
                    continue;

                renameTo(tempFile, downloadedFile);

                break;

            } catch (MalformedURLException e) {
                logger.error(e, e);
            }
            finally {
                delete(tempFile);
            }
        }

        // if it was not possible to download the file, mark 'coords' as invalid
        if (!downloadedFile.exists()) {
            logger.errorf("Unable to download file %s for %s", type, coords);
            markAsNotValidType(coords, type);
        }
    }

    // ----------------------------------------------------------------------
    // Private Static methods
    // ----------------------------------------------------------------------

    private boolean isNotValidType(MavenCoords coords, MavenType type) {
        File flagNotValid = getNotValidFlagFile(coords, type);
        if (!flagNotValid.exists())
            return false;

        if ((System.currentTimeMillis() - flagNotValid.lastModified()) < checkTimeout)
            return true;

        delete(flagNotValid);
        return false;
    }

    private void markAsNotValidType(MavenCoords coords, MavenType type) {
        File flagNotValid = getNotValidFlagFile(coords, type);
        try(Writer wrt = new FileWriter(flagNotValid)) {
            wrt.write("notvalid " + new Date());
        }
        catch(Exception e) { }
    }

    // ----------------------------------------------------------------------
    // Private Static methods
    // ----------------------------------------------------------------------

    private static boolean isValidFile(File file, MavenType type) {
        // file not existent
        if (!file.exists())
            return false;

        // empty file
        if (file.length() == 0)
            return false;

        // not a jar
        if (type == MavenType.JAR && !"PK".equals(getBytes(file, 2)))
            return false;

        if (type == MavenType.POM) {
            try {
                Element root = XPathUtils.parse(file).getDocumentElement();
                return "project".equals(root.getTagName());
            }
            catch (Throwable t) {
                return false;
            }
        }

        if (type == MavenType.METADATA) {
            try {
                Element root = XPathUtils.parse(file).getDocumentElement();
                return "metadata".equals(root.getTagName());
            }
            catch (Throwable t) {
                return false;
            }
        }

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
            logger.errorf("Unable to create the directory %s", parentDir);
    }

    private static void delete(File file) {
        if (file != null)
            file.delete();
    }

    private static void renameTo(File fromFile,  File toFile) {
        toFile.delete();
        fromFile.renameTo(toFile);
    }

    private static String getBytes(File file, int len) {
        try(InputStream stream = new FileInputStream(file)) {
            byte[] b = new byte[len];
            int r = stream.read(b);
            return new String(b, 0, r, Charset.defaultCharset());
        }
        catch (Exception e) {
            logger.error(e, e);
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
            logger.errorf("Unable to read %s: %s", file, e);
        }
        return lines;
    }
}
