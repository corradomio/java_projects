package jext.util;

import java.io.File;
import java.util.Map;

public class LanguageUtils {

    private static final String UNKNOWN = "unknown";

    private static Map<String,String> LANGUAGES = new HashMap<String,String>()
        .put_(".py", "python")
        .put_(".java", "java")
        .put_(".scala", "scala")
        .put_(".c", "c")
        .put_(".h", "c++")
        .put_(".cpp", "c++")
        .put_(".cxx", "c++")
        .put_(".hpp", "c++")
        .put_(".hxx", "c++")
        .put_(".htm", "html")
        .put_(".html", "html")
        .put_(".css", "css")
        .put_(".xsl", "xsl");

    /**
     * Return the programming language used in the source code, based on the file extension
     */
    public static String guessLanguage(File file) {
        String extension = PathUtils.getExtension(file.getName());
        return LANGUAGES.getOrDefault(extension, UNKNOWN);
    }
}
