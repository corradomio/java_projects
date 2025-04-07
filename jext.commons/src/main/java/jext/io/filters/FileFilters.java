package jext.io.filters;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class FileFilters {

    public static final FileFilter IS_DIRECTORY = File::isDirectory;
    public static final FileFilter IS_FILE = File::isFile;

    public static final FileFilter IS_JAR = file -> (file.getName().endsWith(".jar") || file.getName().endsWith(".aar"));
    public static final FileFilter IS_DLL = file -> file.getName().endsWith(".dll");
    // public static final FileFilter IS_SPL = file -> file.getName().endsWith(".spl");

    public static final FileFilter TRUE = TrueFileFilter.INSTANCE;
    public static final FileFilter FALSE = FalseFileFilter.INSTANCE;


    public static FileFilter none() {
        return FalseFileFilter.INSTANCE;
    }

    public static FileFilter of(String ext) {
        if (ext.contains(",") || ext.contains(" ") || ext.contains("|")) {
            String[] exts = ext.split("[, |]");
            OrFileFilter orfilter = (OrFileFilter) or();
            for(String ext1: exts)
                orfilter.add(of(ext1));
            return orfilter;
        }
        else {
            return file -> file.getName().endsWith(ext);
        }
    }

    public static FileFilter excluding(String... pattern) {
        return not(wildcards(pattern));
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

    public static FileFilter wildcards(String ... patterns) {
        return new WildcardFileFilter().addAll(Arrays.asList(patterns));
    }

    public static FileFilter wildcards(List<String> patterns) {
        return new WildcardFileFilter().addAll(patterns);
    }

    public static FileFilter hasExtension(String extension) {
        return file -> file.getName().endsWith(extension);
    }

}
