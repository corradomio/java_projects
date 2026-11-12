package check;

import jext.util.logging.LogManagerExt;

public class CheckLogging {

    public static void main(String[] args) {
        // Logger.configure("logging.properties");
        LogManagerExt.configure("log4j.xml");

        System.out.println(String.format("c:%d %d", 1, 2));

        // java.util.logging.Handler h;
        //
        // SystemOut System = Logger.getSystemOut("CiccioPasticcio");
        //
        // System.out.println("[sout] Hello World");
        // System.err.println("[serr] Hello World");
        //
        // Logger log = Logger.getLogger("Main");
        //
        // log.debug("Hello Cruel World");
        // log.info("Hello Cruel World");
        // log.warning("Hello Cruel World");
        // log.error("Hello Cruel World");
        // log.fatal("Hello Cruel World");
    }
}
