package jext.jgrapht.nio.file;

import org.jgrapht.Graph;
import org.jgrapht.nio.ExportException;
import org.jgrapht.nio.GraphExporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class permit to use an GraphExporter exporting the graph
 * directly in a compressed file.
 * .
 * The compression algorithm is selected from the file extension.
 * The supported formats are: '.gz', '.zip'
 *
 */
public class FileGraphExporter<V, E> implements GraphExporter<V, E> {

    private GraphExporter<V, E> exporter;

    public FileGraphExporter(GraphExporter<V, E> exporter) {
        this.exporter = exporter;
    }

    public void exportGraph(Graph<V, E> g, Writer writer) {
        exporter.exportGraph(g, writer);
    }

    public void exportGraph(Graph<V, E> g, File file) {
        String fname = file.getName().toLowerCase();
        if (fname.endsWith(".gz"))
            try (FileOutputStream fout = new FileOutputStream(file);
                 GZIPOutputStream gzos = new GZIPOutputStream(fout)) {

                exportGraph(g, new OutputStreamWriter(gzos));
                gzos.finish();
            } catch (IOException e) {
                throw new ExportException(e);
            }
        else if (fname.endsWith(".zip"))
            try (FileOutputStream fout = new FileOutputStream(file);
                 ZipOutputStream zos = new ZipOutputStream(fout)) {
                String name = file.getName();
                name = name.substring(0, name.lastIndexOf('.'));
                ZipEntry e = new ZipEntry(name);
                zos.putNextEntry(e);

                exportGraph(g, new OutputStreamWriter(zos));
                zos.closeEntry();
            } catch (IOException e) {
                throw new ExportException(e);
            }

        else
            try (OutputStreamWriter writer  =
                         new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))
            {
                exportGraph(g, writer);
            } catch (IOException e) {
                throw new ExportException(e);
            }
    }
}
