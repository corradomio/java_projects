package jext.sourcecode.project.python.libraries;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.java.maven.MavenName;
import jext.sourcecode.project.lfm.LibraryLicense;
import jext.sourcecode.project.lfm.LicenseFinder;
import jext.sourcecode.project.python.PyPiDownloader;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PyPiLibrary extends PythonLibrary {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final MavenCoords coords;
    private final PyPiDownloader downloader;
    private LibraryLicense license;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public PyPiLibrary(MavenCoords coords, PyPiDownloader downloader) {
        super(MavenName.of(coords));
        this.version = coords.version;
        this.libraryFile = downloader.getLibraryFile(coords);
        this.libraryType = LibraryType.REMOTE;
        this.coords = coords;
        this.downloader = downloader;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public LibraryLicense getLibraryLicense() {
        if (license == null)
            license = LicenseFinder.findLicense(libraryFile);
        return license;
    }

    // ----------------------------------------------------------------------
    // getFiles

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

    private void populate() {
        // check for    [HOME]/<name>/<version>/<name>-<version>/<name>
        // return       [HOME]/<name>/<version>/<name>-<version>/
        findForNameVersion();
        // check for    [HOME]/<name>/<version>/<name>
        // return       [HOME]/<name>/<version>/
        findForName();
        // check for    [HOME]/<name>/<version>/.../<name>
        // return       [HOME]/<name>/<version>/.../<name>/..
        findRecursivelyForName();
        // not found
        if (libraryFiles == null)
            libraryFiles = Collections.emptyList();
    }

    private void findForNameVersion() {
        if (libraryFiles != null)
            return;

        String relativePath = String.format(
                "%1$s-%2$s/%1$s",
                coords.artifactId,
                coords.version);

        File libraryHome = new File(libraryFile, relativePath);
        if (libraryHome.exists())
            libraryFiles = Collections.singletonList(libraryHome.getParentFile());
    }

    private void findForName() {
        if (libraryFiles != null)
            return;

        File libraryHome = new File(libraryFile, coords.artifactId);
        if (libraryHome.exists())
            libraryFiles = Collections.singletonList(libraryFile);
    }

    private void findRecursivelyForName() {
        if (libraryFiles != null)
            return;

        // scan depth-first

        File selected = null;
        Queue<File> waiting = new LinkedList<>();
        waiting.add(libraryFile);

        while (!waiting.isEmpty()) {
            File current = waiting.remove();
            if (current.getName().equals(coords.artifactId)) {
                selected = current;
                break;
            }

            File[] subdirs = current.listFiles(File::isDirectory);
            if (subdirs != null)
                waiting.addAll(Arrays.asList(subdirs));
        }

        if (selected != null)
            libraryFiles = Collections.singletonList(selected.getParentFile());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
