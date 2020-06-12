package jext.vfs.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.Date;

public class InvalidEntry implements ArchiveEntry {

    static Date NO_DATE = new Date(0);
    private String name;

    InvalidEntry(String n) {
        name = n;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return -1;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public Date getLastModifiedDate() {
        return NO_DATE;
    }
}
