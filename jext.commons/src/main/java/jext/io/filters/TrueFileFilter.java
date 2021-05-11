package jext.io.filters;

import java.io.File;
import java.io.FileFilter;

public class TrueFileFilter implements FileFilter {

    public static final TrueFileFilter INSTANCE = new TrueFileFilter();

    @Override
    public boolean accept(File pathname) {
        return true;
    }
}
