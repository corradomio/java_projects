package jext.util.logging;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts ordinary (non-strict) Log4j 2 XML to java.util.logging properties,
 * without a Log4j dependency or changes to active logging.
 *
 * <p>Supports Console, File and size-based RollingFile appenders, logger levels,
 * additivity, AppenderRef levels and appender ThresholdFilter elements. Local
 * Properties, ${sys:name}, ${env:name} and ${name:-default} lookups are resolved
 * at parse time. Missing logger levels inherit; the default root level is ERROR.
 *
 * <p>Conversion is approximate: ConsoleHandler always writes to stderr, layouts
 * become SimpleFormatter (PatternLayout charset is preserved), and rolling files
 * use JUL generation names rather than filePattern. Buffering is managed by JUL.
 * File compression, time-based rotation, custom filters, async components and
 * strict XML are unsupported. Only one referenced appender per JUL handler class
 * is possible, and references to that appender must have the same effective level.
 * JUL creates separate handlers for different loggers; sharing a file appender
 * across loggers can therefore produce additional files with unique IDs.
 */
public final class Log4J2Parser {
    private static final String CONSOLE = "java.util.logging.ConsoleHandler";
    private static final String FILE = "java.util.logging.FileHandler";
    private static final Pattern VARIABLE = Pattern.compile("\\$\\{([^{}]+)}");
    private static final Pattern SIZE = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*([KMGT]?B)?", Pattern.CASE_INSENSITIVE);
    private final Map<String, String> variables = new LinkedHashMap<>();
    private final Map<String, Element> appenders = new LinkedHashMap<>();
    private final Map<String, String> owners = new LinkedHashMap<>();
    private final Map<String, Handler> converted = new LinkedHashMap<>();
    private final Map<String, String> referenceLevels = new LinkedHashMap<>();
    private final Properties output = new Properties();

    private record Handler(String name, String level) { }

    private Log4J2Parser() { }

    /**
     * Recognizes the Log4j 2 Configuration root and namespace in well-formed XML.
     * This is format detection, not schema validation or a conversion check:
     * strict XML, unsupported plugins and unresolved properties can be recognized.
     * The same secure XML loading rules as {@link #parser(File)} apply.
     *
     * @param file XML file to inspect
     * @return false for another format, malformed XML or prohibited DTDs
     * @throws IOException if the file cannot be read or the XML parser cannot be configured
     * @throws NullPointerException if file is null
     */
    public static boolean isLog4J2(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        Element root;
        try {
            root = read(file);
        } catch (IOException e) {
            if (e.getCause() instanceof SAXException) return false;
            throw e;
        }
        try {
            return tag(root).equals("Configuration");
        } catch (IOException e) {
            // A foreign namespace is a different format, not a file access error.
            return false;
        }
    }

    /**
     * Returns a new configuration suitable for LogManager.readConfiguration after
     * serialization with Properties.store(OutputStream, String).
     * XML DTDs and external entities are prohibited. Relative file names retain
     * their meaning relative to the application's working directory.
     *
     * @param file the Log4j 2 XML configuration
     * @return standard JUL properties
     * @throws IOException for unreadable, invalid or unsupported configuration
     * @throws NullPointerException if file is null
     */
    public static Properties parser(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        return new Log4J2Parser().convert(read(file));
    }

