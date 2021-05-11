package jext.io.filters;

import java.io.File;
import java.io.FileFilter;

public class NotFileFilter implements FileFilter {

    private FileFilter filter;

    public NotFileFilter() {

    }

    public NotFileFilter(FileFilter filter) {
        this.filter = filter;
    }

    public NotFileFilter add(FileFilter filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public boolean accept(File pathname) {
        if (filter == null)
            return false;
        else
            return !filter.accept(pathname);
    }
}
