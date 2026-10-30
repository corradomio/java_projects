package org.apache.log4j;

import java.io.IOException;

public class RollingFileAppender extends FileAppender{
    public RollingFileAppender() throws IOException, SecurityException {
    }

    public RollingFileAppender(String pattern) throws IOException, SecurityException {
        super(pattern);
    }

    public RollingFileAppender(String pattern, boolean append) throws IOException, SecurityException {
        super(pattern, append);
    }

    public RollingFileAppender(String pattern, int limit, int count) throws IOException, SecurityException {
        super(pattern, limit, count);
    }

    public RollingFileAppender(String pattern, int limit, int count, boolean append) throws IOException, SecurityException {
        super(pattern, limit, count, append);
    }

    public RollingFileAppender(String pattern, long limit, int count, boolean append) throws IOException {
        super(pattern, limit, count, append);
    }
}
