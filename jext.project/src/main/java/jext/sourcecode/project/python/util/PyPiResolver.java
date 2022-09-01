package jext.sourcecode.project.python.util;

import jext.logging.Logger;
import jext.maven.Versions;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.Option;
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
    private Elements elts;
    private boolean initialized;

    public static class Info implements Comparable<Info> {
        public final String name;
        public final String url;

        private Info(String name, String url) {
            this.name = name;
            this.url = url;
        }

        // ----------------------------------------------------------------------

        @Override
        public int compareTo(@NotNull Info o) {
            return name.compareTo(o.name);
        }
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PyPiResolver(File versionsFile) {
        this.versionsFile = versionsFile;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     *
     * @return
     */
    public Versions getVersions() {
        populate();

        Versions versions = new Versions();

        for(Element elt : elts) {
            if (elt.nodeName().equals("a")) {
                String versioned = elt.ownText();
                String version = extractVersion(versioned);
                versions.add(version);
            }
        }

        return versions;
    }

    public Optional<Info> selectDistribution(String version) {
        populate();

        // scan for the .tar.gz
        // if available available, scan for the first ".whl"
        Optional<Info> info = findUrl(version, ".tar.gz");
        if (!info.isPresent())
            info = findUrl(version, ".whl");

        return info;
    }



    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void populate() {
        if (initialized)
            return;
        else
            initialized = true;

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

        // remove "-cp2" | "-cp3"
        version = sremove(version, "-cp2");
        version = sremove(version, "-cp3");

        // remove '.zip', '.tar.gz'
        version = sremove(version, ".zip");
        version = sremove(version, ".tar.gz");
        version = sremove(version, ".win32");

        return version;
    }

    private static String sremove(String s, String t) {
        int p = s.indexOf(t);
        return p != -1 ? s.substring(0, p) : s;
    }

    private Optional<Info> findUrl(String version, String ext) {

        TreeSet<Info> available = new TreeSet<>();

        for(Element elt : elts) {
            if (elt.nodeName().equals("a")) {
                String versioned = elt.ownText();
                String ver = extractVersion(versioned);
                if (!ver.equals(version))
                    continue;
                if (!versioned.endsWith(ext))
                    continue;

                String href = elt.attr("href");
                available.add(new Info(versioned, href));
            }
        }

        return available.isEmpty() ? Optional.empty() : Optional.of(available.last());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
