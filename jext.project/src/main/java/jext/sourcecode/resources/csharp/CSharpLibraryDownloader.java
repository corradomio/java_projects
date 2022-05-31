package jext.sourcecode.resources.csharp;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryDownloader;

import java.util.Collection;

public class CSharpLibraryDownloader implements LibraryDownloader {

    @Override
    public LibraryDownloader addRepository(String url) {
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
