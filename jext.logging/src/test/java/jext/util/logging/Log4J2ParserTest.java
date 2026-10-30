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

/** Standalone tests; run in an isolated JVM because they reset JUL. */
public class Log4J2ParserTest {
    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("log4j2-parser-");
        Path input = directory.resolve("log4j2.xml");
        String key = "jext.log4j2.parser.test.directory";
        String previous = System.getProperty(key);
        System.setProperty(key, directory.toString());
        String xml = """
                <Configuration status="WARN">
                  <Properties>
                    <Property name="directory">${sys:jext.log4j2.parser.test.directory}</Property>
                    <Property name="logFile">${directory}/literal%g.log</Property>
                  </Properties>
                  <Appenders>
                    <Console name="console" target="SYSTEM_ERR"><PatternLayout pattern="%p %m%n"/></Console>
                    <File name="file" fileName="${logFile}" append="false">
                      <PatternLayout charset="UTF-8" pattern="%m%n"/>
                      <ThresholdFilter level="DEBUG" onMatch="NEUTRAL" onMismatch="DENY"/>
                    </File>
                  </Appenders>
                  <Loggers>
                    <Logger name="parser2.test" level="DEBUG" additivity="false">
                      <AppenderRef ref="file" level="INFO"/>
                    </Logger>
                    <Logger name="parser2.test.child"/>
                    <Root level="WARN"><AppenderRef ref="console"/></Root>
                  </Loggers>
                </Configuration>
                """;
        try {
            Properties properties = parse(input, xml);
            check("WARNING".equals(properties.getProperty(".level")), "root level");
            check("INFO".equals(properties.getProperty("java.util.logging.FileHandler.level")), "reference threshold");
            check("ALL".equals(properties.getProperty("java.util.logging.ConsoleHandler.level")), "console accepts child levels");
            check(!properties.containsKey("parser2.test.child.level"), "inherited level");
            LogManager manager = LogManager.getLogManager();
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                properties.store(bytes, null);
                manager.readConfiguration(new ByteArrayInputStream(bytes.toByteArray()));
                Logger parent = Logger.getLogger("parser2.test");
                Logger child = Logger.getLogger("parser2.test.child");
                check(!parent.getUseParentHandlers(), "additivity applied");
                check(parent.getLevel() == Level.FINE && child.isLoggable(Level.FINE), "inheritance applied");
                child.fine("filtered-marker");
                child.info("accepted-marker");
                parent.getHandlers()[0].flush();
                String content = Files.readString(directory.resolve("literal%g.log"));
                check(content.contains("accepted-marker") && !content.contains("filtered-marker"), "actual JUL output");
            } finally { manager.reset(); }

