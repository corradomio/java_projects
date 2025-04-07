package org.example;
import jext.util.logging.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

public class CheckLog4J {

    public static void main(String[] args) {
        LogManager.configure(new File("logging.properties"));
        Logger log = Logger.getLogger(CheckLog4J.class);
        log.info("Ciccio");

        jext.util.logging.Logger log2 = jext.util.logging.Logger.getLogger(CheckLog4J.class);
        log2.info("Ciccio2");

    }
}
