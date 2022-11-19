package org.hls.check;

import jext.cache.Caches;
import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;

public class App2 {

    public static void main(String[] args) {
        Logger.configure();
        Caches.configure();

        MavenDownloader md = new MavenDownloader();
        md.addRepository("https://repo1.maven.org/maven2");
        md.addRepository("http://repository.primefaces.org");

        MavenCoords coods = MavenCoords.of("antlr:antlr:2.7.7");

        md.checkArtifact(coods);

        System.out.println(md.getLatestVersion(coods));
    }
}
