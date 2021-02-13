package jext.sourcecode.project.gradle.collectors;

import jext.io.LineOutputStream;
import jext.logging.Logger;

public class LoggerCollector extends LineOutputStream {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private Logger logger;
    private LineOutputStream delegate;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public LoggerCollector(Logger logger, LineOutputStream delegate) {
        this.logger = logger;
        this.delegate = delegate;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public void consume(String line) {
        String message = line.toLowerCase();
        if (message.contains("error"))
            logger.error(line);
        else if (message.contains("warn"))
            logger.warn(line);
        else if (message.contains("info"))
            logger.info(line);
        else
            logger.debug(line);

        delegate.consume(line);
    }
}
