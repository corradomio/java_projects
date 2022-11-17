package org.hls.check;

import jext.text.TokensCounter;
import jext.text.TokensIterator;
import jext.text.TokensPredicate;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main1(String[] args) {

        Predicate<List<String>> p = TokensPredicate.parse("a*");

        p = TokensPredicate.parse("A&B");

    }

    public static void main(String[] args) throws IOException {
        TokensCounter tc = new TokensCounter();

        Files.walkFileTree(new File("D:\\SPLGroup\\SPLDevelopment3.0\\splserver3.3\\splserver").toPath(),
            new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if(dir.toString().equals(".jext-jgit"))
                    return FileVisitResult.SKIP_SUBTREE;
                else
                    return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                TokensCounter fc = new TokensCounter();

                // System.out.println(file);
                if (file.toString().endsWith(".java")) {
                    // System.out.println(file);
                    try(TokensIterator tokit = new TokensIterator(file.toFile())) {
                        while (tokit.hasNext())
                            fc.add(tokit.next());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                tc.add(fc);

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


        // File f = new File("D:\\Projects.github\\other_projects\\jgrapht\\jgrapht-ext\\src\\main\\java\\org\\jgrapht\\ext\\JGraphXAdapter.java");
        //
        // try(TokensIterator tokit = new TokensIterator(f)) {
        //     while (tokit.hasNext())
        //         tc.add(tokit.next());
        // }
        // catch (Exception e) {
        //
        // }

        // try(Reader rdr = new FileReader("D:\\Projects.github\\other_projects\\jgrapht\\jgrapht-ext\\src\\main\\java\\org\\jgrapht\\ext\\JGraphXAdapter.java")) {
        //     TokensIterator tokit = new TokensIterator(rdr);
        //
        //     while(tokit.hasNext())
        //         tc.add(tokit.next());
        // }

        tc.counters(3, 3).forEach(c -> {
            System.out.printf("%s: (%d, %d) tdifd: %f\n", c.token, c.documents, c.count, tc.tfidf(c));
        });
    }
}
