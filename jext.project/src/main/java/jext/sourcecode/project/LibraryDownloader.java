package jext.sourcecode.project;

import jext.maven.MavenCoords;

import java.util.Collection;
import java.util.List;

public interface LibraryDownloader {

    // void checkArtifacts(List<MavenCoords> artifacts, boolean b, boolean parallel);

    LibraryDownloader addRepository(String url);

    LibraryDownloader newDownloader();

    void checkArtifacts(Collection<MavenCoords> artifacts, boolean b, boolean parallel);
}
