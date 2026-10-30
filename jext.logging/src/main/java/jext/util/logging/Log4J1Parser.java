package jext.util.logging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts Log4j 1 XML configuration into standard java.util.logging properties.
 * No Log4j dependency is required, and parsing does not change active logging.
 *
 * <p>ConsoleAppender, FileAppender and RollingFileAppender are supported, along
 * with root/logger/category levels, appender thresholds, encoding and additivity.
 * TRACE maps to FINER, DEBUG to FINE, WARN to WARNING and ERROR/FATAL to SEVERE.
 * System properties in ${name} placeholders are resolved while parsing.
 *
 * <p>This is a best-effort conversion: JUL ConsoleHandler always writes to stderr
 * (including when Log4j targets stdout); Log4j layouts are replaced by JUL's
 * SimpleFormatter; rolling files use JUL's generation names and rotation rules.
 * JUL creates separate handler instances when a handler is attached to multiple
 * loggers, so a shared file appender may produce additional files with unique IDs.
 * Only one referenced appender per JUL handler class is supported because standard
 * JUL properties configure handlers by class, not by appender name.
 * Unsupported appenders, filters and other behavioral features are rejected.
 */
public final class Log4J1Parser {
    private static final String CONSOLE = "java.util.logging.ConsoleHandler";
    private static final String FILE = "java.util.logging.FileHandler";
    private static final Pattern VARIABLE = Pattern.compile("\\$\\{([^}]+)}");
    private static final Pattern SIZE = Pattern.compile("(\\d+)\\s*(KB|MB|GB)?", Pattern.CASE_INSENSITIVE);

    private Log4J1Parser() { }

    /**
     * Recognizes the Log4j 1 root element and namespace in well-formed XML.
     * This is format detection, not schema validation or a conversion check:
     * unsupported appenders and unresolved properties do not affect the result.
     * The legacy unbound log4j prefix and external DTD declarations are accepted;
     * external DTDs are never loaded.
     *
     * @param file XML file to inspect
     * @return false for another format, malformed XML or prohibited internal DTDs
     * @throws IOException if the file cannot be read or the XML parser cannot be configured
     * @throws NullPointerException if file is null
     */
    public static boolean isLog4J1(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        try {
            return isConfiguration(read(file));
        } catch (IOException e) {
            if (e.getCause() instanceof SAXException) return false;
            throw e;
        }
    }

    private static boolean isConfiguration(Element configuration) {
        if (!Set.of("configuration", "log4j:configuration").contains(configuration.getTagName())) return false;
        String namespace = configuration.getAttribute(configuration.getTagName().contains(":")
                ? "xmlns:log4j" : "xmlns");
        return namespace.isEmpty() || namespace.equals("http://jakarta.apache.org/log4j/");
    }

