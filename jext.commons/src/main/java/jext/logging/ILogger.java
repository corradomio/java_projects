package jext.logging;

public interface ILogger {
    // query

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    // debug/debugf

    void debug(Object message);

    void debug(Object message, Throwable t);

    void debugf(String format, Object... args);

    // info/infof

    void info(Object message);

    void info(Object message, Throwable t);

    void infof(String format, Object... args);

    // warn/warnf

    void warn(Object message);

    void warn(Object message, Throwable t);

    void warnf(String format, Object... args);

    // error/errorf

    void error(Object message);

    void error(Object message, Throwable t);

    void errorf(String format, Object... args);

    // fatal

    void fatal(Object message);

    void fatal(Object message, Throwable t);

    // timed debug/info
    // it write a log ONLY each 'timeout' milliseconds

    void debugt(String format, Object... args);

    void debugft(String format, Object... args);

    void infoft(String format, Object... args) ;

    void warnft(String format, Object... args);

    // counted warn/error
    // it generate ONLY a specified number of messages

    void warnc(String category, String format, Object... args);

    void errorc(String category, String format, Object... args);

    // query

    void println(String message);

    void printf(String message, Object ... args);
}
