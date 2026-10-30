Conviene passare a Java Logging ed abbandonare (a malicuore) Log4J.
L'alternativa e' usare SLF4J (Simple Logging Facade for Java) che puo' essere configurato
per Java Logging oppure Log4J.

COMUNQUE, rimane il problema di quelle implementazioni che sano Log4J DIRETTAMENTE.

In questo caso, jext.commons implementa

    org.apache.log4j.Logger

che non e' altro che un wrapper a Java Logging Logger.

---

The parte mancante e' la possibilita' di usare un file di configurazione Log4J (properties o XML)
con Java Logging.

LogManagerExt: direct XML configuration
--------------------------------------
Install the custom JUL manager before logging is initialized:

    java -Djava.util.logging.manager=jext.util.logging.LogManagerExt \
         -Djava.util.logging.config.file=log4j2.xml ...

The same options accept Log4j 1 XML and normal JUL .properties files.
The manager detects the format from the content, converts XML in memory, and
creates no .properties sidecar file. No Apache Log4j runtime is required.

Programmatic use, after installing the manager:

    LogManagerExt manager = (LogManagerExt) java.util.logging.LogManager.getLogManager();
    manager.readConfiguration(Path.of("log4j.xml"));
    List<String> warnings = manager.getConfigurationWarnings();

File and InputStream overloads are also supported. Caller-owned streams stay open.
The inherited readConfiguration() reloads the configured file; inherited
updateConfiguration methods continue to accept JUL properties only.
Creating a separate LogManagerExt instance does not replace the global JUL manager.

Support follows Log4J1Converted and Log4J2Converted: Console/File and size-based rolling appenders,
levels, additivity and local Log4j 2 properties. Layouts become SimpleFormatter;
SYSTEM_OUT becomes SYSTEM_ERR. Only one appender per JUL handler class and one
reference per appender are supported. Time-based rolling, compressed archives, filters, custom plugins,
and unsupported lookups are rejected with IOException before JUL is reset.
Conversion warnings are available as an immutable list from
getConfigurationWarnings(). Relative log file paths use the working directory.
External DTD declarations in Log4j 1 are accepted without fetching resources;
internal DTD subsets are rejected. See README_Log4JConverters.txt for details.

