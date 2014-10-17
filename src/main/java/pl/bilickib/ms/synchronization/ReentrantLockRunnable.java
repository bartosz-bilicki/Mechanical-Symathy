package pl.bilickib.ms.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockRunnable implements Runnable{
    private int counter = 0;

    private Lock lock;

    private ReentrantLockRunnable(boolean fair) {
        super();

        lock = new ReentrantLock(fair);
    }

    @Override
    public void run() {
        lock.lock();

        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
