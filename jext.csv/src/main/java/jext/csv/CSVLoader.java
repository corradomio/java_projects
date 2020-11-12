package jext.csv;

import org.assertj.core.api.Assertions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVLoader {
    private String separator = ",";
    private List<String> columnTypes;
    private List<String> columnNames;
    private int skipLines = 0;
    private String comment = "#";
    private boolean header = false;
    private int nColumns = 0;
    private String missing = "?";
    private int targetCol = -1;
    private Map<Integer, Map<String,Integer>> enums = new HashMap<>();

    private List<Object[]> data;
    private List<Object> target;

    public CSVLoader() { }

    public CSVLoader withSeparator(String sep) {
        this.separator = sep;
        return this;
    }

    public CSVLoader withComment(String comment) {
        Assertions.assertThat(comment).isNotNull();
        Assertions.assertThat(comment).isNotEmpty();
        this.comment = comment;
        return this;
    }

    public CSVLoader withMissing(String missing) {
        this.missing = missing;
        return this;
    }

    public CSVLoader withTarget(int tcol) {
        this.targetCol = tcol;
        return this;
    }

    public CSVLoader withSkipLines(int nSkip) {
        this.skipLines = nSkip;
        return this;
    }

    public CSVLoader withHeader(boolean header) {
        this.header = header;
        return this;
    }

    public CSVLoader withColumnTypes(String colType, int nCols) {
        if (columnTypes == null)
            columnTypes = new ArrayList<>();
        for(int i = 0; i < nCols; ++i)
            columnTypes.add(colType);
        return this;
    }

    public CSVLoader withColumnNames(String... colNames) {
        if (columnNames == null)
            columnNames = new ArrayList<>();
        columnNames.addAll(Arrays.asList(colNames));
        return this;
    }

    public CSVLoader load(File file) {
        int skipped = 0;
        int iLine = 0;
        data = new ArrayList<>();
        target = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
            for(String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.startsWith(comment)) {
                    continue;
                }
                if (skipped < skipLines) {
                    ++skipped;
                    continue;
                }

                String[] parts = trim(line.split(separator));

                if (iLine == 0 && header) {
                    columnNames = Arrays.asList(parts);
                    nColumns = parts.length;
                    iLine += 1;
                    continue;
                }

                if (iLine == 0 && columnTypes == null) {
                    columnTypes = new ArrayList<>();
                    for(int i=0; i<parts.length; ++i)
                        columnTypes.add("string");
                }

                if (iLine == 0 && columnNames == null) {
                    columnNames = new ArrayList<>();
                    for(int i=0; i<parts.length; ++i)
                        columnNames.add(String.format("c%d", i+1));
                }

                if (iLine == 0) {
                    nColumns = parts.length;
                    if (columnTypes.size() != nColumns)
                        throw new RuntimeException(String.format("N of column types doesn't match the n  of columns: %d != %d", columnTypes.size(), nColumns));
                    if (columnNames.size() != nColumns)
                        throw new RuntimeException(String.format("N of column types doesn't match the n  of columns: %d != %d", columnTypes.size(), nColumns));
                }

                Object[] record = convert(parts);

                if (targetCol!= -1) {
                    target.add(record[targetCol]);
                    record[targetCol] = null;
                }

                data.add(record);

                iLine += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public List<Object[]> getData() {
        return data;
    }

    public List<Object> getTarget() {
        return target;
    }

    public List<Object> getColumn(String name) {
        return getColumn(colIndex(name));
    }

    public List<Object> getColumn(int index) {
        List<Object> column = new ArrayList<>();
        for (Object[] datum : data) column.add(datum[index]);
        return column;
    }

    private int colIndex(String name) {
        for(int i = 0; i<columnNames.size(); ++i)
            if (columnNames.get(i).equals(name))
                return i;
            return -1;
    }

    private String[] trim(String[] slist) {
        for (int i=0; i<slist.length; ++i) {
            String s = slist[i];
            s  = s.trim();
            if (s.startsWith("'") || s.startsWith("\"")) s = s.substring(1);
            if (s.endsWith("'") || s.endsWith("\"")) s = s.substring(0, s.length()-1);
            slist[i] = s;
        }

        return slist;
    }

    private Object[] convert(String[] parts) {
        Object[] record = new Object[nColumns];
        for (int i=0; i<nColumns; ++i) {
            String colType = columnTypes.get(i);
            String value = parts[i];

            if (value.equals(missing))
                record[i] = null;
            else if (value.length() == 0 && !colType.startsWith("str"))
                record[i] = null;
            else if (colType.startsWith("str"))
                record[i] = value;
            else if (colType.startsWith("skip"))
                record[i] = null;
            else if (colType.startsWith("int"))
                record[i] = Integer.parseInt(value);
            else if (colType.startsWith("long"))
                record[i] = Long.parseLong(value);
            else if (colType.startsWith("float"))
                record[i] = Float.parseFloat(value);
            else if (colType.startsWith("double"))
                record[i] = Double.parseDouble(value);
            else if (colType.startsWith("enum"))
                record[i] = parseEnum(i, value);
            else
                record[i] = value;
        }
        return record;
    }

    private Integer parseEnum(Integer colIndex, String value) {
        if (!enums.containsKey(colIndex))
            enums.put(colIndex, new HashMap<>());
        Map<String, Integer> emap = enums.get(colIndex);
        if (!emap.containsKey(value))
            emap.put(value, emap.size());
        return emap.get(value);
    }

}
