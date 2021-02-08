package jext.compress.zip;

import java.io.InputStream;

public class ZipArchiveInputStream extends org.apache.commons.compress.archivers.zip.ZipArchiveInputStream {

    public ZipArchiveInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding) {
        super(inputStream, encoding);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields) {
        super(inputStream, encoding, useUnicodeExtraFields);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor) {
        super(inputStream, encoding, useUnicodeExtraFields, allowStoredEntriesWithDataDescriptor);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor, boolean skipSplitSig) {
        super(inputStream, encoding, useUnicodeExtraFields, allowStoredEntriesWithDataDescriptor, skipSplitSig);
    }

}
