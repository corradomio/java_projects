package jext.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Simple class to print a table with header on the console
 */
public class Tabulate {

    // ----------------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------------

    private List<String> header = new ArrayList<>();
    private List<List<?>> data = new ArrayList<>();
    private List<String> formats = null;
    private String rformat = null;
    private String iformat = null;
    private String bformat = null;
    private String margin = " ";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Tabulate() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * String used to separate the columns.
     * For default it is used a single space
     *
     * @param margin string to use as columns' separator
     * @return this
     */
    public Tabulate margin(String margin) {
        this.margin = margin;
        return this;
    }

    /**
     * String format used to format real values, if not already specified using 'format'
     *
     * @param fmt format string
     * @return this
     */
    public Tabulate realFormat(String fmt) {
        this.rformat = fmt;
        return this;
    }

    /**
     * String format used to format boolean values, if not already specified using 'format'
     *
     * @param fmt format string
     * @return this
     */
    public Tabulate booleanFormat(String fmt) {
        this.bformat = fmt;
        return this;
    }

    /**
     * String format used to format integer values, if not already specified using 'format'
     *
     * @param fmt format string
     * @return this
     */
    public Tabulate intFormat(String fmt) {
        this.iformat = fmt;
        return this;
    }

    /**
     * List of format strings to use for each column
     * Note: a column format can be null
     *
     * @param formats list of formats for each column
     * @return this
     */
    public Tabulate format(String... formats) {
        // ArrayList<String> list = new ArrayList<>();
        // for(String format : formats)
        //     list.add(format);
        // return format(list);
        return format(Arrays.asList(formats));
    }

    /**
     * List of format strings to use for each column
     * Note: a column format can be null
     *
     * @param formats list of formats for each column
     * @return this
     */
    public Tabulate format(List<String> formats) {
        if (formats.size() != header.size())
            throw new RuntimeException("Format length mismatch: not the same number of patterns as the header");
        this.formats = formats;
        return this;
    }

    /**
     * List of labels to use as header
     *
     * @param labels list of labels
     * @return this
     */
    public Tabulate header(String... labels) {
        // ArrayList<String> list = new ArrayList<>();
        // for(String label : labels)
        //     list.add(label);
        // return header(list);
        return header(Arrays.asList(labels));
    }

    /**
     * List of labels to use as header
     *
     * @param labels list of labels
     * @return this
     */
    public Tabulate header(Collection<String> labels) {
        header.addAll(labels);
        return this;
    }

    /**
     * Tabular data
     *
     * @param data tabular data
     * @return this
     */
    public Tabulate data(List<List<?>> data) {
        this.data = data;
        return this;
    }

    /**
     * Add a record / row of data
     *
     * @param values record values
     * @return this
     */
    public Tabulate add(Object... values) {
        // List<Object> record = new ArrayList<>();
        // for (Object obj : values)
        //     record.add(obj);
        // return add(record);
        return add(Arrays.asList(values));
    }

    /**
     * Add a record / row of data
     *
     * @param values record values
     * @return this
     */
    public Tabulate add(List<?> values) {
        if (values.size() != header.size())
            throw new RuntimeException("Record length mismatch: not the same number of columns as the header");
        data.add(values);
        return this;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    /**
     * Print the table
     */
    public void print() {
        List<List<String>> table = composeTable();
        int[] width = computeWidth(table);
        printTable(table, width);
    }

    @Override
    public String toString() {
        List<List<String>> table = composeTable();
        int[] width = computeWidth(table);
        return asString(table, width);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private List<List<String>> composeTable() {
        int i;
        List<List<String>> table = new ArrayList<>();

        table.add(header);

        for (List<?> record : data) {
            String s;
            List<String> srec = new ArrayList<>();

            i = 0;
            for (Object obj : record) {

                // -- null value
                if (obj == null) {
                    s = "-";
                }

                // -- boolean
                else if (obj instanceof Boolean) {
                    boolean b = (Boolean) obj;
                    String fmt = getFormat(i, bformat);
                    s = fmt != null ? String.format(fmt, b) : String.valueOf(b);
                }

                // -- integral value
                else if (obj instanceof Byte) {
                    Byte v = (Byte) obj;
                    String fmt = getFormat(i, iformat);
                    s = fmt != null ? String.format(fmt, v) : String.valueOf(v);
                }
                else if (obj instanceof Short) {
                    Short v = (Short) obj;
                    String fmt = getFormat(i, iformat);
                    s = fmt != null ? String.format(fmt, v) : String.valueOf(v);
                }
                else if (obj instanceof Integer) {
                    Integer v = (Integer) obj;
                    String fmt = getFormat(i, iformat);
                    s = fmt != null ? String.format(fmt, v) : String.valueOf(v);
                }
                else if (obj instanceof Long) {
                    long v = (Long) obj;
                    String fmt = getFormat(i, iformat);
                    s = fmt != null ? String.format(fmt, v) : String.valueOf(v);
                }

                // -- real value
                else if (obj instanceof Float) {
                    float v = (Float) obj;
                    String fmt = getFormat(i, rformat);
                    s = fmt != null ? String.format(fmt, v) : String.valueOf(v);
                }
                else if (obj instanceof Double) {
                    double v = (Double) obj;
                    String fmt = getFormat(i, rformat);
                    s = fmt != null ? String.format(fmt, v) : String.valueOf(v);
                }

                // -- string and other values
                else {
                    String fmt = getFormat(i, null);
                    s = fmt != null ? String.format(fmt, obj) : String.valueOf(obj);
                }

                srec.add(s);
                i++;
            }

            table.add(srec);
        }

        return table;
    }

    private String getFormat(int i, String defaultFormat) {
        if (formats == null) return defaultFormat;
        if (formats.get(i) == null) return defaultFormat;
        return formats.get(i);
    }

    private int[] computeWidth(List<List<String>> table) {
        int[] width = new int[header.size()];

        for (List<String> record : table) {
            int i = 0;
            for (String s : record) {
                width[i] = Math.max(width[i], s.length());
                i++;
            }
        }

        return width;
    }

    private void printTable(List<List<String>> table, int[] width) {
        int i;
        String[] formats = new String[width.length];

        for(i=0; i<width.length; ++i)
            formats[i] = "%" + String.valueOf(width[i]) + "s";

        for(List<String> record : table) {
            i=0;
            for(String s : record) {
                if (i > 0) System.out.print(margin);
                System.out.printf(formats[i], s);

                ++i;
            }
            System.out.println();
        }
    }

    private String asString(List<List<String>> table, int[] width) {
        StringBuilder sb = new StringBuilder();
        int i;
        String[] formats = new String[width.length];

        for(i=0; i<width.length; ++i)
            formats[i] = "%" + width[i] + "s";

        for(List<String> record : table) {
            i=0;
            for(String s : record) {
                if (i > 0)
                    sb.append(margin);
                sb.append(String.format(formats[i], s));

                ++i;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
