package org.hls.example;

import jext.geotools.ShapeFileToImage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PreviewShapeFile {

    private static boolean isShapeFile(Path file) {
        return file.getFileName().toString().endsWith(".shp");
    }


    public static void main(String[] args) {
        Path ROOT = Path.of("D:\\Projects.ebtic\\project.ftth\\EbticDATA");

        try (Stream<Path> stream = Files.walk(ROOT)) {
            stream.filter(PreviewShapeFile::isShapeFile).forEach(path -> {
                try {
                    ShapeFileToImage.writeImage(path.toFile(), ".svg");
                    System.out.println(">> " + path.getFileName());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
