package org.hls.check;

import jext.logging.Logger;

import java.io.IOException;

public class CheckLogger {

    static Logger l = Logger.getLogger("main");

    public static void main(String[] args) throws IOException {
        Logger.configure();

        // Logger l = Logger.getLogger("main");

        l.debug("debug");
        l.info("info");
        l.warn("warn");
        l.error("error");
        l.fatal("fatal");
        System.out.println( "Hello World!");

        // checkCSharp();
        // checkJava();
        // checkPython();
        // checkScala();
    }
}
