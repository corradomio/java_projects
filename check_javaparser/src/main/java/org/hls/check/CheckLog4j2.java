package org.hls.check;

import jext.logging.v2.Logger;

public class CheckLog4j2 {

    public static void main(String[] args) {
        Logger.configure();
        Logger log = Logger.getLogger();
        log.infof("Hello world");
    }
}
