package jext.hls.check;

import jext.util.Sleep;
import jext.util.concurrent.ExecutorService;
import jext.util.concurrent.Executors;
import jext.util.concurrent.QueueExecutorService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class R implements Runnable {

    private int i;
    private long millis;

    R(int i, long millis) {
        this.i = i;
        this.millis = millis;
    }

    @Override
    public void run() {
        System.out.println(i);
        Sleep.sleep(millis);
    }
}

class T implements Runnable {
    private int i;
    private String q;
    private long millis;

    T(String q, int i, long millis) {
        this.q = q;
        this.i = i;
        this.millis = millis;
    }

    @Override
    public void run() {
        System.out.printf("%s/%d\n", q, i);
        Sleep.sleep(millis);
    }

}

public class App {

    static Random rnd = new Random();

    private static void test1() {

        ExecutorService service = (ExecutorService) Executors.newDynamicThreadPool(8);

        for(int i=0; i<100; i++) {
            long millis = (5000 + rnd.nextInt(5000));
            System.out.printf("%dms\n", millis);
            service.submit(new R(i, millis));
        }

        // while (!service.isCompleted()) {
        //     service.dump();
        //     Sleep.sleep(1, TimeUnit.SECONDS);
        // }

        service.waitForCompletion();
        service.dump();
    }

    private static void test2() {
        QueueExecutorService service = Executors.newQueueThreadPool(8);

        for(int i=0; i<100; i++) {
            long millis = (5000 + rnd.nextInt(5000));
            System.out.printf("%dms\n", millis);
            service.submit(new R(i, millis));
            String q = String.valueOf(i/10);
            service.submit(q, new T(q, i%10, millis));
        }

        service.waitForCompletion();
    }

    private static void test3() {

        ExecutorService service = (ExecutorService) Executors.newDynamicThreadPool(8);

        for(int i=0; i<100; i++) {
            long millis = (5000 + rnd.nextInt(5000));
            System.out.printf("%dms\n", millis);
            String q = String.valueOf(i/10);
            service.submit(new T(q, i%10, millis));
        }

        // while (!service.isCompleted()) {
        //     service.dump();
        //     Sleep.sleep(1, TimeUnit.SECONDS);
        // }

        service.waitForCompletion();
        service.dump();
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }
}
