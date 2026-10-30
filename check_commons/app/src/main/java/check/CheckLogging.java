package check;

import jext.util.logging.LogManagerExt;
import jext.util.logging.Logger;
import jext.util.logging.SystemOut;

public class CheckLogging {

    public static void main(String[] args) {
        // Logger.configure("logging.properties");
        LogManagerExt.configure("log4j.xml");

        java.util.logging.Handler h;

        SystemOut System = Logger.getSystemOut("CiccioPasticcio");

        System.out.println("[sout] Hello World");
        System.err.println("[serr] Hello World");

        Logger log = Logger.getLogger("Main");

        log.debug("Hello Cruel World");
        log.info("Hello Cruel World");
        log.warning("Hello Cruel World");
        log.error("Hello Cruel World");
        log.fatal("Hello Cruel World");
    }
}
