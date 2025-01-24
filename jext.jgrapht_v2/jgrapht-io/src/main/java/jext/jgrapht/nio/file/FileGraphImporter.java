package jext.jgrapht.nio.file;

import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;
import org.jgrapht.nio.ImportException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class permit to use an GraphImporter to import the graph
 * directly from a compressed file.
 * .
 * The compression algorithm is selected from the file extension.
 * The supported formats are: '.gz', '.zip'
 *
 */
public class FileGraphImporter<V, E> implements GraphImporter<V, E> {

    private GraphImporter<V, E> importer;

    public FileGraphImporter(GraphImporter<V, E> importer) {
        this.importer = importer;
    }

    @Override
    public void importGraph(Graph<V, E> g, Reader in) {
        importer.importGraph(g, in);
    }

    @Override
    public void importGraph(Graph<V, E> g, File file) {
        if (file.getName().toLowerCase().endsWith(".zip"))
            try(FileInputStream fis = new FileInputStream(file)) {
                ZipInputStream zis = new ZipInputStream(fis);
                ZipEntry entry = zis.getNextEntry();

                importGraph(g, new InputStreamReader(zis));
            } catch (IOException e) {
                throw new ImportException(e);
            }
        else if (file.getName().toLowerCase().endsWith(".gz"))
            try (FileInputStream fis = new FileInputStream(file)) {
                GZIPInputStream gzis = new GZIPInputStream(fis);

                importGraph(g, new InputStreamReader(gzis));
            } catch (IOException e) {
                throw new ImportException(e);
            }
        else
            try (InputStreamReader reader =
                         new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
            {
                importGraph(g, reader);
            } catch (IOException e) {
                throw new ImportException(e);
            }
    }
}
