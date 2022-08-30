package jext.sourcecode.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Simple class used to validate a file.
 * It checks:
 *
 *  1) if the file exists
 *  2) if the file has length > 0
 *  3) based on the file type, if the file syntatically valid (text based file)
 *     or it is not corrupted (compressed file)
 *
 * It is necessary to pass a file type because there are several possible file
 * extensions for the same file type, and, in general, who uses this class
 * already knows the correct file type
 */
public class FileValidator {

    public enum Type {
        TEXT,
        HTML,
        XML,
        JSON,
        ZIP,
        GZ,
        TAR,
        TGZ
    }

    public static boolean validate(File file) {
        Type type = guessType(file);
        return validate(file, type);
    }

    public static boolean validate(File file, Type type) {
        return new FileValidator(file, type).checkValidity();
    }

    private static Type guessType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".txt"))
            return Type.TEXT;
        if (name.endsWith(".html") || name.endsWith(".htm"))
            return Type.HTML;
        if (name.endsWith(".json"))
            return Type.JSON;
        if (name.endsWith(".zip"))
            return Type.ZIP;
        if (name.endsWith(".tar.gz") || name.endsWith(".tgz"))
            return Type.TGZ;
        if (name.endsWith(".gz"))
            return Type.GZ;
        if (name.endsWith(".tar"))
            return Type.TAR;

        // special cases
        // java
        if (name.endsWith(".war") || name.endsWith(".ear"))
            return Type.ZIP;
        // python wheel
        if (name.endsWith(".whl"))
            return Type.ZIP;

        throw new UnsupportedOperationException("Unsupported file " + file);
    }


    private File file;
    private Type type;

    private FileValidator(File file, Type type) {
        this.file = file;
        this.type = type;
    }

    private boolean checkValidity() {
        if (!file.exists() || file.isDirectory())
            return false;
        if (file.length() == 0)
            return false;

        switch(type) {
            case TEXT:
                return checkText();
            case HTML:
                return checkHTML();
            case XML:
                return checkXML();
            case JSON:
                return checkJSON();
            case ZIP:
                return checkZIP();
            case GZ:
                return checkGZ();
            case TAR:
                return checkTAR();
            case TGZ:
                return checkTGZ();
            default:
                throw new UnsupportedOperationException("Unsupported file format " + type + " for file " + file);
        }
    }

    private boolean checkText() {
        return true;
    }


    private static EntityResolver SKIP_ENTITY_RESOLVER = new EntityResolver() {
        @Override
        public InputSource resolveEntity(String publicId, String systemId) {
            return new InputSource(new ByteArrayInputStream("".getBytes()));
        }
    };

    private static ErrorHandler SKIP_ERROR_HANDLER = new ErrorHandler() {
        @Override
        public void warning(SAXParseException exception) { }

        @Override
        public void error(SAXParseException exception) { }

        @Override
        public void fatalError(SAXParseException exception) { }
    };


    private boolean checkXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(SKIP_ENTITY_RESOLVER);
            builder.setErrorHandler(SKIP_ERROR_HANDLER);

            builder.parse(file);

            return true;
        } catch (ParserConfigurationException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (SAXException e) {
            return false;
        }
    }

    private boolean checkHTML() {
        try {
            Jsoup.parse(file);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private boolean checkJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(file, HashMap.class);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private boolean checkZIP() {
        try(FileInputStream fis = new FileInputStream(file)) {
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                ze = zis.getNextEntry();
            }
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private boolean checkGZ() {
        byte[] buffer = new byte[1024];
        try(FileInputStream fileIn = new FileInputStream(file)) {
            GZIPInputStream gzs = new GZIPInputStream(fileIn);
            while (gzs.read(buffer) > 0)
                ;
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private boolean checkTGZ() {
        return checkGZ();
    }

    private boolean checkTAR() {
        return true;
    }

}
