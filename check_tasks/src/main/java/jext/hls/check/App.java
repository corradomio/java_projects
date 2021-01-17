package jext.hls.check;

import jext.util.Sleep;
import jext.util.concurrent.ExecutorService;
import jext.util.concurrent.Executors;
import jext.util.concurrent.QueueExecutorService;

import java.util.concurrent.TimeUnit;

class R implements Runnable {

    private int i;

    R(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i);
        Sleep.sleep(5, TimeUnit.SECONDS);
    }
}

class T implements Runnable {
    private int i;
    private String q;

    T(String q, int i) {
        this.q = q;
        this.i = i;
    }

    @Override
    public void run() {
        System.out.printf("%s/%d\n", q, i);
        Sleep.sleep(1, TimeUnit.SECONDS);
    }

}

public class App {

    private static void test1() {

        ExecutorService service = Executors.newCachedThreadPool(8);

        for(int i=0; i<100; i++)
            service.submit(new R(i));

        while (!service.isCompleted()) {
            service.dump();
            Sleep.sleep(1, TimeUnit.SECONDS);
        }

        service.waitForCompletion();
        service.dump();
    }

    private static void test2() {
        QueueExecutorService service = Executors.newQueueThreadPool(8);

        for(int i=0; i<100; i++) {
            String q = String.valueOf(i/10);
            service.submit(q, new T(q, i%10));
        }

        service.waitForCompletion();
    }

    public static void main(String[] args) {
        // test1();
        test2();
    }
}
