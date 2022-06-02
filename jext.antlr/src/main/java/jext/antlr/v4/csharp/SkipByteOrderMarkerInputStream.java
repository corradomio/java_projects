package jext.antlr.v4.csharp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SkipByteOrderMarkerInputStream extends FileInputStream {

    private boolean skip = true;

    public SkipByteOrderMarkerInputStream(String name) throws IOException {
        super(name);
        skipByteOrderMarker();
    }

    public SkipByteOrderMarkerInputStream(File file) throws IOException {
        super(file);
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
        if (skip) {
            skip = false;
            return super.skip(3);
        }
        return 0;
    }

}
