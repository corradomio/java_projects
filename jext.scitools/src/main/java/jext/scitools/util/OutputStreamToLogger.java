package jext.scitools.util;

import jext.logging.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutputStreamToLogger extends OutputStream {

    private static final int BUFFER_LENGTH = 1024;

    private byte[] buffer;
    private int length;
    private Logger logger;

    public OutputStreamToLogger(Logger logger) {
        this.logger = logger;
        this.buffer = new byte[BUFFER_LENGTH];
    }

    @Override
    public void write(int b) {
        if (length == buffer.length)
            expand();
        if (b == '\n' || b == '\r') {
            if (length != 0)
                log();
        }
        else
            buffer[length++] = (byte)b;
    }

    private void log() {
        String message = new String(buffer, 0, length, StandardCharsets.US_ASCII);
        length = 0;
        logger.debug(message);
        if (buffer.length > BUFFER_LENGTH)
            buffer = new byte[BUFFER_LENGTH];
    }

    private void expand() {
        byte[] nbuffer = new byte[buffer.length + BUFFER_LENGTH];
        System.arraycopy(buffer, 0, nbuffer, 0, length);
        buffer = nbuffer;
    }
}
