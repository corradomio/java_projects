package org.hls.check;

import jext.buildtools.SourceProject;
import jext.buildtools.maven.MavenProject;
import jext.util.logging.Logger;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenDownloader;
import jext.buildtools.maven.MavenPom;
import jext.buildtools.maven.Versions;
import jext.util.concurrent.Parallel;

import java.io.File;

public class MainMaven {

    public static void main(String[] args) {
        Logger.configure();

        MavenProject project = new MavenProject(new File(
                // "D:\\Projects.github\\other_projects\\commons-jcs"
                "D:\\Projects.github\\apache_gitbox\\hadoop"
        ));

        SourceProject.dump(project);
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
