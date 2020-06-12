package jext.vfs.util;

import java.util.ArrayList;
import java.util.List;

public class FileCount {

    // used to COUNT the number of objects TO PROCESS
    public int nFiles = 0;
    public int nFolders = 0;
    public long nBytes = 0;

    // used to COUNT the number of objects PROCESSED
    public int cFiles = 0;
    public int cFolders = 0;
    public long cBytes = 0;

    public List<String> unprocessed = new ArrayList<>();
    public byte[] buffer;

    public FileCount() {

    }

    public void addFolder() { nFolders++; }
    public void addFile() { addFile(1); }
    public void addFile(long length) { nFiles++; nBytes += length; }

    public void processFolder() { cFolders++; }
    public void processFile() { processFile(1); }
    public void processFile(long length) { cFiles++; cBytes += length; }

    public void unprocessed(String path) {
        unprocessed.add(path);
    }

    public void validate(boolean skipFolders) {
        if (skipFolders) {
            if (nFolders != cFolders)
                throw new RuntimeException(String.format("Unable to process some folders (%d, %d)", nFolders, cFolders));
            if (nBytes != cBytes)
                throw new RuntimeException(String.format("Some files are corrupted (%d, %d)", nBytes, cBytes));
        }
        else {
            if (nFiles == cFiles && nFolders == cFolders && nBytes == cBytes)
                return;
            if (nFiles != cFiles && nFolders != cFolders)
                throw new RuntimeException(String.format("Unable to process some files and folders (%d, %d)", nFiles+nFolders, cFiles+cFolders));
            if (nFiles != cFiles)
                throw new RuntimeException(String.format("Unable to process some files (%d, %d)", nFiles, cFiles));
            if (nFolders != cFolders)
                throw new RuntimeException(String.format("Unable to process some folders (%d, %d)", nFolders, cFolders));
            if (nBytes != cBytes)
                throw new RuntimeException(String.format("Some files are corrupted (%d, %d)", nBytes, cBytes));
        }
    }
}
