package jext.sourcecode.project.csharp;

import jext.compress.Archives;
import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Versions;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.util.FileValidator;
import jext.util.FileUtils;
import jext.util.JSONUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NuGetDownloader implements LibraryDownloader {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(NuGetDownloader.class);
    private static final int BUFFER_SIZE = 1024;
    private static final String PACKAGE_ADDRESS_BASE = "PackageBaseAddress/3.0.0";

    private String name = "nuget";
    private String nugetUrl = "https://api.nuget.org/v3/index.json";
    private String packageAddressBase;

    private File downloadDirectory = new File(".nuget");
    private long downloadTimeout = 500;
    private long checkTimeout = 24L*60*60*1000L;   // 1 day in milliseconds
    private int  parallelDownloads = 5;

    private enum ArtifactType {
        VERSIONS,
        ARTIFACT,
        INVALID
    }

    private static class ServiceIndex extends HashMap<String, Object> {

        String getValue(String key) {
            List<Map<String, Object>> resources = (List<Map<String, Object>>) get("resources");
            for (Map<String, Object> entry : resources) {
                String type = (String) entry.get("@type");
                if (key.equals(type))
                    return (String) entry.get("@id");
            }

            throw new RuntimeException("Invalid key " + key);
        }
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public NuGetDownloader() {

    }

    // ----------------------------------------------------------------------
    // Configuration
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

    public void addLocalDirectory(File localdir) {
        // not used for now
    }

    @Override
    public void addRepository(String name, String url) {
        this.name = name;
        this.nugetUrl = url;
    }

    // ----------------------------------------------------------------------

    public void initialize() {
        createDownloadDirectory();
        retrieveServiceIndex();
    }
    
    private void createDownloadDirectory() {
        if (!downloadDirectory.exists())
            FileUtils.mkdirs(downloadDirectory);
    }

    private void retrieveServiceIndex() {
        try {
            File serviceIndexFile = new File(downloadDirectory, "serviceIndex.json");
            if (!serviceIndexFile.exists())
                downloadFile(nugetUrl, serviceIndexFile);
            if (!serviceIndexFile.exists())
                throw new FileNotFoundException(serviceIndexFile.getAbsolutePath());

            ServiceIndex serviceIndex = JSONUtils.load(serviceIndexFile, ServiceIndex.class);
            packageAddressBase = serviceIndex.getValue(PACKAGE_ADDRESS_BASE);
        }
        catch (IOException e) {
            logger.error (e, e);
        }
    }

    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LibraryDownloader newDownloader() {
        NuGetDownloader downloader = new NuGetDownloader();
        downloader.setDownloadTimeout(downloadTimeout);
        downloader.setCheckTimeout(checkTimeout);
        downloader.setParallelDownloads(parallelDownloads);
        downloader.setDownloadDirectory(downloadDirectory);
        downloader.addRepository(name, nugetUrl);
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
        return coords.version;
    }

    // ----------------------------------------------------------------------
    // Operations/2
    // ----------------------------------------------------------------------

    public Versions getArtifactVersions(MavenCoords coords) throws IOException {
        // the package can not exist
        // the file can be corrupted
        Versions versions = new Versions();

        File versionsFile  = composeFile(coords, ArtifactType.VERSIONS);
        String versionsUrl = composeUrl(coords, ArtifactType.VERSIONS);

        if (!versionsFile.exists() || isObsolete(versionsFile)) {
            downloadFile(versionsUrl, versionsFile);
            validateFile(versionsFile, ArtifactType.VERSIONS);
        }

        /*
            {
                "versions": [
                    "3.5.8",
                    "4.0.1",
                    "4.0.2",
                    "6.0.1-beta1",
                    "7.0.1-beta1",
                    "7.0.1-beta2",
                    "7.0.1-beta3",
                    "7.0.1",
                    "13.0.1",
                    "13.0.2-beta1",
                    "13.0.2-beta2"
                ]
            }
         */

        // check if the file exists -> return the empty list of versions
        if (!versionsFile.exists())
            return versions;

        Map<String, Object> jversions = JSONUtils.load(versionsFile, HashMap.class);
        List<String> vlist = (List<String>) jversions.get("versions");
        for(String version : vlist)
            versions.add(version);

        return versions;
    }

    private boolean isObsolete(File file) {
        long currentTimestamp = System.currentTimeMillis();
        long fileTimestamp = file.lastModified();
        return (currentTimestamp - fileTimestamp) > checkTimeout;
    }

    // ----------------------------------------------------------------------

    public Optional<File> downloadArtifact(MavenCoords coords) {
        File  artifactFile = composeFile(coords, ArtifactType.ARTIFACT);
        String artifactUrl = composeUrl(coords, ArtifactType.ARTIFACT);

        if (!artifactFile.exists()) {
            downloadFile(artifactUrl, artifactFile);
            validateFile(artifactFile, ArtifactType.ARTIFACT);
        }
        uncompressArtifact(artifactFile);

        return artifactFile.exists() ? Optional.of(artifactFile) : Optional.empty();
    }

    private static void uncompressArtifact(File artifactFile) {
        File libDirectory = new File(artifactFile.getParentFile(), "lib");
        if (!artifactFile.exists() ||  libDirectory.exists())
            return;

        File artifactDirectory = artifactFile.getParentFile();
        try {
            Archives.uncompress(artifactFile, artifactDirectory);
        } catch (IOException e) {
            logger.error("Unable to uncompress " + artifactFile);
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private File composeFile(MavenCoords coords, ArtifactType type) {
        String relativePath = "";
        switch (type) {
            case VERSIONS:
                relativePath = String.format("%1$s/versions.json",
                        coords.artifactId.toLowerCase());
                break;
            case ARTIFACT:
                relativePath = String.format("%1$s/%2$s/%1$s.%2$s.nupkg",
                        coords.artifactId.toLowerCase(),
                        coords.version.toLowerCase());
                break;
            case INVALID:
                relativePath = String.format("%1$s/%2$s/%1$s.%2$s.nupkg.invalid",
                        coords.artifactId.toLowerCase(),
                        coords.version.toLowerCase());
                break;
            default:
                throw new UnsupportedOperationException(type.toString());
        }

        return new File(downloadDirectory, relativePath);
    }

    private String composeUrl(MavenCoords coords, ArtifactType type) {
        String url;
        switch (type) {
            case VERSIONS:
                url = String.format("%1$s/%2$s/index.json",
                        packageAddressBase,
                        coords.artifactId.toLowerCase());
                break;
            case ARTIFACT:
                url =  String.format("%1$s/%2$s/%3$s/%2$s.%3$s.nupkg",
                        packageAddressBase,
                        coords.artifactId.toLowerCase(),
                        coords.version.toLowerCase());
                break;
            default:
                throw new UnsupportedOperationException(type.toString());
        }

        return normalizeUrl(url);
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

    public static void validateFile(File file, ArtifactType type) {
        boolean valid = true;
        switch (type) {
            case VERSIONS:
                valid = FileValidator.validate(file, FileValidator.Type.JSON);
                break;
            case ARTIFACT:
                valid = FileValidator.validate(file, FileValidator.Type.ZIP);
            default:
                break;
        }
        if (!valid) {
            logger.errorf("Invalid %s file %s", type, file.getAbsolutePath());
            FileUtils.delete(file);
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
