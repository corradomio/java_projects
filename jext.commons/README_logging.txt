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

