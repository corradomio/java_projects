package jext.sourcecode.project.python;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryDownloader;

import java.util.Collection;

public class PyPiDownloader implements LibraryDownloader {

    @Override
    public String getName() {
        return "pypi";
    }

    @Override
    public void addRepository(String name, String url) {

    }

    @Override
    public LibraryDownloader newDownloader() {
        return this;
    }

    @Override
    public void checkArtifacts(Collection<MavenCoords> artifacts, boolean b, boolean parallel) {

    }

    @Override
    public String getLatestVersion(MavenCoords coords) {
        return coords.version;
    }
}