    /**
     * Returns properties suitable for {@link java.util.logging.LogManager#readConfiguration(java.io.InputStream)}
     * after serialization with {@link Properties#store(java.io.OutputStream, String)}.
     * A missing root level defaults to Log4j 1's DEBUG (JUL FINE).
     * External DTDs are ignored; internal DTD subsets and entities are rejected.
     *
     * @param file Log4j 1 XML configuration file
     * @return a new configuration, without modifying the file or global logging
     * @throws IOException if the file is unreadable, invalid or unsupported
     * @throws NullPointerException if file is null
     */
    public static Properties parser(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        Element configuration = read(file);
        if (!isConfiguration(configuration)) {
            throw new IOException("Expected a Log4j 1 configuration element");
        }
        String threshold = configuration.getAttribute("threshold");
        if (!threshold.isEmpty() && !value(threshold).equalsIgnoreCase("ALL")
                && !value(threshold).equalsIgnoreCase("NULL")) {
            throw new IOException("Repository-wide threshold cannot be represented by standard JUL properties");
        }
        Map<String, Element> appenders = new LinkedHashMap<>();
        Map<String, Element> loggers = new LinkedHashMap<>();
        for (Element child : children(configuration)) {
            switch (child.getTagName()) {
                case "appender" -> {
                    String name = required(child, "name");
                    if (appenders.putIfAbsent(name, child) != null) throw new IOException("Duplicate appender: " + name);
                }
                case "root", "logger", "category" -> {
                    String name = child.getTagName().equals("root") ? "" : required(child, "name");
                    if (loggers.putIfAbsent(name, child) != null) throw new IOException("Duplicate logger: " + name);
                }
                default -> throw unsupported(child);
            }
        }

        Properties result = new Properties();
        result.setProperty("handlers", "");
        result.setProperty(".level", "FINE");
        Map<String, String> handlerOwners = new LinkedHashMap<>();
        Map<String, String> converted = new LinkedHashMap<>();
        for (Map.Entry<String, Element> entry : loggers.entrySet()) {
            String name = entry.getKey();
            Element logger = entry.getValue();
            if (logger.hasAttribute("class")) throw new IOException("Custom logger classes are unsupported");
            if (!name.isEmpty()) {
                result.setProperty(name + ".useParentHandlers", bool(logger.getAttribute("additivity"), true));
            }
            Set<String> handlers = new LinkedHashSet<>();
            boolean hasLevel = false;
            for (Element child : children(logger)) {
                switch (child.getTagName()) {
                    case "level", "priority" -> {
                        if (hasLevel || child.hasAttribute("class")) throw new IOException("Duplicate or custom logger level");
                        hasLevel = true;
                        String level = required(child, "value");
                        if (level.equalsIgnoreCase("INHERITED") || level.equalsIgnoreCase("NULL")) {
                            if (name.isEmpty()) throw new IOException("Root logger cannot inherit its level");
                        } else result.setProperty(name + ".level", level(level));
                    }
                    case "appender-ref" -> {
                        String ref = required(child, "ref");
                        if (!converted.containsKey(ref)) {
                            Element appender = appenders.get(ref);
                            if (appender == null) throw new IOException("Unknown appender: " + ref);
                            String handler = appender(appender, result);
                            String previous = handlerOwners.putIfAbsent(handler, ref);
                            if (previous != null) throw new IOException("Appenders " + previous + " and " + ref
                                    + " require different instances of " + handler + "; standard JUL properties cannot represent this");
                            converted.put(ref, handler);
                        }
                        handlers.add(converted.get(ref));
                    }
                    default -> throw unsupported(child);
                }
            }
            result.setProperty(name.isEmpty() ? "handlers" : name + ".handlers", String.join(", ", handlers));
        }
        return result;
    }