            String rolling = """
                    <Configuration xmlns="https://logging.apache.org/xml/ns">
                      <Appenders>
                        <RollingFile name="rolling" fileName="${sys:jext.log4j2.parser.test.directory}/rolling.log"
                                     filePattern="rolling-%i.log">
                          <Policies><SizeBasedTriggeringPolicy size="1.5 KB"/></Policies>
                          <DefaultRolloverStrategy min="2" max="4"/>
                        </RollingFile>
                      </Appenders>
                      <Loggers><Root level="ALL"><AppenderRef ref="rolling"/></Root></Loggers>
                    </Configuration>
                    """;
            properties = parse(input, rolling);
            check("1536".equals(properties.getProperty("java.util.logging.FileHandler.limit")), "fractional size");
            check("4".equals(properties.getProperty("java.util.logging.FileHandler.count")), "archive range plus active file");
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                properties.store(bytes, null);
                manager.readConfiguration(new ByteArrayInputStream(bytes.toByteArray()));
                Logger root = Logger.getLogger("");
                for (int i = 0; i < 50; i++) root.info("rotation-marker-" + i + "x".repeat(100));
                for (var handler : root.getHandlers()) handler.flush();
                check(Files.exists(directory.resolve("rolling.log.1")), "actual file rotation");
            } finally { manager.reset(); }

            properties = parse(input, "<Configuration/>");
            check("SEVERE".equals(properties.getProperty(".level")), "default ERROR root");
            check("java.util.logging.ConsoleHandler".equals(properties.getProperty("handlers")), "default console");
            properties = parse(input, "<Configuration><Loggers><Root/></Loggers></Configuration>");
            check("".equals(properties.getProperty("handlers")), "explicit empty root");
            for (String[] pair : new String[][]{{"ALL", "ALL"}, {"TRACE", "FINER"}, {"DEBUG", "FINE"},
                    {"INFO", "INFO"}, {"WARN", "WARNING"}, {"ERROR", "SEVERE"}, {"FATAL", "SEVERE"}, {"OFF", "OFF"}}) {
                properties = parse(input, "<Configuration><Loggers><Root level=\"" + pair[0] + "\"/></Loggers></Configuration>");
                check(pair[1].equals(properties.getProperty(".level")), "level " + pair[0]);
            }
            properties = parse(input, xml.replace("level=\"WARN\"", "level=\"${missing:-ERROR}\""));
            check("SEVERE".equals(properties.getProperty(".level")), "lookup default");
            parse(input, xml.replace("<Configuration status=\"WARN\">", "<Configuration xmlns=\"http://logging.apache.org/log4j/2.0/config\">"));
            reject(input, "<configuration/>");
            reject(input, "<Configuration>");
            reject(input, "<!DOCTYPE Configuration [<!ENTITY x SYSTEM 'file:///secret'>]><Configuration/>");
            reject(input, xml.replace("status=\"WARN\"", "strict=\"true\""));
            reject(input, xml.replace("ref=\"file\"", "ref=\"missing\""));
            reject(input, xml.replace("${logFile}", "${missing}"));
            reject(input, xml.replace("${directory}/literal%g.log", "${logFile}"));
            reject(input, xml.replace("${logFile}", "$${logFile}"));
            reject(input, xml.replace("level=\"WARN\"", "level=\"CUSTOM\""));
            reject(input, xml.replace("additivity=\"false\"", "additivity=\"invalid\""));
            reject(input, xml.replace("<Root level", "<AsyncRoot level").replace("</Root>", "</AsyncRoot>"));
            reject(input, xml.replace("<ThresholdFilter", "<RegexFilter"));
            reject(input, xml.replace("onMismatch=\"DENY\"", "onMismatch=\"ACCEPT\""));
            reject(input, xml.replace("<Logger name=\"parser2.test.child\"/>",
                    "<Logger name=\"parser2.test.child\"><AppenderRef ref=\"file\" level=\"ERROR\"/></Logger>"));
            reject(input, xml.replace("</Appenders>", "<Console name=\"other\"/></Appenders>")
                    .replace("<AppenderRef ref=\"console\"/>", "<AppenderRef ref=\"console\"/><AppenderRef ref=\"other\"/>"));
            reject(input, rolling.replace("rolling-%i.log", "rolling-%i.log.gz"));
            reject(input, rolling.replace("SizeBasedTriggeringPolicy size=\"1.5 KB\"", "TimeBasedTriggeringPolicy"));
            reject(input, rolling.replace("1.5 KB", "999999999999999999999 GB"));
            reject(input, rolling.replace("min=\"2\"", "min=\"5\""));
            try {
                Log4J2Parser.parser(directory.resolve("missing.xml").toFile());
                throw new AssertionError("Missing file accepted");
            } catch (IOException expected) { }
            System.out.println("Log4J2Parser tests passed");
        } finally {
            if (previous == null) System.clearProperty(key); else System.setProperty(key, previous);
            try (var paths = Files.walk(directory)) {
                for (Path path : paths.sorted(Comparator.reverseOrder()).toList()) Files.delete(path);
            }
        }
    }

    private static Properties parse(Path input, String xml) throws IOException {
        Files.writeString(input, xml);
        return Log4J2Parser.parser(input.toFile());
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
