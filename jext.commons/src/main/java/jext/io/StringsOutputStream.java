package jext.io;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class StringsOutputStream extends LineOutputStream {

    protected List<String> content = new ArrayList<>();

    public StringsOutputStream() {
        super();
    }

    public StringsOutputStream(Charset charset) {
        super(charset);
    }

    @Override
    public void consume(String line) {
        content.add(line);
    }
}