    private static Element read(File file) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Namespace-unaware parsing also accepts historical unbound log4j prefixes.
            factory.setNamespaceAware(false);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            var builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new DefaultHandler() {
                @Override public void error(org.xml.sax.SAXParseException e) throws SAXException { throw e; }
                @Override public void fatalError(org.xml.sax.SAXParseException e) throws SAXException { throw e; }
            });
            Document document = builder.parse(file);
            if (document.getDoctype() != null && document.getDoctype().getInternalSubset() != null) {
                throw new SAXException("Internal DTD subsets are unsupported");
            }
            return document.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IllegalArgumentException e) {
            throw new IOException("Cannot parse Log4j 1 XML: " + file, e);
        }
    }

    private static String appender(Element appender, Properties output) throws IOException {
        String type = required(appender, "class");
        boolean rolling = type.equals("org.apache.log4j.RollingFileAppender");
        String handler;
        if (type.equals("org.apache.log4j.ConsoleAppender")) handler = CONSOLE;
        else if (type.equals("org.apache.log4j.FileAppender") || rolling) handler = FILE;
        else throw new IOException("Unsupported appender class: " + type);

        Map<String, String> params = new LinkedHashMap<>();
        boolean hasLayout = false;
        for (Element child : children(appender)) {
            if (child.getTagName().equals("param")) {
                String key = required(child, "name").toLowerCase(Locale.ROOT);
                if (params.putIfAbsent(key, required(child, "value")) != null) throw new IOException("Duplicate parameter: " + key);
            } else if (child.getTagName().equals("layout") && !hasLayout) {
                hasLayout = true;
                String layout = required(child, "class");
                if (!Set.of("org.apache.log4j.PatternLayout", "org.apache.log4j.SimpleLayout",
                        "org.apache.log4j.TTCCLayout").contains(layout)) {
                    throw new IOException("Unsupported layout: " + layout);
                }
                for (Element parameter : children(child)) {
                    if (!parameter.getTagName().equals("param")) throw unsupported(parameter);
                }
            } else throw unsupported(child);
        }
        Set<String> allowed = new LinkedHashSet<>(Set.of("threshold", "encoding", "immediateflush"));
        if (handler.equals(CONSOLE)) allowed.addAll(Set.of("target", "follow"));
        else allowed.addAll(Set.of("file", "append", "bufferedio", "buffersize"));
        if (rolling) allowed.addAll(Set.of("maxfilesize", "maxbackupindex"));
        for (String key : params.keySet()) {
            if (!allowed.contains(key)) throw new IOException("Unsupported appender parameter: " + key);
        }
        if (!bool(params.get("immediateflush"), true).equals("true")
                || bool(params.get("bufferedio"), false).equals("true")
                || bool(params.get("follow"), false).equals("true")) {
            throw new IOException("Buffered output, disabled immediate flush and console follow are unsupported");
        }
        output.setProperty(handler + ".level", level(params.getOrDefault("threshold", "ALL")));
        output.setProperty(handler + ".formatter", "java.util.logging.SimpleFormatter");
        if (params.containsKey("encoding")) {
            try { java.nio.charset.Charset.forName(params.get("encoding")); }
            catch (IllegalArgumentException e) { throw new IOException("Unsupported encoding", e); }
            output.setProperty(handler + ".encoding", params.get("encoding"));
        }
        if (handler.equals(CONSOLE)) {
            String target = params.getOrDefault("target", "System.out");
            if (!target.equalsIgnoreCase("System.out") && !target.equalsIgnoreCase("System.err")) {
                throw new IOException("Invalid console target: " + target);
            }
        } else {
            String path = params.get("file");
            if (path == null || path.isBlank()) throw new IOException("File appender requires File parameter");
            output.setProperty(handler + ".pattern", path.replace("%", "%%"));
            output.setProperty(handler + ".append", bool(params.get("append"), true));
            long limit = rolling ? size(params.getOrDefault("maxfilesize", "10MB")) : 0;
            int count = 1;
            if (rolling) {
                try {
                    int backups = Integer.parseInt(params.getOrDefault("maxbackupindex", "1"));
                    if (backups < 1 || backups == Integer.MAX_VALUE) throw new NumberFormatException();
                    count = backups + 1;
                } catch (NumberFormatException e) {
                    throw new IOException("MaxBackupIndex must be between 1 and 2147483646 for JUL rotation", e);
                }
            }
            output.setProperty(handler + ".limit", Long.toString(limit));
            output.setProperty(handler + ".count", Integer.toString(count));
        }
        return handler;
    }

    private static String level(String value) throws IOException {
        return switch (value.toUpperCase(Locale.ROOT)) {
            case "ALL" -> "ALL";
            case "TRACE" -> "FINER";
            case "DEBUG" -> "FINE";
            case "INFO" -> "INFO";
            case "WARN" -> "WARNING";
            case "ERROR", "FATAL" -> "SEVERE";
            case "OFF" -> "OFF";
            default -> throw new IOException("Unsupported Log4j level: " + value);
        };
    }

    private static long size(String value) throws IOException {
        Matcher matcher = SIZE.matcher(value.trim());
        if (!matcher.matches()) throw new IOException("Invalid MaxFileSize: " + value);
        try {
            long factor = switch (matcher.group(2) == null ? "" : matcher.group(2).toUpperCase(Locale.ROOT)) {
                case "KB" -> 1024L;
                case "MB" -> 1024L * 1024;
                case "GB" -> 1024L * 1024 * 1024;
                default -> 1L;
            };
            long bytes = Math.multiplyExact(Long.parseLong(matcher.group(1)), factor);
            if (bytes <= 0) throw new ArithmeticException();
            return bytes;
        } catch (ArithmeticException | NumberFormatException e) {
            throw new IOException("MaxFileSize must be a positive size within the long range: " + value, e);
        }
    }

    private static String bool(String value, boolean fallback) throws IOException {
        if (value == null || value.isEmpty()) return Boolean.toString(fallback);
        value = value(value);
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            throw new IOException("Invalid boolean: " + value);
        }
        return value.toLowerCase(Locale.ROOT);
    }

    private static String required(Element element, String attribute) throws IOException {
        String text = value(element.getAttribute(attribute));
        if (text.isEmpty()) throw new IOException("Missing " + attribute + " on " + element.getTagName());
        return text;
    }

    private static String value(String text) throws IOException {
        Matcher matcher = VARIABLE.matcher(text);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String replacement = System.getProperty(matcher.group(1));
            if (replacement == null) throw new IOException("Unresolved system property: " + matcher.group(1));
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static List<Element> children(Element parent) throws IOException {
        List<Element> result = new ArrayList<>();
        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node instanceof Element element) result.add(element);
            else if (node.getNodeType() == Node.ENTITY_REFERENCE_NODE) throw new IOException("XML entities are unsupported");
        }
        return result;
    }

    private static IOException unsupported(Element element) {
        return new IOException("Unsupported Log4j element: " + element.getTagName());
    }
}
