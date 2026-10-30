package jext.util.logging;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/** Standalone integration tests; run in an isolated JVM because they reset JUL. */
public class Log4J1ParserTest {
    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("log4j1-parser-");
        Path input = directory.resolve("log4j.xml");
        String property = "jext.log4j1.parser.test.path";
        String old = System.getProperty(property);
        try {
            Path log = directory.resolve("literal%g.log");
            System.setProperty(property, log.toString());
            String xml = """
                    <!DOCTYPE log4j:configuration SYSTEM "file:///missing/log4j.dtd">
                    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
                      <appender name="console" class="org.apache.log4j.ConsoleAppender">
                        <param name="Target" value="System.err"/>
                        <layout class="org.apache.log4j.PatternLayout">
                          <param name="ConversionPattern" value="%p %m%n"/>
                        </layout>
                      </appender>
                      <appender name="file" class="org.apache.log4j.FileAppender">
                        <param name="File" value="${jext.log4j1.parser.test.path}"/>
                        <param name="Append" value="false"/>
                        <param name="Threshold" value="INFO"/>
                        <param name="Encoding" value="UTF-8"/>
                      </appender>
                      <category name="parser.test" additivity="false">
                        <priority value="DEBUG"/><appender-ref ref="file"/>
                      </category>
                      <logger name="parser.test.child"><level value="INHERITED"/></logger>
                      <root><level value="WARN"/><appender-ref ref="console"/></root>
                    </log4j:configuration>
                    """;
            Properties properties = parse(input, xml);
            check("WARNING".equals(properties.getProperty(".level")), "root mapping");
            check("FINE".equals(properties.getProperty("parser.test.level")), "category priority");
            check(!properties.containsKey("parser.test.child.level"), "inherited level omitted");
            check("ALL".equals(properties.getProperty("java.util.logging.ConsoleHandler.level")), "handler allows debug");
            LogManager manager = LogManager.getLogManager();
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                properties.store(bytes, null);
                manager.readConfiguration(new ByteArrayInputStream(bytes.toByteArray()));
                Logger parent = Logger.getLogger("parser.test");
                Logger child = Logger.getLogger("parser.test.child");
                check(!parent.getUseParentHandlers(), "additivity applied");
                check(parent.getLevel() == Level.FINE && child.isLoggable(Level.FINE), "inheritance applied");
                child.fine("filtered-marker");
                child.info("accepted-marker");
                parent.getHandlers()[0].flush();
                String content = Files.readString(log);
                check(content.contains("accepted-marker") && !content.contains("filtered-marker"), "JUL output and threshold");
            } finally { manager.reset(); }
            properties = parse(input, xml.replace("FileAppender", "RollingFileAppender")
                    .replace("<param name=\"Append\"", "<param name=\"MaxFileSize\" value=\"2MB\"/>"
                            + "<param name=\"MaxBackupIndex\" value=\"3\"/><param name=\"Append\""));
            check("2097152".equals(properties.getProperty("java.util.logging.FileHandler.limit")), "rolling size");
            check("4".equals(properties.getProperty("java.util.logging.FileHandler.count")), "backup count");
            parse(input, xml.replace(" xmlns:log4j=\"http://jakarta.apache.org/log4j/\"", ""));
            properties = parse(input, "<configuration><root/></configuration>");
            check("FINE".equals(properties.getProperty(".level")), "default root level");
            check("".equals(properties.getProperty("handlers")), "no implicit handler");
            for (String[] pair : new String[][]{{"ALL", "ALL"}, {"TRACE", "FINER"}, {"DEBUG", "FINE"},
                    {"INFO", "INFO"}, {"WARN", "WARNING"}, {"ERROR", "SEVERE"}, {"FATAL", "SEVERE"}, {"OFF", "OFF"}}) {
                properties = parse(input, "<configuration><root><level value=\"" + pair[0] + "\"/></root></configuration>");
                check(pair[1].equals(properties.getProperty(".level")), "level " + pair[0]);
            }
            reject(input, "<Configuration/>");
            reject(input, "<configuration>");
            reject(input, "<!DOCTYPE configuration [<!ENTITY x SYSTEM 'file:///secret'>]><configuration/>");
            reject(input, xml.replace("ref=\"file\"", "ref=\"unknown\""));
            reject(input, xml.replace("FileAppender", "DailyRollingFileAppender"));
            reject(input, xml.replace("value=\"DEBUG\"", "value=\"CUSTOM\""));
            reject(input, xml.replace("additivity=\"false\"", "additivity=\"invalid\""));
            reject(input, xml.replace("<root>", "<root><filter/>"));
            reject(input, xml.replace("name=\"file\"", "name=\"console\""));
            reject(input, xml.replace("<root>", "<appender name=\"second\" class=\"org.apache.log4j.ConsoleAppender\"/>"
                    + "<root><appender-ref ref=\"second\"/>"));
            reject(input, xml.replace("${jext.log4j1.parser.test.path}", "${jext.log4j1.parser.test.missing}"));
            try {
                Log4J1Parser.parser(directory.resolve("missing.xml").toFile());
                throw new AssertionError("Missing file accepted");
            } catch (IOException expected) { }
            System.out.println("Log4J1Parser tests passed");
        } finally {
            if (old == null) System.clearProperty(property); else System.setProperty(property, old);
            try (var paths = Files.walk(directory)) {
                for (Path path : paths.sorted(Comparator.reverseOrder()).toList()) Files.delete(path);
            }
        }
    }

    private static Properties parse(Path input, String xml) throws IOException {
        Files.writeString(input, xml);
        return Log4J1Parser.parser(input.toFile());
    }

    private static void reject(Path input, String xml) throws IOException {
        try {
            parse(input, xml);
            throw new AssertionError("Unsupported XML accepted: " + xml);
        } catch (IOException expected) { }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
