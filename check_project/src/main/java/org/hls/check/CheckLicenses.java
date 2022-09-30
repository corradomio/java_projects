package org.hls.check;

import jext.sourcecode.project.lfm.LicenseFinder;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CheckLicenses {

    public static void main(String[] args) throws IOException {

        LicenseFinder.findLicense(new File("d:\\Etc\\tabby\\resources\\app.asar.unpacked\\node_modules\\libnpmorg\\LICENSE"));


        Files.walkFileTree(new File("d:/").toPath(), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                String name = dir.toFile().getName();
                if (name.startsWith("$") || name.startsWith("."))
                    return FileVisitResult.SKIP_SUBTREE;
                else
                    return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                File file = path.toFile();
                if (file.isFile() && file.getName().contains("LICENSE"))
                    printLicense(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void printLicense(File licenseFile) {
        String license = LicenseFinder.findLicense(licenseFile);
        if (license.equals("Undefined"))
            System.out.printf("%s:  %s\n", licenseFile, LicenseFinder.findLicense(licenseFile));
    }
}
