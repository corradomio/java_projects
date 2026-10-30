package jext.util.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Standalone tests for format detection, independent of conversion support. */
public class Log4JParserDetectionTest {
    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("log4j-detection-");
        Path input = directory.resolve("configuration.xml");
        try {
            for (String xml : new String[]{
                    "<configuration/>",
                    "<log4j:configuration/>",
                    "<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/'/>",
                    "<configuration xmlns='http://jakarta.apache.org/log4j/'/>",
                    "<!DOCTYPE log4j:configuration SYSTEM 'file:///missing-log4j.dtd'><log4j:configuration/>",
                    "<configuration><appender name='unsupported' class='custom.Appender'/><root/></configuration>"}) {
                detect(input, xml, true, false);
            }
            for (String xml : new String[]{
                    "<Configuration/>",
                    "<Configuration xmlns='https://logging.apache.org/xml/ns'/>",
                    "<Configuration xmlns='http://logging.apache.org/log4j/2.0/config'/>",
                    "<l:Configuration xmlns:l='https://logging.apache.org/xml/ns'/>",
                    "<Configuration strict='true'><Loggers><Node type='Root'/></Loggers></Configuration>",
                    "<Configuration><Appenders><Custom name='${missing}'/></Appenders></Configuration>"}) {
                detect(input, xml, false, true);
            }
            for (String xml : new String[]{
                    "", "not XML", "<configuration>", "<Configuration>",
                    "<Configuration/><Configuration/>", "<other/>",
                    "<configuration xmlns='urn:other'/>",
                    "<log4j:configuration xmlns:log4j='urn:other'/>",
                    "<Configuration xmlns='urn:other'/>",
                    "<!DOCTYPE configuration [<!ENTITY x SYSTEM 'file:///secret'>]><configuration/>",
                    "<!DOCTYPE Configuration SYSTEM 'file:///missing.dtd'><Configuration/>"}) {
                detect(input, xml, false, false);
            }
            Path missing = directory.resolve("missing.xml");
            try {
                Log4J1Parser.isLog4J1(missing.toFile());
                throw new AssertionError("v1 missing file must throw IOException");
            } catch (IOException expected) { }
            try {
                Log4J2Parser.isLog4J2(missing.toFile());
                throw new AssertionError("v2 missing file must throw IOException");
            } catch (IOException expected) { }
            try {
                Log4J1Parser.isLog4J1(null);
                throw new AssertionError("v1 null must throw NullPointerException");
            } catch (NullPointerException expected) { }
            try {
                Log4J2Parser.isLog4J2(null);
                throw new AssertionError("v2 null must throw NullPointerException");
            } catch (NullPointerException expected) { }
            System.out.println("Log4j format detection tests passed");
        } finally {
            Files.deleteIfExists(input);
            Files.delete(directory);
        }
    }

    private static void detect(Path input, String xml, boolean v1, boolean v2) throws IOException {
        Files.writeString(input, xml);
        if (Log4J1Parser.isLog4J1(input.toFile()) != v1) throw new AssertionError("v1 detection: " + xml);
        if (Log4J2Parser.isLog4J2(input.toFile()) != v2) throw new AssertionError("v2 detection: " + xml);
    }
}
