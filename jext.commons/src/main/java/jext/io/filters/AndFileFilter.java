package jext.io.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class AndFileFilter implements FileFilter {

    private List<FileFilter> filters = new ArrayList<>();

    public AndFileFilter() {

    }

    public AndFileFilter add(FileFilter filter) {
        if (filter == null)
            throw new IllegalArgumentException("Filter can not be null");
        filters.add(filter);
        return this;
    }

    @Override
    public boolean accept(File pathname) {
        for (FileFilter filter : filters)
            if (!filter.accept(pathname))
                return false;
        return true;
    }
}
