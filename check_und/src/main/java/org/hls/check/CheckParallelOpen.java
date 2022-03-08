package org.hls.check;

import com.scitools.understand.Database;
import com.scitools.understand.Entity;
import com.scitools.understand.Understand;

class ParallelUnd implements Runnable {

    private String undpath;
    private Database und;
    private int count;

    ParallelUnd(String undpath) {
        this.undpath = undpath;
    }

    public void run() {
        try {
            System.out.printf("[%d] open\n", Thread.currentThread().getId());
            und = Understand.open(undpath);
            Entity[] ents = und.ents("");

            System.out.printf("[%d]   do something ...\n", Thread.currentThread().getId());
            for (Entity e : ents) {
                doSomething(e);
            }
            System.out.printf("[%d]   done %d\n", Thread.currentThread().getId(), count);

        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (und != null) {
                System.out.printf("[%d] close\n", Thread.currentThread().getId());
                und.close();
                und = null;
            }
        }
    }

    private void doSomething(Entity e) {
        try {
            Thread.yield();
            count += 1;
            if (count%1000 == 0) {
                System.out.printf("[%d] ... %d\n", Thread.currentThread().getId(), count);
                Thread.sleep(20);
            }
            // Thread.sleep(10);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

}

public class CheckParallelOpen {

    public static void main(String[] args) {

        String UND_PATH = "D:\\SPLGroup\\spl-workspaces\\sample-projects\\cocome-maven-project\\.spl\\scitools.und";
        int N = 2;

        Thread[] threads = new Thread[N];
        for (int i = 0; i<N; ++i)
            threads[i] = new Thread(new ParallelUnd(UND_PATH));

        // start all threads
        {
            for (int i = 0; i < N; ++i)
                threads[i].start();
        }

        // wait for the termination
        try {
            boolean running = true;
            while (running) {
                running = false;
                for (int i = 0; i < N; ++i)
                    if (threads[i].isAlive())
                        running = true;
                Thread.sleep(500);
            }

        }
        catch (Throwable t) {
            t.printStackTrace();
        }

    }
}
