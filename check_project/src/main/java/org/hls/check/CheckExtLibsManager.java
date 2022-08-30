package org.hls.check;

import jext.maven.MavenCoords;
import jext.maven.Versions;
import jext.sourcecode.project.lfm.csharp.NuGetRepository;
import jext.sourcecode.project.lfm.python.PyPiRepository;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class CheckExtLibsManager {

    static void checkNuget() throws IOException {

        MavenCoords artifact = MavenCoords.of("", "Newtonsoft.Json", "13.0.1");

        NuGetRepository repo = new NuGetRepository();
        repo.initialize();

        Versions versions = repo.getArtifactVersions(artifact);
        System.out.println(versions.getLatestVersion());

        Optional<File> artifactFile = repo.downloadArtifact(artifact);
        System.out.println(artifactFile);
    }

    static void checkPypi() throws IOException {

        MavenCoords artifact = MavenCoords.of("", "networkx", "2.8.6");

        PyPiRepository repo = new PyPiRepository();
        repo.initialize();

        Versions versions = repo.getArtifactVersions(artifact);
        System.out.println(versions.getLatestVersion());

        Optional<File> file = repo.downloadArtifact(artifact);
    }

    public static void main(String[] args) throws IOException {
        //checkNuget();
        checkPypi();
    }
}
