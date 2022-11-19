package jext.text;

import jext.io.filters.FileFilters;
import jext.util.FileUtils;
import jext.util.concurrent.Serial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class TokensScanner {

    public static TokensCounter scan(File scanDirectory, String ext) throws IOException {
        return scan(scanDirectory, new File(scanDirectory, ".tokens"), ext);
    }

    public static TokensCounter scan(File scanDirectory, File scanOutput, String ext) throws IOException {

        TokensCounter gtc = new TokensCounter();

        List<File> files = FileUtils.listFiles(scanDirectory, FileFilters.of(ext), FileFilters.excluding(ext, ".*"));

        Serial.forEach(files, file->{
            // System.out.println(file);

            TokensCounter tc = new TokensCounter();
            try(TokensIterator tit = new TokensIterator(file)) {
                while (tit.hasNext())
                    tc.add(tit.next());
            }
            catch(Exception e) { }

            synchronized (gtc) {
                gtc.add(tc);
            }
            save(tc, scanDirectory, file, scanOutput);
        });

        save(gtc, scanDirectory, new File(scanDirectory, "counts.csv"), scanOutput);

        // Files.walkFileTree(scanDirectory.toPath(), new FileVisitor<Path>() {
        //     @Override
        //     public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        //         return FileVisitResult.CONTINUE;
        //     }
        //
        //     @Override
        //     public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        //         File file = path.toFile();
        //         if (!file.getName().endsWith(ext))
        //             return FileVisitResult.CONTINUE;
        //
        //         System.out.println(path);
        //
        //         TokensCounter tc = new TokensCounter();
        //         try(TokensIterator tit = new TokensIterator(file)) {
        //             while (tit.hasNext())
        //                 tc.add(tit.next());
        //         }
        //         catch(Exception e) {
        //             throw (IOException)e;
        //         }
        //
        //         gtc.add(tc);
        //         save(tc, scanDirectory, file, scanOutput);
        //
        //         return FileVisitResult.CONTINUE;
        //     }
        //
        //     @Override
        //     public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        //         return FileVisitResult.CONTINUE;
        //     }
        //
        //     @Override
        //     public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        //         return FileVisitResult.CONTINUE;
        //     }
        // });

        return gtc;
    }

    public static TokensCounter load(File countersFile) throws IOException {
        TokensCounter tc = new TokensCounter();

        try(LineNumberReader rdr = new LineNumberReader(new FileReader(countersFile))) {
            // skip header, totals
            String header = rdr.readLine();
            String totals = rdr.readLine();

            for(String line=rdr.readLine(); line != null; line=rdr.readLine()) {
                String[] parts = line.split(",");
                String token = parts[0];
                int count = Integer.parseInt(parts[1]);
                int documents = Integer.parseInt(parts[2]);

                tc.add(token, count, documents);
            }

            {
                String[] parts = totals.split(",");
                int count = Integer.parseInt(parts[1]);
                int documents = Integer.parseInt(parts[2]);
                tc.set(count, documents);
            }
        }

        return tc;
    }

    private static void save(TokensCounter tc, File baseDirectory, File file, File outputDirectory) {
        String bdir = baseDirectory.getAbsolutePath().replace('\\', '/');
        String fdir = file.getAbsolutePath().replace('\\', '/');
        String rdir = fdir.substring(bdir.length());
        File outputFile = new File(outputDirectory, rdir);
        outputFile.getParentFile().mkdirs();

        try(Writer wrt = new FileWriter(outputFile)) {
            wrt.write("token,count,docs\n");
            wrt.write(String.format("$counters,%d,%d\n", tc.tokens(), tc.documents()));
            tc.counters().forEach(c -> {
                try {
                    wrt.write(String.format("%s,%d,%d\n", c.token, c.count,c.documents));
                } catch (IOException e) { }
            });
        }
        catch(IOException e) { }
    }
}
