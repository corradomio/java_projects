package jext.io.filters;

import java.io.File;
import java.io.FileFilter;

public class FalseFileFilter implements FileFilter {

    public static final FalseFileFilter INSTANCE = new FalseFileFilter();

    @Override
    public boolean accept(File pathname) {
        return false;
    }
}
