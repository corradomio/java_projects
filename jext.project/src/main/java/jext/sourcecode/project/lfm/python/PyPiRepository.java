package jext.sourcecode.project.lfm.python;

import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.Versions;
import jext.sourcecode.project.util.BaseLibraryRepository;
import jext.sourcecode.project.util.FileValidator;
import jext.util.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class PyPiRepository extends BaseLibraryRepository {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(PyPiRepository.class);

    private static final String SIMPLE_URL = "https://pypi.org/simple/";
    private static final long MAX_VERSIONS_AGE = 24L*60*60*1000L;   // 1 day in milliseconds
    private static final int BUFFER_SIZE = 1024;

    private boolean initialized;
    private File pypiDirectory;

    private enum ArtifactType {
        VERSIONS,
        ARTIFACT,
        INVALID
    }

    private static class ArtifactList {

        private File versionsFile;

        ArtifactList(File versionsFile) {
            this.versionsFile = versionsFile;
        }

        Versions getVersions() {
            Versions versions = new Versions();

            /*
                <!DOCTYPE html>
                <html>
                  ...
                  <body>
                    <h1>Links for networkx</h1>
                    <a href="..." >networkx-0.34-py2.4.egg</a><br />
                    ...

                    </body>
                </html>
                <!--SERIAL 14836743-->
             */

            if (!versionsFile.exists())
                return versions;

            try {
                Document html = Jsoup.parse(versionsFile);
                Elements elts = html.body().children();

                for(Element elt : elts) {
                    if (elt.nodeName().equals("a")) {
                        String versioned = elt.ownText();
                        String version = extractVersion(versioned);
                        versions.add(version);
                    }
                }

            } catch (Exception e) {
                // already checked
            }

            return versions;
        }

        Optional<String> getUrl(String version) {
            Optional<String> url;
            // the version can not exist
            if (!versionsFile.exists())
                return Optional.empty();

            try {
                Document html = Jsoup.parse(versionsFile);

                return findUrl(html, version, ".tar.gz");

            } catch (Exception e) {
                // already checked
            }

            return Optional.empty();
        }
    }

    private static Optional<String> findUrl(Document html, String version, String ext) {
        Elements elts = html.body().children();
        for(Element elt : elts) {
            if (elt.nodeName().equals("a")) {
                String versioned = elt.ownText();
                String ver = extractVersion(versioned);
                if (!ver.equals(version))
                    continue;
                if (!versioned.endsWith(ext))
                    continue;

                String href = elt.attr("href");
                return Optional.of(href);
            }
        }

        return Optional.empty();
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PyPiRepository() {
        setInfo("PyPi", "https://pypi.org/");
        setDownloadDirectory(new File("."));
    }

    public void initialize() throws IOException {
        if (initialized)
            return;
        else
            initialized = true;

        createDownloadDirectory();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public Versions getArtifactVersions(MavenCoords coords) throws IOException {
        // https://pypi.org/simple/pylibmc/
        initialize();
        Versions versions = new Versions();

        File versionsFile  = composeFile(coords, ArtifactType.VERSIONS);
        String versionsUrl = composeUrl(coords, ArtifactType.VERSIONS);

        if (!versionsFile.exists() || isObsolete(versionsFile)) {
            downloadFile(versionsUrl, versionsFile);
            validateFile(versionsFile, ArtifactType.VERSIONS);
        }

        ArtifactList artifactList = new ArtifactList(versionsFile);
        return artifactList.getVersions();

    }

    private static boolean isObsolete(File file) {
        long currentTimestamp = System.currentTimeMillis();
        long fileTimestamp = file.lastModified();
        return (currentTimestamp - fileTimestamp) > MAX_VERSIONS_AGE;
    }

    private static String extractVersion(String versioned) {
        String version = versioned;
        int p;
        // networkx-0.34-py2.4.egg
        // networkx-0.34.tar.gz
        // networkx-0.34.win32.exe
        // networkx-1.0rc1-py2.4.egg
        // neo4j-4.0.0a1.tar.gz             alpha
        // neo4j-4.0.0b1.tar.gz             beta
        // neo4j-4.0.0rc1.tar.gz            release candidate

        // remove prefix
        p = version.indexOf('-');
        version = version.substring(p+1);

        // remove "-py2" | "-py3"
        version = sremove(version, "-py2");
        version = sremove(version, "-py3");

        // remove '.zip', '.tar.gz'
        version = sremove(version, ".zip");
        version = sremove(version, ".tar.gz");
        version = sremove(version, ".win32.exe");

        return version;
    }

    private static String sremove(String s, String t) {
        int p = s.indexOf(t);
        return p != -1 ? s.substring(0, p) : s;
    }

    // ----------------------------------------------------------------------

    public Optional<File> downloadArtifact(MavenCoords coords) throws IOException {
        File artifactFile  = composeFile(coords, ArtifactType.ARTIFACT);
        if (artifactFile.exists())
            return Optional.of(artifactFile);

        // the correct url is available in the HTML used to retrieve the artifact versions
        Versions versions = getArtifactVersions(coords);
        if (versions.isEmpty())
            return Optional.empty();

        File versionsFile  = composeFile(coords, ArtifactType.VERSIONS);
        ArtifactList artifactList = new ArtifactList(versionsFile);

        Optional<String> artifactUrl = artifactList.getUrl(coords.version);
        if (!artifactUrl.isPresent())
            return Optional.empty();

        downloadFile(artifactUrl.get(), artifactFile);
        validateFile(artifactFile, ArtifactType.ARTIFACT);

        return artifactFile.exists() ? Optional.of(artifactFile) : Optional.empty();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void createDownloadDirectory() {
        pypiDirectory = new File(download, ".pypi");
        if (!pypiDirectory.exists())
            FileUtils.mkdirs(pypiDirectory);
    }

    private String composeUrl(MavenCoords coords, ArtifactType type) {
        String url;
        switch (type) {
            case VERSIONS:
                url = String.format("%1$s/%2$s",
                        SIMPLE_URL,
                        coords.artifactId.toLowerCase());
                break;
            case ARTIFACT:
                url = String.format("%1$s/%2$s/%2$s-%3$s.tar.gz",
                        SIMPLE_URL,
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

        return new File(pypiDirectory, relativePath);
    }

    private void downloadFile(String fileUrl, File filePath) throws IOException {
        FileUtils.mkdirs(filePath.getParentFile());

        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] dataBuffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, BUFFER_SIZE)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
    }

    public static void validateFile(File file, ArtifactType type) {
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

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
