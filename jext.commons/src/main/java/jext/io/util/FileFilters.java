package jext.io.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileFilters {

    public static class AndFilter implements FileFilter {

        private List<FileFilter> filters = new ArrayList<>();

        public AndFilter() {

        }

        public AndFilter(FileFilter ... filters) {
            this.filters.addAll(Arrays.asList(filters));
        }

        @Override
        public boolean accept(File pathname) {
            for (FileFilter filter : filters)
                if (!filter.accept(pathname))
                    return false;
            return true;
        }

        public AndFilter and(FileFilter filter) {
            filters.add(filter);
            return this;
        }
    }

    public static class OrFilter implements FileFilter {

        private List<FileFilter> filters = new ArrayList<>();

        public OrFilter() {

        }

        public OrFilter(FileFilter ... filters) {
            this.filters.addAll(Arrays.asList(filters));
        }

        @Override
        public boolean accept(File pathname) {
            for (FileFilter filter : filters)
                if (filter.accept(pathname))
                    return true;
            return false;
        }

        public OrFilter or(FileFilter filter) {
            filters.add(filter);
            return this;
        }
    }

    public static class NotFilter implements FileFilter {

        private FileFilter filter;

        public NotFilter() {

        }

        public NotFilter(FileFilter filter) {
            this.filter = filter;
        }

        @Override
        public boolean accept(File pathname) {
            return !filter.accept(pathname);
        }

        public NotFilter not(FileFilter filter) {
            this.filter = filter;
            return this;
        }
    }

    public static class HasExtensionFilter implements FileFilter {

        private List<String> extensions;

        public HasExtensionFilter(String ... extensions) {
            this.extensions.addAll(Arrays.asList(extensions));
        }

        @Override
        public boolean accept(File pathname) {
            for (String ext : extensions)
                if (pathname.getName().endsWith(ext))
                    return true;
            return false;
        }

    }

    public static final FileFilter IS_JAR = file -> (file.getName().endsWith(".jar") || file.getName().endsWith(".aar"));
    public static final FileFilter IS_JMOD = file -> file.getName().endsWith(".jmod");
    public static final FileFilter IS_JAVA = file -> file.getName().endsWith(".java");

}
