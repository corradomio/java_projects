package jext.vfs.util;


import jext.logging.Logger;
import jext.util.MimeTypes;
import jext.vfs.VFileName;

public class FilePath implements VFileName {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(FilePath.class);
    private String path;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public FilePath(String path) {
        this.path = normalize(path);
        assert path.length() == 0 || path.startsWith("/");
    }

    public FilePath(String prefix, String suffix) {
        this(concat(prefix, suffix));
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isRoot() {
        return path.length() == 0;
    }

    @Override
    public String getName() {
        return nameOf(path);
    }

    @Override
    public String getNameWithoutExt() {
        return nameOnlyOf(path);
    }

    @Override
    public String getExt() {
        return extensionOf(path);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getParentPath() {
        return parentOf(path);
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public String getMimeType() {
        return MimeTypes.guessMimeType(path);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Static operations
    // ----------------------------------------------------------------------

    /**
     *  Normalize
     *  ---------------------------------------------------------------------
     *
     *  "a\b"       -> "a/b"
     *  "a//b"      -> "a/b"
     *  "/a"        -> "a"
     *  "a/"        -> "a"
     *  "/"         -> ""
     *
     *  Then
     *  ---------------------------------------------------------------------
     *
     *  "a"         -> "/a"
     *
     *  Note:
     *  ---------------------------------------------------------------------
     *
     *  "a/./b"     -> "a/b"
     *  "a//b"      -> "a/b"
     *  "a/../b"    -> "b"
     *  "a/b/.../c" -> "c"
     *
     * @param path
     */
    public static String normalize(String path) {
        path = path
            .replace("\\", "/")
            .replace("//", "/");

        while (path.startsWith("/"))
            path = path.substring(1);
        while (path.endsWith("/"))
            path = path.substring(0, path.length()-1);

        if ("".equals(path))
            return "";
        else
            return "/" + path;
    }

    /**
     *
     */
    public static String concat(String prefix, String suffix) {
        while (prefix.endsWith("/"))
            prefix = prefix.substring(0, prefix.length()-1);
        while (suffix.startsWith("/"))
            suffix = suffix.substring(1);

        if (prefix.length() == 0)
            return suffix;
        if (suffix.length() == 0)
            return prefix;
        else
            return String.format("%s/%s", prefix, suffix);
    }

    public static String nameOf(String path) {
        int pos = path.lastIndexOf('/');
        return path.substring(pos+1);
    }

    public static String nameOnlyOf(String path) {
        int pos = path.lastIndexOf('/');
        int sep = path.lastIndexOf('.');

        if (pos == -1 && sep == -1)
            return path;
        if (pos == -1)
            return path.substring(0, sep);
        if (sep == -1)
            return path.substring(pos+1);
        else
            return path.substring(pos+1, sep);
    }

    public static String extensionOf(String path){
        int pos = path.lastIndexOf('/');
        int sep = path.lastIndexOf('.');
        if (sep > pos)
            return path.substring(sep);
        else
            return "";
    }

    public static String parentOf(String path) {
        int pos = path.lastIndexOf('/');
        return pos >= 0 ? path.substring(0, pos) : "";
    }

    public static String relative(String root, String path) {
        root = normalize(root);
        path = normalize(path);
        if (!path.startsWith(root))
            return "";
        return path.substring(root.length());
    }
}
