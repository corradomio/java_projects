package jext.sourcecode.project;

import jext.maven.MavenCoords;

import java.util.Collection;

public interface LibraryDownloader {

    String getName();

    /** Each project can add 'extra' repositories */
    void addRepository(String name, String url);

    LibraryDownloader newDownloader();

    void checkArtifacts(Collection<MavenCoords> artifacts, boolean b, boolean parallel);

    String getLatestVersion(MavenCoords coords);
}
