package jext.eclipse.shapeeditor.editors.dbf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;

public class DbfFileModel {

    private final List<String> columnNames = new ArrayList<>();
    private final List<Object[]> rows = new ArrayList<>();

    public void load(File file) throws IOException {
        columnNames.clear();
        rows.clear();

        try (FileInputStream input = new FileInputStream(file);
                DbaseFileReader reader = new DbaseFileReader(
                        input.getChannel(), false, StandardCharsets.ISO_8859_1)) {
            DbaseFileHeader header = reader.getHeader();
            for (int i = 0; i < header.getNumFields(); i++) {
                columnNames.add(header.getFieldName(i));
            }

            while (reader.hasNext()) {
                Object[] row = reader.readEntry();
                if (row != null) {
                    rows.add(row);
                }
            }
        }
    }

    public List<String> getColumnNames() {
        return new ArrayList<>(columnNames);
    }

    public List<Object[]> getRows() {
        return new ArrayList<>(rows);
    }
}
