package jext.versioning.svn.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import org.tmatesoft.svn.core.internal.util.SVNLogInputStream;
import org.tmatesoft.svn.core.internal.util.SVNLogOutputStream;
import org.tmatesoft.svn.core.internal.util.SVNLogStream;
import org.tmatesoft.svn.util.ISVNDebugLog;
import org.tmatesoft.svn.util.SVNLogType;

/**
 * @version 1.3
 * @author  TMate Software Ltd.
 */
public abstract class SVNDebugLogAdapter implements ISVNDebugLog {

    @Override
    public void logError(SVNLogType logType, String message) {
        log(logType, message, Level.INFO);
    }

    @Override
    public void logError(SVNLogType logType, Throwable th) {
        log(logType, th, Level.INFO);
    }

    @Override
    public void logSevere(SVNLogType logType, String message) {
        log(logType, message, Level.SEVERE);
    }

    @Override
    public void logSevere(SVNLogType logType, Throwable th) {
        log(logType, th, Level.SEVERE);
    }

    @Override
    public void logFine(SVNLogType logType, Throwable th) {
        log(logType, th, Level.FINE);
    }

    @Override
    public void logFine(SVNLogType logType, String message) {
        log(logType, message, Level.FINE);
    }

    @Override
    public void logFiner(SVNLogType logType, Throwable th) {
        log(logType, th, Level.FINER);
    }

    @Override
    public void logFiner(SVNLogType logType, String message) {
        log(logType, message, Level.FINER);
    }

    @Override
    public void logFinest(SVNLogType logType, Throwable th) {
        log(logType, th, Level.FINEST);
    }

    @Override
    public void logFinest(SVNLogType logType, String message) {
        log(logType, message, Level.FINEST);
    }

    @Override
    public void flushStream(Object stream) {
        if (stream instanceof SVNLogInputStream) {
            SVNLogInputStream logStream = (SVNLogInputStream) stream;
            logStream.flushBuffer();
        } else if (stream instanceof SVNLogOutputStream) {
            SVNLogOutputStream logStream = (SVNLogOutputStream) stream;
            logStream.flushBuffer();
        }
    }

    @Override
    public InputStream createLogStream(SVNLogType logType, InputStream is) {
        return new SVNLogInputStream(is, createInputLogStream());
    }

    @Override
    public OutputStream createLogStream(SVNLogType logType, OutputStream os) {
        return new SVNLogOutputStream(os, createOutputLogStream());
    }

    @Override
    public OutputStream createInputLogStream() {
        return new SVNLogStream(this, false);
    }

    @Override
    public OutputStream createOutputLogStream() {
        return new SVNLogStream(this, true);
    }

}
