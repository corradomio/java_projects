package jext.sourcecode.project.java.gradle.collectors;

import jext.io.LineOutputStream;
import jext.util.logging.Logger;

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

        // skip "> Task :"
        // skip "ProjectDir:
        if (line.contains("> Task :") || line.contains("ProjectDir:"))
            return;

        if (message.contains("error") || message.contains("fail"))
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