    private Properties convert(Element root) throws IOException {
        if (!tag(root).equals("Configuration")) throw new IOException("Expected Log4j 2 Configuration");
        attributes(root, "name", "status", "verbose", "monitorInterval", "shutdownHook",
                "shutdownTimeout", "strict", "schema", "packages", "advertiser");
        if (bool(root, "strict", false)) throw new IOException("Strict Log4j 2 XML is unsupported");
        if (root.hasAttribute("advertiser")) throw new IOException("Configuration advertisers are unsupported");
        Map<String, Element> sections = new LinkedHashMap<>();
        for (Element section : children(root)) {
            String name = tag(section);
            if (!Set.of("Properties", "Appenders", "Loggers").contains(name)) throw unsupported(section);
            if (sections.putIfAbsent(name, section) != null) throw new IOException("Duplicate section: " + name);
            attributes(section);
        }
        if (sections.containsKey("Properties")) {
            for (Element property : children(sections.get("Properties"))) {
                if (!tag(property).equals("Property")) throw unsupported(property);
                attributes(property, "name", "value");
                leaf(property);
                String name = property.getAttribute("name");
                if (name.isBlank()) throw new IOException("Property name is required");
                String value = property.hasAttribute("value") ? property.getAttribute("value") : property.getTextContent().trim();
                if (variables.putIfAbsent(name, value) != null) throw new IOException("Duplicate property: " + name);
            }
        }
        if (sections.containsKey("Appenders")) {
            for (Element appender : children(sections.get("Appenders"))) {
                String name = required(appender, "name");
                if (appenders.putIfAbsent(name, appender) != null) throw new IOException("Duplicate appender: " + name);
            }
        }
        output.setProperty("handlers", "");
        output.setProperty(".level", "SEVERE");
        Set<String> names = new LinkedHashSet<>();
        if (sections.containsKey("Loggers")) {
            for (Element logger : children(sections.get("Loggers"))) {
                String type = tag(logger);
                if (!type.equals("Root") && !type.equals("Logger")) throw unsupported(logger);
                attributes(logger, "name", "level", "additivity", "includeLocation");
                String name = type.equals("Root") ? "" : required(logger, "name");
                if (!names.add(name)) throw new IOException("Duplicate logger: " + name);
                if (logger.hasAttribute("level")) output.setProperty(name + ".level", level(required(logger, "level")));
                if (!name.isEmpty()) output.setProperty(name + ".useParentHandlers", Boolean.toString(bool(logger, "additivity", true)));
                Set<String> handlers = new LinkedHashSet<>();
                for (Element ref : children(logger)) {
                    if (!tag(ref).equals("AppenderRef")) throw unsupported(ref);
                    attributes(ref, "ref", "level");
                    leaf(ref);
                    String appenderName = required(ref, "ref");
                    Handler handler = converted.get(appenderName);
                    if (handler == null) {
                        Element appender = appenders.get(appenderName);
                        if (appender == null) throw new IOException("Unknown appender: " + appenderName);
                        handler = appender(appender);
                        claim(handler.name(), appenderName);
                        converted.put(appenderName, handler);
                    }
                    String effective = stricter(handler.level(), level(attr(ref, "level", "ALL")));
                    String previous = referenceLevels.putIfAbsent(appenderName, effective);
                    if (previous != null && !previous.equals(effective)) {
                        throw new IOException("Different reference levels for appender " + appenderName + " cannot be expressed in JUL properties");
                    }
                    output.setProperty(handler.name() + ".level", effective);
                    handlers.add(handler.name());
                }
                output.setProperty(name.isEmpty() ? "handlers" : name + ".handlers", String.join(", ", handlers));
            }
        }
        // Log4j installs a default ERROR console root when no Root is configured.
        if (!names.contains("")) {
            claim(CONSOLE, "<default root console>");
            output.setProperty("handlers", CONSOLE);
            output.setProperty(CONSOLE + ".level", "ALL");
            output.setProperty(CONSOLE + ".formatter", "java.util.logging.SimpleFormatter");
        }
        return output;
    }

