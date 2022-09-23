package jext.sourcecode.project.python.util;

import jext.logging.Logger;
import jext.maven.Version;
import jext.maven.Versions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.TreeSet;

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

/**
 * Read the content of the PyPi HTML versions file and resolves
 * list of versions or distribution file to use
 */
public class PyPiResolver {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(PyPiResolver.class);

    private final File versionsFile;
    private final Versions versions;
    private final String name;
    private Elements elts;

    public static class Info implements Comparable<Info> {
        public final String name;
        public final String version;
        public final String url;
        public final String versioned;

        private Info(String name, String version, String url, String versioned) {
            this.name = name;
            this.version = version;
            this.url = url;
            this.versioned = versioned;
        }

        // ----------------------------------------------------------------------

        @Override
        public int compareTo(Info o) {
            int cmp = name.compareTo(o.name);
            if (cmp == 0)
                cmp = version.compareTo(o.version);
            return cmp;
        }

        @Override
        public String toString() {
            return String.format("%s:%s", name, version);
        }
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PyPiResolver(Versions versions, File versionsFile) {
        if (versionsFile.isDirectory())
            versionsFile = new File(versionsFile, "versions.html");
        this.versionsFile = versionsFile;
        this.name = versionsFile.getParentFile().getName();
        this.versions = versions;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Return the list of available versions
     *
     * @return an object handling the list of available versions
     */
    public Versions getVersions() {
        populate();

        return versions;
    }

    /**
     * Check for the exact version
     * @param version
     * @return
     */
    public Optional<Info> selectVersion(String version) {
        populate();

        if (version.isEmpty() && !versions.isEmpty())
            version = versions.getLatestVersion().get();

        // scan for the .tar.gz
        // if not available, scan for '.zip'
        // if not available, scan for '.whl' for python3
        Optional<Info> info = findUrl(version, ".tar.gz");
        if (!info.isPresent())
            info = findUrl(version, ".zip");
        if (!info.isPresent())
            info = findUrl(version, ".whl");

        return info;
    }

    public Optional<Info> selectNearest(String version) {
        populate();

        Version selver = versions.select(version, "~");
        if (selver.isEmpty())
            return Optional.empty();
        else
            return selectVersion(selver.get());
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void populate() {
        if (elts != null)
            return;

        populateElts();
        populateVersions();
    }

    private void populateElts() {
        if (!versionsFile.exists()) {
            elts = new Elements();
            return;
        }

        try {
            Document html = Jsoup.parse(versionsFile);
            elts = html.body().children();
        } catch (IOException e) {
            logger.error(e, e);
            elts = new Elements();
        }
    }

    private void populateVersions() {
        for(Element elt : elts) {
            if (elt.nodeName().equals("a")) {
                String versioned = elt.ownText();
                String version = extractVersion(versioned);
                versions.add(version);
            }
        }
    }

    private String extractVersion(String versioned) {
        String name = this.name.toLowerCase();
        String version = versioned.toLowerCase();
        int p;

        // networkx-0.34-py2.4.egg
        // networkx-0.34.tar.gz
        // networkx-0.34.win32.exe
        // networkx-1.0rc1-py2.4.egg
        // neo4j-4.0.0a1.tar.gz             alpha
        // neo4j-4.0.0b1.tar.gz             beta
        // neo4j-4.0.0rc1.tar.gz            release candidate

        if (version.startsWith(name))
            version = version.substring(name.length());

        // remove prefix
        p = version.indexOf('-');
        version = version.substring(p+1);

        // remove "-py2" | "-py3"
        version = sremove(version, "-py2");
        version = sremove(version, "-py3");

        // remove "-cp2" | "-cp3"
        version = sremove(version, "-cp2");
        version = sremove(version, "-cp3");

        // remove "-pp3" | "-pypy"
        version = sremove(version, "-pp3");
        version = sremove(version, "-pypy");

        // remove '.zip', '.tar.gz', '.whl', ...
        version = sremove(version, ".zip");
        version = sremove(version, ".tar.gz");
        version = sremove(version, ".win32");
        version = sremove(version, ".whl");

        return version;
    }

    private static String sremove(String s, String t) {
        int p = s.indexOf(t);
        return p != -1 ? s.substring(0, p) : s;
    }

    private Optional<Info> findUrl(String version, String ext) {

        Version thisver = Version.of(version);
        TreeSet<Info> available = new TreeSet<>();
        int n = elts.size();

        for(int i=0; i<n; ++i) {
            Element elt = elts.get(i);
            if (elt.nodeName().equals("a")) {
                String versioned = elt.ownText();
                String ver = extractVersion(versioned);
                Version thatver = Version.of(ver);

                if (!thisver.equals(thatver))
                    continue;
                if (!versioned.endsWith(ext))
                    continue;

                // skip for Python2 libraries
                // Note: NOT NECESSARY!
                // 'Python3' is selected by 'available.last()'
                //

                String href = elt.attr("href");
                available.add(new Info(name, ver, href, versioned));
            }
        }

        if (available.isEmpty())
            return Optional.empty();
        else
            return Optional.of(available.last());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
