package org.hls.example;

import jext.apache.logging.log4j.Logger;

import java.io.File;

public class CheckLogging {

    public static void main(String[] args) {
        // Logger.configure(new File("log4j2.properties"));
        Logger.configure(new File("log4j2v1.properties"));
        Logger logger = Logger.getLogger();

        logger.debug("This is a debug message.");
        logger.info("This is a info message.");
        logger.warn("This is a warning message.");
        logger.error("This is a error message.");
        logger.fatal("This is a fatal message.");
    }
}