    private Handler appender(Element element) throws IOException {
        String type = tag(element);
        boolean rolling = type.equals("RollingFile");
        if (!Set.of("Console", "File", "RollingFile").contains(type)) throw unsupported(element);
        Set<String> allowed = new LinkedHashSet<>(Set.of("name", "ignoreExceptions", "immediateFlush", "bufferSize", "bufferedIo"));
        if (type.equals("Console")) allowed.addAll(Set.of("target", "follow", "direct"));
        else allowed.addAll(Set.of("fileName", "append", "locking", "createOnDemand"));
        if (rolling) allowed.add("filePattern");
        attributes(element, allowed.toArray(String[]::new));
        if (!bool(element, "ignoreExceptions", true) || !bool(element, "immediateFlush", true)
                || bool(element, "follow", false) || bool(element, "direct", false) || bool(element, "locking", false)) {
            throw new IOException("Unsupported output or error-handling options on " + required(element, "name"));
        }
        String handler = type.equals("Console") ? CONSOLE : FILE;
        String threshold = "ALL";
        String charset = "UTF-8";
        Element sizePolicy = null;
        Element strategy = null;
        Set<String> seen = new LinkedHashSet<>();
        for (Element child : children(element)) {
            String name = tag(child);
            if (!seen.add(name)) throw new IOException("Duplicate appender element: " + name);
            switch (name) {
                case "PatternLayout" -> {
                    attributes(child, "pattern", "charset", "alwaysWriteExceptions", "disableAnsi", "noConsoleNoAnsi", "header", "footer");
                    leaf(child);
                    charset = attr(child, "charset", "UTF-8");
                }
                case "ThresholdFilter" -> {
                    attributes(child, "level", "onMatch", "onMismatch");
                    leaf(child);
                    if (!Set.of("ACCEPT", "NEUTRAL").contains(attr(child, "onMatch", "NEUTRAL").toUpperCase(Locale.ROOT))
                            || !attr(child, "onMismatch", "DENY").equalsIgnoreCase("DENY")) {
                        throw new IOException("Unsupported ThresholdFilter actions");
                    }
                    threshold = level(attr(child, "level", "ERROR"));
                }
                case "SizeBasedTriggeringPolicy", "Policies" -> {
                    if (!rolling || sizePolicy != null) throw unsupported(child);
                    if (name.equals("Policies")) {
                        attributes(child);
                        List<Element> policies = children(child);
                        if (policies.size() != 1 || !tag(policies.get(0)).equals("SizeBasedTriggeringPolicy")) {
                            throw new IOException("Only a single SizeBasedTriggeringPolicy is supported");
                        }
                        sizePolicy = policies.get(0);
                    } else sizePolicy = child;
                    attributes(sizePolicy, "size");
                    leaf(sizePolicy);
                }
                case "DefaultRolloverStrategy" -> {
                    if (!rolling) throw unsupported(child);
                    strategy = child;
                    attributes(strategy, "min", "max", "fileIndex");
                    leaf(strategy);
                }
                default -> throw unsupported(child);
            }
        }
        try { Charset.forName(charset); }
        catch (IllegalArgumentException e) { throw new IOException("Invalid charset: " + charset, e); }
        output.setProperty(handler + ".encoding", charset);
        output.setProperty(handler + ".formatter", "java.util.logging.SimpleFormatter");
        if (handler.equals(CONSOLE)) {
            if (!Set.of("SYSTEM_OUT", "SYSTEM_ERR").contains(attr(element, "target", "SYSTEM_OUT").toUpperCase(Locale.ROOT))) {
                throw new IOException("Invalid console target");
            }
        } else {
            output.setProperty(handler + ".pattern", required(element, "fileName").replace("%", "%%"));
            output.setProperty(handler + ".append", Boolean.toString(bool(element, "append", true)));
            long limit = 0;
            int count = 1;
            if (rolling) {
                String pattern = required(element, "filePattern");
                if (!pattern.contains("%i") || pattern.contains("%d")
                        || pattern.toLowerCase(Locale.ROOT).matches(".*\\.(gz|zip|bz2|xz|z|deflate|pack200)$")) {
                    throw new IOException("Only uncompressed, index-based rolling file patterns are supported");
                }
                if (sizePolicy == null) throw new IOException("RollingFile requires SizeBasedTriggeringPolicy");
                limit = size(attr(sizePolicy, "size", "10MB"));
                int min = strategy == null ? 1 : positiveInt(attr(strategy, "min", "1"));
                int max = strategy == null ? 7 : positiveInt(attr(strategy, "max", "7"));
                if (max < min || (long) max - min + 2 > Integer.MAX_VALUE) throw new IOException("Invalid rollover index range");
                if (strategy != null && !Set.of("min", "max").contains(attr(strategy, "fileIndex", "max").toLowerCase(Locale.ROOT))) {
                    throw new IOException("Unbounded rollover is unsupported");
                }
                count = max - min + 2; // Archive slots plus the active file.
            }
            output.setProperty(handler + ".limit", Long.toString(limit));
            output.setProperty(handler + ".count", Integer.toString(count));
        }
        return new Handler(handler, threshold);
    }

    private void claim(String handler, String name) throws IOException {
        String previous = owners.putIfAbsent(handler, name);
        if (previous != null && !previous.equals(name)) {
            throw new IOException("Appenders " + previous + " and " + name + " both require " + handler
                    + "; standard JUL properties support only one configuration per handler class");
        }
    }

    private String required(Element element, String name) throws IOException {
        String result = attr(element, name, "");
        if (result.isBlank()) throw new IOException("Missing " + name + " on " + tag(element));
        return result;
    }

    private String attr(Element element, String name, String fallback) throws IOException {
        return element.hasAttribute(name) ? resolve(element.getAttribute(name), new LinkedHashSet<>()) : fallback;
    }

