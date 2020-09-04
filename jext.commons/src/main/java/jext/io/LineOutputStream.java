package jext.io;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class LineOutputStream extends OutputStream {

    private static final int BUFFER_SIZE = 1024;

    private final Charset charset;
    private byte[] buffer = new byte[BUFFER_SIZE];
    private int count;
    private int ch;

    public LineOutputStream() {
        this(StandardCharsets.UTF_8);
    }

    public LineOutputStream(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void write(int b) {
        // is end of line
        //  \r -> 13    CR
        //  \n -> 10    LF
        //
        // Unix:    \n
        // Windows: \r\n
        // Ma:      \r
        if (b == '\r' || b == '\n') {
            if (count > 0)
                this.consume(new String(buffer, 0, count, charset));
            else if (ch != '\r')
                this.consume("");
            count = 0;
            ch = b;
            return;
        }

        if (count == buffer.length) {
            byte[] obuffer = buffer;
            buffer = new byte[buffer.length + BUFFER_SIZE];
            System.arraycopy(obuffer, 0, buffer, 0, count);
        }

        buffer[count++] = (byte) b;
        ch = b;
    }

    @Override
    public void close() {
        write('\n');
        buffer = null;
    }

    public abstract void consume(String line);
}
