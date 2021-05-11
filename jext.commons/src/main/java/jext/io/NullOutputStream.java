package jext.io;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream {

    public static final NullOutputStream INSTANCE = new NullOutputStream();

    public static NullOutputStream instance() {
        return INSTANCE;
    }

    @Override
    public void write(int b) throws IOException {

    }
}
