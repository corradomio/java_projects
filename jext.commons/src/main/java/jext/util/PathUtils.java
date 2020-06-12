package jext.util;

/**
 * Some utilities on th 'path', a string with the structure of a
 * Linux file path:
 *
 * 		(<directory>/)*<file_or_directory_name_without_ext>[.<ext>]?
 *
 * @author Corrado Mio
 *
 */
public class PathUtils {

    private static final String EMPTY_PATH = "";
    private static final String SLASH = "/";
    private static final String BACK_SLASH = "\\";

    /**
     * Change '\' in '/'
     * Remove '/' from the head of the path
     */
    public static String normalize(String path) {
        if (path == null) return EMPTY_PATH;
        path = path.replace(BACK_SLASH, SLASH);
        while(path.startsWith(SLASH)) path = path.substring(1);
        return path;
    }

    /**
     * Compose two strings as a path
     */
    public static String concat(String prefix, String suffix) {
        prefix = normalize(prefix);
        suffix = normalize(suffix);

        if (!isValid(prefix) && !isValid(suffix))
            return EMPTY_PATH;

        if (prefix.endsWith(SLASH) && suffix.startsWith(SLASH))
            suffix = suffix.substring(1);

        if (!isValid(prefix)) return suffix;
        if (!isValid(suffix)) return prefix;
        if (prefix.endsWith(SLASH) || suffix.startsWith(SLASH))
            return String.format("%s%s", prefix, suffix);
        else
            return String.format("%s/%s", prefix, suffix);
    }

    public static String concat(String prefix, String... suffixes) {
        String suffix = StringUtils.compose(suffixes, SLASH);
        return concat(prefix, suffix);
    }

    /**
     * Check if path is valid (!= null and length > 0)
     */
    public static boolean isValid(String path) {
        return path != null && path.length() > 0;
    }

    /**
     * Return the relative path
     *
     * @param basePath base path
     * @param currentPath current path
     * @return the relative path
     */
    public static String relativePath(String basePath, String currentPath) {
        assert currentPath.startsWith(basePath);

        if (basePath.equals(currentPath))
            return EMPTY_PATH;
        else if (basePath.endsWith(SLASH))
            return currentPath.substring(basePath.length());
        else
            return currentPath.substring(basePath.length()+1);
    }

    /**
     * Return the parent of the path, the path WITHOUT the last component
     */
    public static String getParent(String path) {
        int pos;
        while (path.endsWith(SLASH)) path = path.substring(0, path.length()-1);
        if ((pos = path.lastIndexOf(SLASH)) != -1)
            return path.substring(0, pos);
        else
            return EMPTY_PATH;
    }

    /**
     * Return the last component of the path
     */
    public static String getName(String path) {
        int pos = path.lastIndexOf(SLASH);
        return pos > 0 ? path.substring(pos+1) : path;
    }

    /**
     * Return the last component WITHOUT the extension
     */
    public static String getNameWithoutExt(String path) {
        String name = getName(path);
        int sep = name.lastIndexOf('.');
        return sep != -1 ? name.substring(0, sep) : name;
    }

    public static String getExtension(String path) {
        String name = getName(path);
        int sep = name.lastIndexOf('.');
        return sep != -1 ? name.substring(sep) : "";
    }

    /**
     * Return the 'version' of the path
     *
     *  - spring-expression-5.1.3.RELEASE-8F89198BCDF78F00D72A45FB288F6597
     *  - spring-jcl-5.1.0.BUILD-SNAPSHOT-54558D54E93F02C29A56DCA5A20F3C44
     *
     */
    public static String getVersion(String path) {
        String name = getNameWithoutExt(path);
        int sep;

        // ...-<MD5>.txt
        sep = name.lastIndexOf('-');
        if (name.length() - sep > 25)
            name = name.substring(0, sep);

        sep = name.indexOf('-');
        while (startsWithLetter(name) && sep != -1) {
            name = name.substring(sep+1);
            sep = name.indexOf('-');
        }

        if (startsWithLetter(name))
            return "";
        else
            return name;
    }

    private static boolean startsWithLetter(String name) {
        if (name.length() == 0)
            return false;
        char c = name.charAt(0);
        return !('0' <= c && c <= '9');
    }

    public static String removeLastSlash(String url) {
        while (url.endsWith("/"))
            url = url.substring(0, url.length()-1);
        return url;
    }

}
