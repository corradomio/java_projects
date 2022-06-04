package jext.antlr.v4.csharp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.file.Files;

public class SkipByteOrderMarkerInputStream extends PushbackInputStream {

    private boolean skip = true;

    public SkipByteOrderMarkerInputStream(String name) throws IOException {
        super(new FileInputStream(name));
        skipByteOrderMarker();
    }

    public SkipByteOrderMarkerInputStream(File file) throws IOException {
        super(Files.newInputStream(file.toPath()));
        skipByteOrderMarker();
    }

    // @Override
    // public int read() throws IOException {
    //     skipByteOrderMarker();
    //     return super.read();
    // }
    //
    // @Override
    // public int read(byte[] b, int off, int len) throws IOException {
    //     skipByteOrderMarker();
    //     return super.read(b, off, len);
    // }
    //
    // @Override
    // public long skip(long n) throws IOException {
    //     skipByteOrderMarker();
    //     return super.skip(n);
    // }

    private long skipByteOrderMarker() throws IOException {
        int m = super.read();
        if (m == '\u00EF') {
            super.read();
            super.read();
        }
        else {
            super.unread(m);
        }
        // if (skip) {
        //     skip = false;
        //     return super.skip(3);
        // }
        return 0;
    }

}
