package jext.io.filters;

import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class FileFilters {

    public static final FileFilter IS_JAR = file -> (file.getName().endsWith(".jar") || file.getName().endsWith(".aar"));
    public static final FileFilter IS_SPL = file -> file.getName().endsWith(".spl");
    public static final FileFilter IS_DLL = file -> file.getName().endsWith(".dll");
    public static final FileFilter TRUE = TrueFileFilter.INSTANCE;
    public static final FileFilter FALSE = FalseFileFilter.INSTANCE;

    public static FileFilter of(String filters) {
        String[] exts;
        if (filters.contains(","))
            exts = filters.split(",");
        else if (filters.contains("|"))
            exts = filters.split("|");
        else
            exts = new String[]{filters};

        if (exts.length == 1)
            return filter(exts[0]);

        OrFileFilter orf = new OrFileFilter();
        for (String ext : exts)
            orf.add(filter(ext));
        return orf;
    }

    private static FileFilter filter(String filter) {
        boolean not = filter.startsWith("!");
        if (not)
            filter = filter.substring(1);

        String pattern = filter;
        FileFilter ff;

        if(pattern.contains("*") || pattern.contains("?"))
            ff = wildcard(pattern);
        else if (pattern.startsWith("."))
            ff = file -> file.getName().endsWith(pattern);
        else
            ff = file -> file.getName().equals(pattern);

        if (not)
            ff = not(ff);

        return ff;
    }

    public static FileFilter excluding(String ... patterns) {
        FileFilter select;
        if (patterns.length == 1) {
            select = filter(patterns[0]);
        }
        else {
            OrFileFilter or = new OrFileFilter();
            for(String pattern : patterns)
                or.add(filter(pattern));
            select = or;
        }

        return not(select);
    }


    public static FileFilter none() {
        return FalseFileFilter.INSTANCE;
    }

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

    public static FileFilter wildcard(String pattern) {
        return new WildcardFilter(pattern);
    }

    public static FileFilter wildcards(String ... patterns) {
        return new WildcardFileFilter().addAll(Arrays.asList(patterns));
    }

    public static FileFilter wildcards(List<String> patterns) {
        return new WildcardFileFilter().addAll(patterns);
    }
}
