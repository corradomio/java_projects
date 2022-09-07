package org.hls.check;

import jext.maven.MavenCoords;
import jext.sourcecode.project.python.util.PyPiResolver;
import jext.sourcecode.project.python.util.Requirements;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class CheckPyPiResolver {

    static void checkPyPiServer() {

        Requirements req = new Requirements(new File("D:\\Projects\\Python\\pypiserver-1.5.0"));
        List<MavenCoords> libraries = req.getLibraries();
        libraries.forEach(coords -> {
            System.out.println(coords);
        });

        MavenCoords flask = req.getLibrary("flask");

        PyPiResolver res = new PyPiResolver(new File("C:\\Users\\Corrado Mio\\.spl\\.pip\\flask\\versions.html"));
        Optional<PyPiResolver.Info> r = res.selectVersion(flask.version);
        System.out.println();
    }

    static void checkFlask() {
        Requirements req = new Requirements(new File("D:\\Projects\\Python\\flask-2.1.2"));
        List<MavenCoords> libraries = req.getLibraries();
        libraries.forEach(coords -> {
            System.out.println(coords);
        });
    }

    public static void main(String[] args) {
        // checkPyPiServer();
        checkFlask();
    }
}
