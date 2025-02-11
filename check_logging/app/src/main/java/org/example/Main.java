package org.example;

import java.io.File;

import jext.util.logging.LogManager;
import jext.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // for (String arg: args)
        //     System.out.println(arg);
        // System.out.println("-------------------------------");

        LogManager.configure(new File("logging.properties"));
        Logger logger = LogManager.getLogger(Main.class);

        // debug
        // info
        // warning
        // error
        // severe

        // debug
        // logger.finest("Fine - 300");
        // logger.finer("Fine - 400");
        // logger.fine("Fine - 500");

        logger.debug("Debug - 500");
        logger.info("Info - 800");
        logger.warning("Warning - 900");
        logger.error("Error - 950");
        logger.fatal("Fatal - 1000");
        logger.severe("Severe - 1000");

        // OFF
        // SEVERE
        // WARNING
        // INFO
        // CONFIG
        // FINE
        // FINER
        // FINEST
        // ALL

    }

}
