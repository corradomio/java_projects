package jext.scitools.util;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class OutputStreamToConsumer extends OutputStream {

    private static final int BUFFER_LENGTH = 1024;

    private byte[] buffer;
    private int length;
    private Consumer<String> consumer;

    public OutputStreamToConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
        this.buffer = new byte[BUFFER_LENGTH];
    }

    @Override
    public void write(int b) {
        if (length == buffer.length)
            expand();
        if (b == '\n' || b == '\r') {
            if (length != 0)
                consume();
        }
        else
            buffer[length++] = (byte)b;
    }

    private void consume() {
        String message = new String(buffer, 0, length, StandardCharsets.US_ASCII);

        // reset the buffer
        length = 0;
        if (buffer.length > BUFFER_LENGTH)
            buffer = new byte[BUFFER_LENGTH];

        consumer.accept(message);
    }

    private void expand() {
        byte[] nbuffer = new byte[buffer.length + BUFFER_LENGTH];
        System.arraycopy(buffer, 0, nbuffer, 0, length);
        buffer = nbuffer;
    }
}
