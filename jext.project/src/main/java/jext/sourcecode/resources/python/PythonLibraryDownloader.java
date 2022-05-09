package jext.sourcecode.resources.python;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryDownloader;

import java.util.Collection;

public class PythonLibraryDownloader implements LibraryDownloader {
    @Override
    public Object addRepository(String url) {
        return this;
    }

    @Override
    public LibraryDownloader newDownloader() {
        return this;
    }

    @Override
    public void checkArtifacts(Collection<MavenCoords> artifacts, boolean b, boolean parallel) {

    }
}
