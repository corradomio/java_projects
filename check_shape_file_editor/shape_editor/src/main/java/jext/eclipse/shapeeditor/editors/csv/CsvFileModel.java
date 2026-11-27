package jext.eclipse.shapeeditor.editors.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CsvFileModel {

    private final List<String> columnNames = new ArrayList<>();
    private final List<Object[]> rows = new ArrayList<>();

    public void load(File file) throws IOException {
        columnNames.clear();
        rows.clear();

        String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        if (!content.isEmpty() && content.charAt(0) == '\ufeff') {
            content = content.substring(1);
        }

        List<List<String>> records = parse(content);
        if (records.isEmpty()) {
            return;
        }

        int columnCount = 0;
        for (List<String> record : records) {
            columnCount = Math.max(columnCount, record.size());
        }

        List<String> header = records.get(0);
        for (int i = 0; i < columnCount; i++) {
            String name = i < header.size() ? header.get(i) : "";
            columnNames.add(name.isEmpty() ? "Column " + (i + 1) : name);
        }

        for (int i = 1; i < records.size(); i++) {
            List<String> record = records.get(i);
            Object[] row = new Object[columnCount];
            for (int j = 0; j < columnCount; j++) {
                row[j] = j < record.size() ? record.get(j) : "";
            }
            rows.add(row);
        }
    }

    private static List<List<String>> parse(String content) throws IOException {
        List<List<String>> records = new ArrayList<>();
        if (content.isEmpty()) {
            return records;
        }

        List<String> record = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < content.length() && content.charAt(i + 1) == '"') {
                        field.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    field.append(c);
                }
            } else if (c == '"' && field.length() == 0) {
                inQuotes = true;
            } else if (c == ',') {
                record.add(field.toString());
                field.setLength(0);
            } else if (c == '\r' || c == '\n') {
                record.add(field.toString());
                field.setLength(0);
                addRecord(records, record);
                record = new ArrayList<>();
                if (c == '\r' && i + 1 < content.length() && content.charAt(i + 1) == '\n') {
                    i++;
                }
            } else {
                field.append(c);
            }
        }

        if (inQuotes) {
            throw new IOException("Unclosed quoted field");
        }

        char last = content.charAt(content.length() - 1);
        if (last != '\r' && last != '\n') {
            record.add(field.toString());
            addRecord(records, record);
        }
        return records;
    }

    private static void addRecord(List<List<String>> records, List<String> record) {
        if (record.size() > 1 || !record.get(0).isEmpty()) {
            records.add(record);
        }
    }

    public List<String> getColumnNames() {
        return new ArrayList<>(columnNames);
    }

    public List<Object[]> getRows() {
        return new ArrayList<>(rows);
    }
}
