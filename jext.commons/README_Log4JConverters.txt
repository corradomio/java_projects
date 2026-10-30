Log4j XML converters
================

Both converters are in jext.util.logging and require only the JDK (Java 17+).
LoggingConverter has been renamed to Log4J2Converted.

    import jext.util.logging.Log4J2Converted;
    import java.nio.file.Path;

    var warnings = Log4J2Converted.convert(
        Path.of("config/log4j2.xml"), Path.of("logging.properties"));
    warnings.forEach(System.err::println);

Two String or File paths are also accepted. The input and output must be different
files. The output directory must already exist. An existing destination is
replaced after conversion succeeds. Conversion does not configure live logging.

Load the generated file at application startup with:

    java -Djava.util.logging.config.file=logging.properties ...

Or use java.util.logging.LogManager.readConfiguration(InputStream).

Log4j 2 supported conversion:
  - Root and named Logger levels, inherited levels and additivity.
  - Console -> java.util.logging.ConsoleHandler.
  - File -> java.util.logging.FileHandler, with append and literal file paths.
  - RollingFile -> FileHandler size-based rotation (details below).
  - AppenderRef thresholds, local ${property} references, namespace-aware XML
    and strict XML type attributes.
  - FATAL/ERROR -> SEVERE, WARN -> WARNING, DEBUG -> FINE, TRACE -> FINEST;
    INFO, OFF and ALL retain their names. FATAL and ERROR become indistinguishable.

Limitations:
  - PatternLayout is replaced with SimpleFormatter, with a warning. Log4j
    pattern strings are not java.util.Formatter strings and are not copied.
  - SYSTEM_OUT becomes SYSTEM_ERR, with a warning, because ConsoleHandler
    writes to standard error. Explicit layout charset is retained.
  - Only one Console and one File or RollingFile appender are supported, and each appender
    may be referenced once. Standard JUL properties configure handlers by
    class name, rather than by independently configured appender names.
  - Time/startup/cron rolling policies, compressed archives, filters, async loggers/appenders, custom plugins,
    non-PatternLayout layouts and runtime/environment/system lookups are
    rejected with IOException. No unsupported routing is silently discarded.
  - Selected Configuration lifecycle/status attributes are ignored with warnings.
  - FileHandler can add a unique suffix if another handler locks the file;
    its parent directory must exist when the target application starts.
  - XML DTDs, external entities and includes are disabled.

Warnings are returned and included as comments in the generated properties.
Invalid or unsupported input leaves an existing output file untouched.

Log4j 1 conversion and format detection:

    if (Log4J1Converted.isLog4J1(input)) {
        var warnings = Log4J1Converted.convert(input, output);
        warnings.forEach(System.err::println);
    } else if (Log4J2Converted.isLog4J2(input)) {
        var warnings = Log4J2Converted.convert(input, output);
        warnings.forEach(System.err::println);
    }

Import jext.util.logging.Log4J1Converted as well as Log4J2Converted.
The isLog4J1/isLog4J2 methods accept Path, File or String. They identify a
well-formed XML document by its root element and namespace, not by filename.
They return false for malformed XML or another format; filesystem errors throw
IOException. Detection does not guarantee all configured plugins are supported.

Log4J1Converted supports ConsoleAppender, FileAppender and RollingFileAppender; logger/category and
level/priority aliases; additivity; INHERITED/NULL named logger levels; appender
Threshold, Encoding, File, Append and Target parameters. The default root is
DEBUG with no handlers, as for a fresh Log4j 1 repository. Conversion does not
reproduce updates to an existing repository. PatternLayout, SimpleLayout and
TTCCLayout are replaced with SimpleFormatter, with warnings. Flushing/buffering
settings are replaced by JUL behavior, with warnings. The same restrictions on
multiple/shared appenders apply. DailyRollingFileAppender, filters, custom classes,
property substitutions and non-ALL repository thresholds are rejected.

Legacy external log4j.dtd declarations are accepted without loading the DTD;
internal DTD subsets and entity declarations are rejected. Legacy unbound
log4j:configuration prefixes are accepted as by the old DOMConfigurator.

Size-based rolling files:

Log4j 1 accepts class="org.apache.log4j.RollingFileAppender" and the usual File,
Append, Threshold and Encoding parameters. MaxFileSize accepts positive integer
bytes or KB/MB/GB suffixes (case insensitive, powers of 1024). MaximumFileSize is
an alternative parameter for integer bytes; supplying both is rejected. Defaults
are 10 MB and MaxBackupIndex=1. JUL count is MaxBackupIndex + 1, including the
active file. MaxBackupIndex=0 truncates at the size limit without keeping backups.

Log4j 2 accepts the RollingFile plugin (implemented by
org.apache.logging.log4j.core.appender.RollingFileAppender), including strict XML
type="RollingFile" and the fully qualified class name as a type alias:

    <RollingFile name="file" fileName="app.log" filePattern="app.%i.log">
      <PatternLayout pattern="%m%n"/>
      <Policies><SizeBasedTriggeringPolicy size="10 MB"/></Policies>
      <DefaultRolloverStrategy max="5"/>
    </RollingFile>

SizeBasedTriggeringPolicy can appear directly or as the only policy in Policies.
Its default size is 10 MB; bytes, B, KB, MB, GB and TB are supported, including
decimal values that yield a positive whole number of bytes within the long range.
Zero size is rejected because JUL interprets zero as unlimited, not rollover.
DefaultRolloverStrategy is optional: min=1, max=7, fileIndex=max by default.
Both min and max index ordering modes are accepted; JUL always uses newest-first
backup ordering. JUL count is max - min + 2 (archive window plus active file).
Unbounded nomax, custom actions, compression and non-size policies are rejected.
Local property substitutions work in size, strategy values and file paths.

Naming is approximated, with explicit conversion warnings: JUL uses fileName.0
for the active file and fileName.1, fileName.2, ... for progressively older files.
With zero backups, the original fileName is used. Log4j 2 filePattern must contain
exactly one %i and no other conversions except literal %%; it is replaced by JUL
generation naming derived from fileName. Separate archive paths are not retained.
Compressed archive suffixes are rejected. Existing Log4j archives are not migrated.
Rollover timing and startup behavior may differ; append=false can rotate existing
JUL generations on startup. The output remains ordinary JUL properties with no
custom handler dependency. File and RollingFile share the one-FileHandler limit.

jext.util.logging.LogManager.configure now detects the XML version automatically
when generating the properties file. A newer XML file regenerates stale properties.

Standalone tests (from the java_projects directory):

    javac --release 17 -d jext.commons/target/logging-converter-test jext.commons/src/main/java/jext/util/logging/*.java jext.commons/src/test/java/jext/util/logging/*.java
    java -cp jext.commons/target/logging-converter-test jext.util.logging.Log4J2ConvertedTest
    java -cp jext.commons/target/logging-converter-test jext.util.logging.Log4J1ConvertedTest
    java -cp jext.commons/target/logging-converter-test jext.util.logging.RollingFileConvertedTest

The tests run JUL in an isolated JVM and verify actual file output, hierarchy,
thresholds, XML validation, property escaping and preservation of existing files.

References:
https://logging.apache.org/log4j/1.x/apidocs/org/apache/log4j/xml/DOMConfigurator.html
https://logging.apache.org/log4j/2.x/manual/configuration.html
https://docs.oracle.com/en/java/javase/21/core/java-logging-overview.html
https://docs.oracle.com/en/java/javase/21/docs/api/java.logging/java/util/logging/LogManager.html
https://docs.oracle.com/en/java/javase/21/docs/api/java.logging/java/util/logging/FileHandler.html
