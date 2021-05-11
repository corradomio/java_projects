package jext.io;

import java.io.IOException;
import java.io.InputStream;

public class NullInputStream extends InputStream {

    public static final NullInputStream INSTANCE = new NullInputStream();

    public static NullInputStream instance() {
        return INSTANCE;
    }

    @Override
    public int read() throws IOException {
        return -1;
    }
}
