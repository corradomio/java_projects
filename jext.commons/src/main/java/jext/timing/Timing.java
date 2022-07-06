package jext.timing;

import jext.logging.Logger;

public class Timing {

    private Logger logger = Logger.getLogger(Timing.class);

    private String header;
    private long last;
    private long start;

    public Timing(String header) {
        this.header = header;
        start();
    }

    public Timing(Logger logger, String header) {
        this.logger = logger;
        this.header = header;
        start();
    }

    public void start() {
        start = System.currentTimeMillis();
        last = start;
    }

    public void partial(String what, long mindelta) {
        long current = System.currentTimeMillis();
        long delta = current - last;

        if (delta > mindelta) {
            logger.warnf("[%s.%s] ... partial in %d ms", header, what, delta);
            System.out.printf("[%s.%s] ... partial in %d ms\n", header, what, delta);
        }

        last = current;
    }

    public void done() {
        done(0);
    }

    public void done(long mindelta) {
        long delta = System.currentTimeMillis() - start;
        if (delta > mindelta) {
            logger.warnf("[%s] done in %d ms", header, delta);
            System.out.printf("[%s] done in %d ms\n", header, delta);
        }
    }
}
