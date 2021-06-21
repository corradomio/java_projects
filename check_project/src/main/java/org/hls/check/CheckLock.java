package org.hls.check;

import java.util.concurrent.locks.ReentrantLock;

public class CheckLock {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        // lock.lock();
        try {

        }
        finally {
            lock.unlock();
        }
    }
}
