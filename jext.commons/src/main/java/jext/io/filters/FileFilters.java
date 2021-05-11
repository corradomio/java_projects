package jext.io.filters;

import java.io.FileFilter;
import java.util.Arrays;

public class FileFilters {

    public static final FileFilter IS_JAR = file -> (file.getName().endsWith(".jar") || file.getName().endsWith(".aar"));
    public static final FileFilter IS_JMOD = file -> file.getName().endsWith(".jmod");
    public static final FileFilter IS_SPL = file -> file.getName().endsWith(".spl");

    public static FileFilter and(FileFilter ... filters) {
        AndFileFilter andff = new AndFileFilter();
        for (FileFilter filter : filters)
            andff.add(filter);
        return andff;
    }

    public static FileFilter or(FileFilter ... filters) {
        OrFileFilter orff = new OrFileFilter();
        for (FileFilter filter : filters)
            orff.add(filter);
        return orff;
    }

    public static FileFilter not(FileFilter filter) {
        return new NotFileFilter(filter);
    }

    public static FileFilter wildcards(String ... patterns) {
        return new WildcardFileFilter(Arrays.asList(patterns));
    }
}
