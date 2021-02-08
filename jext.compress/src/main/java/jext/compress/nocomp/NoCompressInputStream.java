package jext.compress.nocomp;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.IOException;
import java.util.Date;

public class NoCompressInputStream extends ArchiveInputStream {

    private static class Entry implements ArchiveEntry {

        @Override
        public String getName() {
            return "";
        }

        @Override
        public long getSize() {
            return 0;
        }

        @Override
        public boolean isDirectory() {
            return false;
        }

        @Override
        public Date getLastModifiedDate() {
            return new Date();
        }
    }

    private Entry defaultEntry = new Entry();

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return defaultEntry;
    }
}
