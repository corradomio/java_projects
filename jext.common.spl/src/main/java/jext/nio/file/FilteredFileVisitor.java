package jext.nio.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;

public class FilteredFileVisitor<T> extends java.nio.file.SimpleFileVisitor<T> {

    @Override
    public FileVisitResult visitFileFailed(T file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(T dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(T file, BasicFileAttributes attrs) throws IOException {
        if (filterFile(file))
            onVisitFile(file, attrs);
        return FileVisitResult.CONTINUE;
    }

    public boolean filterFile(T file) {
        return true;
    }

    public void onVisitFile(T file, BasicFileAttributes attrs) throws IOException {

    }

}
