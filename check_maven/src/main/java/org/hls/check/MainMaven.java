package org.hls.check;

import jext.cache.Caches;
import jext.logging.Logger;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.maven.Versions;
import jext.util.concurrent.Parallel;

import java.io.File;

public class MainMaven {

    public static void main(String[] args) {

    }

    public static void main5(String[] args) {
        Logger.configure();
        Caches.configure();

        MavenDownloader md = new MavenDownloader()
            .addRepository("https://repo1.maven.org/maven2")
            .addRepository("http://repository.primefaces.org")
            ;
        MavenPom pom = new MavenPom(new File("C:\\Users\\Corrado Mio\\.spl\\.extlib\\org\\nd4j\\nd4j-api\\1.0.0-beta7\\nd4j-api-1.0.0-beta7.pom"), md);

        pom.getDependencies().forEach(dep -> {
            System.out.println(dep);
        });

    }

    public static void main4(String[] args) {
        Logger.configure();
        Caches.configure();

        MavenDownloader md = new MavenDownloader()
            .addRepository("https://repo1.maven.org/maven2")
            .addRepository("http://repository.primefaces.org")
            ;

        // MavenCoords mc = new MavenCoords("org.bytedeco", "opencv");
        MavenCoords mc = new MavenCoords("joda-time:joda-time:2.2");
        mc = md.getVersioned(mc);
        System.out.println(mc);

        File avro = md.getArtifact(mc);
        System.out.println(avro.exists());
    }

    public static void main3(String[] args) {

        MavenDownloader downloader = new MavenDownloader();
        MavenPom pom = new MavenPom(
            new File("D:\\SPLGroup\\BTProjects\\SampleProject\\cocome-maven-project\\cloud-logic-service\\cloud-enterprise-logic\\enterprise-logic-ejb\\pom.xml"),
            downloader
        );

        System.out.println("Start ...");
        Parallel.forEach(0, 100, (i) -> {
            try {
                pom.getDependencyCoords();
                // System.out.println(i);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }

        });

        System.out.println("Waiting ...");
        Parallel.shutdown();
        System.out.println("Done");
    }


    public static void main2(String[] args) {
        Logger.configure();

        MavenDownloader md = new MavenDownloader()
            .addRepository("https://repo1.maven.org/maven2")
            .addRepository("http://repository.primefaces.org")
            ;

        MavenCoords mc =
            // new MavenCoords("bouncycastle", "bctsp-jdk14", "138")
            new MavenCoords("asm", "asm-parent")
            ;
        System.out.println(md.getArtifacts(mc));
    }

    public static void main1(String[] args) throws Exception {
        Logger.configure();

        MavenDownloader md = new MavenDownloader()
            .addRepository("https://repo1.maven.org/maven2")
            .addRepository("http://repository.primefaces.org")
            ;

        MavenCoords mc = new MavenCoords("org.bouncycastle", "bctsp-jdk14");
        // mc = new MavenCoords("log4j","log4j");
        System.out.println(md.getLatestVersion(mc));
        Versions v = md.getVersions(mc);
        v.dump();
        System.out.println(v.getLatestVersion());

        // parentPom.dump();

    }
}
