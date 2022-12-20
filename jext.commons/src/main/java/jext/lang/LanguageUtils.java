package jext.lang;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LanguageUtils {

    private static final String UNKNOWN = "unknown";

    private static final Map<String,String> LANGUAGES = new HashMap<String,String>(){{
        put(".py", "python");
        put(".java", "java");
        put(".scala", "scala");
        put(".cs", "csharp");
        put(".c", "c");
        put(".h", "c");
        put(".cpp", "c++");
        put(".cxx", "c++");
        put(".hpp", "c++");
        put(".hxx", "c++");
        put(".htm", "html");
        put(".html", "html");
        put(".css", "css");
        put(".xsl", "xsl");
    }};

    /**
     * Return the programming language used in the source code, based on the file extension
     */
    public static String guessLanguage(File file) {
        String extension = extensionOf(file.getName());
        return LANGUAGES.getOrDefault(extension, UNKNOWN);
    }

    private static String extensionOf(String name) {
        int sep = name.lastIndexOf('.');
        return sep != -1 ? name.substring(sep) : "";
    }
}
