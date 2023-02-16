package jext.text.tokens;

import jext.io.filters.FileFilters;
import jext.logging.Logger;
import jext.util.FileUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Optional;

public class TokensScanner {

    private static Logger logger = Logger.getLogger(TokensScanner.class);

    public static void scan(List<File> scanDirs, String ext) throws IOException {
        for(File dir : scanDirs)
            scan(dir, ext);

    }

    public static TokensScanner scan(File scanDirectory, String ext) throws IOException {
        return scan(scanDirectory, new File(scanDirectory, ".tokens"), ext);
    }

    private static TokensScanner scan(File scanDirectory, File outputDirectory, String ext) throws IOException {
        TokensScanner tokscan = new TokensScanner(scanDirectory, ext);
        tokscan.setOutputDirectory(outputDirectory);
        return tokscan.scan();
    }

    // public static TokensCounter load(File countersFile) throws IOException {
    //     TokensCounter tc = new TokensCounter();
    //
    //     try(LineNumberReader rdr = new LineNumberReader(new FileReader(countersFile))) {
    //         // skip header, totals
    //         String header = rdr.readLine();
    //         String totals = rdr.readLine();
    //
    //         for(String line=rdr.readLine(); line != null; line=rdr.readLine()) {
    //             String[] parts = line.split(",");
    //             String token = parts[0];
    //             int count = Integer.parseInt(parts[1]);
    //             int documents = Integer.parseInt(parts[2]);
    //
    //             tc.add(token, count, documents);
    //         }
    //
    //         {
    //             String[] parts = totals.split(",");
    //             int count = Integer.parseInt(parts[1]);
    //             int documents = Integer.parseInt(parts[2]);
    //             tc.set(count, documents);
    //         }
    //     }
    //
    //     return tc;
    // }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final File scanDirectory;
    private final FileFilter acceptFiles;
    private File outputDirectory;
    private FileFilter rejectFiles;
    private final TokensCounter gtc;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public TokensScanner(File scanDirectory, String ext) {
        this.scanDirectory = scanDirectory;
        this.acceptFiles = FileFilters.of(ext);
        this.rejectFiles = FileFilters.of(".*");
        this.gtc = new TokensCounter(".");
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public TokensCounter getGlobalCounter() {
        return gtc;
    }

    public List<TokensCounter> getFileCounters() {
        return gtc.fileCounters();
    }

    @Nullable
    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(boolean save) {
        if (save)
            setOutputDirectory(new File(scanDirectory, ".tokens"));
    }
    public void setOutputDirectory(File saveDirectory) {
        this.outputDirectory = saveDirectory;
    }

    public void setRejectFilter(String filter) {
        setRejectFilter(FileFilters.of(filter));
    }
    public void setRejectFilter(FileFilter filter) {
        this.rejectFiles = filter;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public TokensScanner scan() throws IOException {
        logger.infof("Scan '%s'", scanDirectory);

        int[] count = new int[1];
        // TokensCounter gtc = new TokensCounter(scanDirectory);

        Files.walkFileTree(scanDirectory.toPath(), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                File file = dir.toFile();
                if (rejectFiles.accept(file))
                    return FileVisitResult.SKIP_SUBTREE;
                else
                    return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                File file = path.toFile();
                if (!acceptFiles.accept(file))
                    return FileVisitResult.CONTINUE;

                count[0]++;
                String rpath = FileUtils.relativePath(scanDirectory, file);
                TokensCounter tc = new TokensCounter(rpath);
                try(TokensIterator tit = new TokensIterator(file)) {
                    while (tit.hasNext()) {
                        tc.add(tit.next());
                    }
                }
                catch(Exception e) { }

                synchronized (gtc) {
                    gtc.add(tc);
                    logger.debugft("... processed %d files", gtc.size());
                }

                save(tc, file, false);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        save(gtc, new File(scanDirectory, "tokens-count"), true);

        logger.infof("done (%d)", gtc.size());

        return this;
    }

    private void save(TokensCounter tc, File file, boolean saveCounts) {
        if (outputDirectory == null)
            return;

        String bdir = scanDirectory.getAbsolutePath().replace('\\', '/');
        String fdir = file.getAbsolutePath().replace('\\', '/');
        String rdir = fdir.substring(bdir.length());

        File tokensFile = new File(outputDirectory, rdir + ".tokens");
        tokensFile.getParentFile().mkdirs();

        try(Writer wrt = new FileWriter(tokensFile)) {
            // wrt.write("token\n");
            tc.words().forEach(word -> {
                try {
                    wrt.write(String.format("%s\n", word));
                } catch (IOException e) { }
            });
        }
        catch(IOException e) { }

        if (!saveCounts) return;

        File countFile = new File(outputDirectory, rdir + ".count");
        try(Writer wrt = new FileWriter(countFile)) {
            wrt.write("token,count,docs\n");
            wrt.write(String.format("$counters,%d,%d\n", tc.count(), tc.documents()));
            tc.counters().forEach(c -> {
                try {
                    wrt.write(String.format("%s,%d,%d\n", c.token, c.count,c.documents));
                } catch (IOException e) { }
            });
        }
        catch(IOException e) { }
    }

}
