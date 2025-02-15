package jext.versioning.svn.util;

import jext.util.logging.Logger;
import org.tmatesoft.svn.util.SVNLogType;

import java.util.logging.Level;

public class SVNLogging extends SVNDebugLogAdapter {

    private Logger logger;

    public SVNLogging(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(SVNLogType logType, Throwable th, Level logLevel) {
        int lvl = logLevel.intValue();
        if (lvl<Level.INFO.intValue())
            logger.debug(th, th);
        else if (lvl<Level.WARNING.intValue())
            logger.info(th, th);
        else if (lvl<Level.SEVERE.intValue())
            logger.warn(th, th);
        else
            logger.error(th,th);
    }

    @Override
    public void log(SVNLogType logType, String message, Level logLevel) {
        int lvl = logLevel.intValue();
        if (lvl<Level.INFO.intValue())
            logger.debug(message);
        else if (lvl<Level.WARNING.intValue())
            logger.info(message);
        else if (lvl<Level.SEVERE.intValue())
            logger.warn(message);
        else
            logger.error(message);
    }

    @Override
    public void log(SVNLogType logType, String message, byte[] data) {
        log(logType, message + " byte[]{...}", Level.FINER);
    }
}
