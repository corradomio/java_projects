package jext.vfs.compress;

import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.Date;

public class RootEntry implements ArchiveEntry {

    @Override
    public String getName() {
        return "/";
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public Date getLastModifiedDate() {
        return InvalidEntry.NO_DATE;
    }
}
