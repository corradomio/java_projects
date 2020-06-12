package jext.util;

import jext.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MimeTypes {

    private static Logger logger = Logger.getLogger(MimeTypes.class);

    private static final String UNKNOWN = "application/generic";

    private static java.util.Map<String, String> MIMETYPES;

    public static String guessMimeType(File file) {
        return guessMimeType(file.getName());
    }

    public static String guessMimeType(String name) {
        checkMimeTypes();
        String ext = extensionOf(name);

        return MIMETYPES.getOrDefault(ext, UNKNOWN);
    }

    private static void checkMimeTypes() {
        if (MIMETYPES != null)
            return;

        Map<String,String> mimetypes = new HashMap<>();
        try {
            String line;
            InputStream in = MimeTypes.class.getResourceAsStream("mimetypes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while(null != (line = reader.readLine())) {
                line = line.trim();

                if (line.length() == 0 || line.startsWith("#") || line.startsWith(";"))
                    continue;

                String[] parts = line.split("\\s+");
                if(parts.length < 2)
                    continue;

                String ext = parts[0].trim();
                String type = parts[1].trim();

                if (mimetypes.containsKey(ext))
                    logger.warn(String.format("Mimetype %s (%s) redefined as %s", ext, mimetypes.get(ext), type));
                mimetypes.put(parts[0].trim(), parts[1].trim());
            }

        }
        catch (Exception e) {
            logger.error(e, e);
        }
        MIMETYPES = mimetypes;
    }

    private static String extensionOf(String name){
        int sep = name.lastIndexOf('.');
        if (sep != -1)
            return name.substring(sep);
        else
            return "";
    }

}
