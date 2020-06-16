package jext.gradle.collectors;

import jext.io.LineOutputStream;
import jext.logging.Logger;

public class LogCollector extends LineOutputStream {

    private Logger logger;
    private LineOutputStream delegate;

    public LogCollector(Logger logger, LineOutputStream delegate) {
        this.logger = logger;
        this.delegate = delegate;
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public void accept(String line) {
        String message = line.toLowerCase();
        if (message.contains("error"))
            logger.error(line);
        else if (message.contains("warn"))
            logger.warn(line);
        else if (message.contains("info"))
            logger.info(line);
        else
            logger.debug(line);
        
        delegate.accept(line);
    }
}