    private String resolve(String text, Set<String> visiting) throws IOException {
        if (text.contains("$${")) throw new IOException("Runtime lookups are unsupported");
        Matcher matcher = VARIABLE.matcher(text);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String expression = matcher.group(1);
            int separator = expression.indexOf(":-");
            String key = separator < 0 ? expression : expression.substring(0, separator);
            if (visiting.size() >= 32 || !visiting.add(key)) throw new IOException("Cyclic or excessively nested property: " + key);
            String replacement;
            if (key.startsWith("sys:")) replacement = System.getProperty(key.substring(4));
            else if (key.startsWith("env:")) replacement = System.getenv(key.substring(4));
            else replacement = variables.get(key);
            if (replacement == null && separator >= 0) replacement = expression.substring(separator + 2);
            if (replacement == null) throw new IOException("Unresolved property: " + key);
            replacement = resolve(replacement, visiting);
            visiting.remove(key);
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        if (result.indexOf("${") >= 0) throw new IOException("Unsupported nested lookup expression: " + text);
        return result.toString();
    }

    private boolean bool(Element element, String name, boolean fallback) throws IOException {
        String text = attr(element, name, Boolean.toString(fallback));
        if (!text.equalsIgnoreCase("true") && !text.equalsIgnoreCase("false")) throw new IOException("Invalid boolean " + name + ": " + text);
        return Boolean.parseBoolean(text);
    }

    private static String level(String text) throws IOException {
        return switch (text.toUpperCase(Locale.ROOT)) {
            case "ALL" -> "ALL";
            case "TRACE" -> "FINER";
            case "DEBUG" -> "FINE";
            case "INFO" -> "INFO";
            case "WARN" -> "WARNING";
            case "ERROR", "FATAL" -> "SEVERE";
            case "OFF" -> "OFF";
            default -> throw new IOException("Unsupported Log4j 2 level: " + text);
        };
    }

    private static String stricter(String first, String second) {
        return Level.parse(first).intValue() >= Level.parse(second).intValue() ? first : second;
    }

    private static long size(String text) throws IOException {
        Matcher matcher = SIZE.matcher(text.trim());
        if (!matcher.matches()) throw new IOException("Invalid rolling size: " + text);
        int exponent = matcher.group(2) == null ? 0 : switch (matcher.group(2).toUpperCase(Locale.ROOT)) {
            case "KB" -> 1;
            case "MB" -> 2;
            case "GB" -> 3;
            case "TB" -> 4;
            default -> 0;
        };
        try {
            long bytes = new BigDecimal(matcher.group(1)).multiply(BigDecimal.valueOf(1024).pow(exponent)).longValueExact();
            if (bytes <= 0) throw new ArithmeticException();
            return bytes;
        } catch (ArithmeticException e) { throw new IOException("Invalid rolling size: " + text, e); }
    }

    private static int positiveInt(String text) throws IOException {
        try {
            int result = Integer.parseInt(text);
            if (result < 1) throw new NumberFormatException();
            return result;
        } catch (NumberFormatException e) { throw new IOException("Expected a positive integer: " + text, e); }
    }

    private static void attributes(Element element, String... allowed) throws IOException {
        Set<String> names = Set.of(allowed);
        var attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attribute.getNamespaceURI())
                    || (XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI.equals(attribute.getNamespaceURI())
                        && Set.of("schemaLocation", "noNamespaceSchemaLocation").contains(attribute.getLocalName()))) continue;
            if (!names.contains(attribute.getNodeName())) throw new IOException("Unsupported attribute " + attribute.getNodeName() + " on " + tag(element));
        }
    }

    private static String tag(Element element) throws IOException {
        String namespace = element.getNamespaceURI();
        if (namespace != null && !Set.of("https://logging.apache.org/xml/ns", "http://logging.apache.org/log4j/2.0/config").contains(namespace)) {
            throw new IOException("Unsupported XML namespace: " + namespace);
        }
        return element.getLocalName();
    }

    private static List<Element> children(Element parent) throws IOException {
        List<Element> result = new ArrayList<>();
        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node instanceof Element element) result.add(element);
            else if (node.getNodeType() == Node.ENTITY_REFERENCE_NODE) throw new IOException("XML entities are unsupported");
        }
        return result;
    }

    private static void leaf(Element element) throws IOException {
        if (!children(element).isEmpty()) throw new IOException("Unsupported nested elements in " + tag(element));
    }

    private static IOException unsupported(Element element) {
        return new IOException("Unsupported Log4j 2 element: " + element.getTagName());
    }

    private static Element read(File file) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            var builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new DefaultHandler() {
                @Override public void error(SAXParseException e) throws SAXException { throw e; }
                @Override public void fatalError(SAXParseException e) throws SAXException { throw e; }
            });
            return builder.parse(file).getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IllegalArgumentException e) {
            throw new IOException("Cannot parse Log4j 2 XML: " + file, e);
        }
    }
}
