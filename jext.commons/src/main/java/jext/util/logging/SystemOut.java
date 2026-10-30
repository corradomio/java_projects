package jext.util.logging;

import java.util.logging.Level;

public class SystemOut {

    public class Out {
        private final Level level;

        Out(Level level) {
            this.level = level;
        }

        public void println(String msg) {
            if (this.level == Level.INFO)
                SystemOut.this.logger.info(msg);
            else
                SystemOut.this.logger.error(msg);
        }
    }

    private final Logger logger;
    public final Out out = new Out(Level.INFO);
    public final Out err = new Out(Level.SEVERE);

    SystemOut(Logger logger) {
        this.logger = logger;
    }
}
