package jext.versioning.svn.util;

import jext.io.NullInputStream;
import jext.io.NullOutputStream;
import jext.logging.Logger;
import org.tmatesoft.svn.util.ISVNDebugLog;
import org.tmatesoft.svn.util.SVNLogType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class SVNLogging implements ISVNDebugLog {

    private Logger logger;

    public SVNLogging(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void logError(SVNLogType logType, String message) {
        logger.error(message);
    }

    @Override
    public void logError(SVNLogType logType, Throwable th) {
        logger.error(th, th);
    }

    @Override
    public void logSevere(SVNLogType logType, String message) {
        logger.error(message);
    }

    @Override
    public void logSevere(SVNLogType logType, Throwable th) {
        logger.error(th, th);
    }

    @Override
    public void logFine(SVNLogType logType, Throwable th) {
        logger.debug(th, th);
    }

    @Override
    public void logFine(SVNLogType logType, String message) {
        logger.debug(message);
    }

    @Override
    public void logFiner(SVNLogType logType, Throwable th) {
        logger.debug(th, th);
    }

    @Override
    public void logFiner(SVNLogType logType, String message) {
        logger.debug(message);
    }

    @Override
    public void logFinest(SVNLogType logType, Throwable th) {
        logger.debug(th, th);
    }

    @Override
    public void logFinest(SVNLogType logType, String message) {
        logger.debug(message);
    }

    @Override
    public void log(SVNLogType logType, Throwable th, Level logLevel) {
        logger.info(th, th);
    }

    @Override
    public void log(SVNLogType logType, String message, Level logLevel) {
        logger.info(message);
    }

    @Override
    public void log(SVNLogType logType, String message, byte[] data) {
        logger.info(message);
    }

    @Override
    public InputStream createLogStream(SVNLogType logType, InputStream is) {
        return NullInputStream.instance();
    }

    @Override
    public OutputStream createLogStream(SVNLogType logType, OutputStream os) {
        return NullOutputStream.instance();
    }

    @Override
    public OutputStream createOutputLogStream() {
        return NullOutputStream.instance();
    }

    @Override
    public OutputStream createInputLogStream() {
        return NullOutputStream.instance();
    }

    @Override
    public void flushStream(Object stream) {

    }
}
