package org.hls.check;

import java.util.HashMap;
import java.util.Map;

class T implements Runnable {

    private ThreadLocal<Map<Object,Object>> threadEnv = ThreadLocal.withInitial(HashMap::new);

    private int id;

    T(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        // threadEnv.set(new HashMap<>());
        threadEnv.get().put("id", currentThread.getId());
        threadEnv.get().put("threadId", currentThread.getId());
        threadEnv.get().put("threadName", currentThread.getName());
        try {
            Thread.sleep((1+id)*500);
        } catch (InterruptedException e) {}

        System.out.printf("%d: id:%d, name:%s\n", id,
            threadEnv.get().get("id"),
            threadEnv.get().get("threadId"),
            threadEnv.get().get("threadName"));
    }
}

public class App {

    static int N = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[N];
        for (int i=0; i<N; ++i) {
            threads[i] = new Thread(new T(i));
            threads[i].start();
        }

        System.out.println("wait");
        for (int i=N-1; i>=0; i--) {
            // if (threads[i].isAlive())
                threads[i].join();
        }

        System.out.println("done");
    }


}
