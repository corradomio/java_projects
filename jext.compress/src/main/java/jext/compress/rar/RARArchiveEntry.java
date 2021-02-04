package jext.compress.rar;

import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.ArchiveEntry;

import java.util.Date;

public class RARArchiveEntry implements ArchiveEntry {

    private FileHeader fileHeader;


    public RARArchiveEntry(FileHeader fh) {
        fileHeader = fh;
    }

    @Override
    public String getName() {
        return fileHeader.getFileNameString();
    }

    @Override
    public long getSize() {
        return fileHeader.getFullUnpackSize();
    }

    @Override
    public boolean isDirectory() {
        return fileHeader.isDirectory();
    }

    @Override
    public Date getLastModifiedDate() {
        return fileHeader.getMTime();
    }
}
