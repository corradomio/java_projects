package jext.sourcecode.project.python;

import jext.compress.Archives;
import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Versions;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.python.util.PyPiResolver;
import jext.sourcecode.project.util.FileValidator;
import jext.util.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;

public class PyPiDownloader implements LibraryDownloader {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(PyPiDownloader.class);
    private static final int BUFFER_SIZE = 1024;

    private String name = "pypi";
    private String pypiUrl = "https://pypi.org/simple/";
    
    private File downloadDirectory = new File(".pip");
    private long downloadTimeout = 500;
    private long checkTimeout = 24L*60*60*1000L;   // 1 day in milliseconds
    private int  parallelDownloads = 5;

    private enum ArtifactType {
        VERSIONS,
        ARTIFACT,
        INVALID
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------
    
    public void setDownloadDirectory(File downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    public File getDownloadDirectory() {
        return downloadDirectory;
    }
    
    public void setDownloadTimeout(long timeout) {
        this.downloadTimeout = timeout;
    }
    
    public void setParallelDownloads(int parallel) {
        this.parallelDownloads = parallel;
    }
    
    public void setCheckTimeout(long checkTimeout) {
        this.checkTimeout = checkTimeout;
    }

    @Override
    public void addRepository(String name, String url) {
        this.name = name;
        this.pypiUrl = url;

        if (!this.pypiUrl.endsWith("/"))
            this.pypiUrl += "/";
        if (!this.pypiUrl.endsWith("simple/"))
            this.pypiUrl += "simple/";
    }
    
    public void addLocalDirectory(File localdir) {
        // not used for now
    }

    // ----------------------------------------------------------------------

    public void initialize() {
        createDownloadDirectory();
    }

    private void createDownloadDirectory() {
        if (!downloadDirectory.exists())
            FileUtils.mkdirs(downloadDirectory);
    }

    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LibraryDownloader newDownloader() {
        PyPiDownloader downloader = new PyPiDownloader();
        downloader.setDownloadTimeout(downloadTimeout);
        downloader.setCheckTimeout(checkTimeout);
        downloader.setParallelDownloads(parallelDownloads);
        downloader.setDownloadDirectory(downloadDirectory);
        downloader.addRepository(name, pypiUrl);
        downloader.initialize();
        return downloader;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void checkArtifacts(Collection<MavenCoords> artifacts, boolean parallel) {

        if (!parallel) {
            artifacts.forEach(coords -> downloadArtifact(coords));
        }
        else {
            (new Thread(() -> {
                artifacts.forEach(this::downloadArtifact);
            })).start();
        }
    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        Versions versions = getArtifactVersions(coords);
        return versions.isEmpty() ? "" : versions.getLatestVersion().get();
    }

    // ----------------------------------------------------------------------
    // Operations/2
    // ----------------------------------------------------------------------

    public Versions getArtifactVersions(MavenCoords coords) {
        // https://pypi.org/simple/pylibmc/

        File versionsFile  = composeFile(coords, ArtifactType.VERSIONS);
        String versionsUrl = composeUrl(coords, ArtifactType.VERSIONS);

        if (!versionsFile.exists() || isObsolete(versionsFile)) {
            downloadFile(versionsUrl, versionsFile);
            validateFile(versionsFile, ArtifactType.VERSIONS);
        }

        PyPiResolver resolver = new PyPiResolver(versionsFile);
        return resolver.getVersions();
    }

    private boolean isObsolete(File file) {
        long currentTimestamp = System.currentTimeMillis();
        long fileTimestamp = file.lastModified();
        return (currentTimestamp - fileTimestamp) > checkTimeout;
    }

    // ----------------------------------------------------------------------

    public Optional<File> downloadArtifact(MavenCoords coords) {
        File versionsFile  = composeFile(coords, ArtifactType.VERSIONS);
        String versionsUrl = composeUrl(coords, ArtifactType.VERSIONS);

        if (!versionsFile.exists() || isObsolete(versionsFile)) {
            downloadFile(versionsUrl, versionsFile);
            validateFile(versionsFile, ArtifactType.VERSIONS);
        }

        // If the exact artifact version is not available, we search for an
        // artifact with a version grater or smaller than the requested version
        // This because very often, in Python, it is specified a version that
        // is
        //
        //      <= version
        //      >= version
        //
        // and at the moment it seems not very useful to support a comparator
        // in the Maven coordinates.

        PyPiResolver resolver = new PyPiResolver(versionsFile);
        Optional<PyPiResolver.Info> artifactInfo = resolver.selectVersion(coords.version);

        if (!artifactInfo.isPresent()) {
            artifactInfo = resolver.selectNearest(coords.version);
            coords = MavenCoords.of(artifactInfo.get().name, artifactInfo.get().version);
        }

        if (!artifactInfo.isPresent())
            return Optional.empty();

        File artifactFile = composeFile(coords, artifactInfo.get());
        if (artifactFile.exists())
            return Optional.of(artifactFile);

        downloadFile(artifactInfo.get().url, artifactFile);
        validateFile(artifactFile, ArtifactType.ARTIFACT);
        uncompressArtifact(artifactFile);

        if (!artifactFile.exists())
            return Optional.empty();
        else
            return Optional.of(artifactFile);
    }

    private static void uncompressArtifact(File artifactFile) {
        if (!artifactFile.exists())
            return;

        File artifactDirectory = artifactFile.getParentFile();
        File[] dircontent = artifactDirectory.listFiles();
        if (dircontent == null || dircontent.length > 1)
            return;

        try {
            Archives.uncompress(artifactFile, artifactDirectory);
        } catch (IOException e) {
            logger.error("Unable to uncompress " + artifactFile);
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private String composeUrl(MavenCoords coords, ArtifactType type) {
        String url;
        switch (type) {
            case VERSIONS:
                url = String.format("%1$s/%2$s",
                        pypiUrl,
                        coords.artifactId.toLowerCase());
                break;
            case ARTIFACT:
                url = String.format("%1$s/%2$s/%2$s-%3$s.tar.gz",
                        pypiUrl,
                        coords.artifactId.toLowerCase(),
                        coords.version.toLowerCase());
            default:
                throw new UnsupportedOperationException(type.toString());
        }

        return normalizeUrl(url);
    }

    private File composeFile(MavenCoords coords, ArtifactType type) {
        String relativePath = "";
        switch (type) {
            case VERSIONS:
                relativePath = String.format("%1$s/versions.html",
                        coords.artifactId.toLowerCase());
                break;
            case ARTIFACT:
                relativePath = String.format("%1$s/%2$s/%1$s.%2$s.tar.gz",
                        coords.artifactId.toLowerCase(),
                        coords.version.toLowerCase());
                break;
            case INVALID:
                relativePath = String.format("%1$s/%2$s/%1$s.%2$s.invalid",
                        coords.artifactId.toLowerCase(),
                        coords.version.toLowerCase());
                break;
            default:
                throw new UnsupportedOperationException(type.toString());
        }

        return new File(downloadDirectory, relativePath);
    }

    private File composeFile(MavenCoords coords, PyPiResolver.Info info) {
        String relativePath = String.format("%1$s/%2$s/%3$s",
                coords.artifactId.toLowerCase(),
                coords.version.toLowerCase(),
                info.name);

        return new File(downloadDirectory, relativePath);
    }

    private void downloadFile(String fileUrl, File filePath) {
        FileUtils.mkdirs(filePath.getParentFile());

        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] dataBuffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, BUFFER_SIZE)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    private static void validateFile(File file, ArtifactType type) {
        boolean valid = true;
        switch (type) {
            case VERSIONS:
                valid = FileValidator.validate(file, FileValidator.Type.HTML);
                break;
            case ARTIFACT:
                // tar.gz/whl/zip
                valid = FileValidator.validate(file);
            default:
                break;
        }
        if (!valid) {
            logger.errorf("Invalid %s file %s", type, file.getAbsolutePath());
            FileUtils.delete(file);
        }
    }

    private static String normalizeUrl(String url) {
        int p = url.indexOf("://") + 3;

        p = url.indexOf("//", p);
        while (p != -1) {
            url = url.substring(0, p) + url.substring(p+1);
            p = url.indexOf("//", p);
        }
        return url;
    }

    //private static String extractVersion(String versioned) {
    //    String version = versioned;
    //    int p;
    //    // networkx-0.34-py2.4.egg
    //    // networkx-0.34.tar.gz
    //    // networkx-0.34.win32.exe
    //    // networkx-1.0rc1-py2.4.egg
    //    // neo4j-4.0.0a1.tar.gz             alpha
    //    // neo4j-4.0.0b1.tar.gz             beta
    //    // neo4j-4.0.0rc1.tar.gz            release candidate
    //
    //    // remove prefix
    //    p = version.indexOf('-');
    //    version = version.substring(p+1);
    //
    //    // remove "-py2" | "-py3"
    //    version = sremove(version, "-py2");
    //    version = sremove(version, "-py3");
    //
    //    // remove "-cp2" | "-cp3"
    //    version = sremove(version, "-cp2");
    //    version = sremove(version, "-cp3");
    //
    //    // remove '.zip', '.tar.gz'
    //    version = sremove(version, ".zip");
    //    version = sremove(version, ".tar.gz");
    //    version = sremove(version, ".win32");
    //
    //    return version;
    //}

    //private static String sremove(String s, String t) {
    //    int p = s.indexOf(t);
    //    return p != -1 ? s.substring(0, p) : s;
    //}

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
