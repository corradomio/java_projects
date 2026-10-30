package org.apache.log4j;

import java.io.IOException;
import java.util.logging.FileHandler;

public class FileAppender extends FileHandler {

    public FileAppender() throws IOException, SecurityException {
    }

    public FileAppender(String pattern) throws IOException, SecurityException {
        super(pattern);
    }

    public FileAppender(String pattern, boolean append) throws IOException, SecurityException {
        super(pattern, append);
    }

    public FileAppender(String pattern, int limit, int count) throws IOException, SecurityException {
        super(pattern, limit, count);
    }

    public FileAppender(String pattern, int limit, int count, boolean append) throws IOException, SecurityException {
        super(pattern, limit, count, append);
    }

    public FileAppender(String pattern, long limit, int count, boolean append) throws IOException {
        super(pattern, limit, count, append);
    }
}
