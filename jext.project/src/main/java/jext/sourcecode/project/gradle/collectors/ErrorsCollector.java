package jext.sourcecode.project.gradle.collectors;

import jext.io.LineOutputStream;
import jext.logging.Logger;

public class ErrorsCollector extends LineOutputStream {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private Logger logger;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ErrorsCollector(Logger logger) {
        this.logger = logger;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public void consume(String message) {
        String lc = message.toLowerCase();
        if (lc.isEmpty())
            ;
        else if (lc.contains("warn"))
            logger.warn(message);
        else if (lc.contains("error"))
            logger.error(message);
        else
            logger.debug(message);
    }

}
