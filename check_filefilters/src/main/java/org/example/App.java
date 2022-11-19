package org.example;

import jext.io.filters.FileFilters;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        String ext = ".java";
        File scanDirectory = new File("D:\\Projects\\elasticsearch-8.5.1");
        List<File> files = FileUtils.listFiles(scanDirectory, FileFilters.of(ext), FileFilters.excluding(".*"));
        System.out.println(files.size());

    }
}
