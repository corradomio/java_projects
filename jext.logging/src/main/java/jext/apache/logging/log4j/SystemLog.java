package jext.apache.logging.log4j;

public class SystemLog {

    public class Out {
        private final boolean errorLevel;

        Out(boolean errorLevel) {
            this.errorLevel = errorLevel;
        }

        public void println(String msg) {
            if (this.errorLevel)
                SystemLog.this.logger.info(msg);
            else
                SystemLog.this.logger.error(msg);
        }
    }

    private final Logger logger;
    public final Out out = new Out(false);
    public final SystemLog.Out err = new Out(true);

    SystemLog(Logger logger) {
        this.logger = logger;
    }
}
