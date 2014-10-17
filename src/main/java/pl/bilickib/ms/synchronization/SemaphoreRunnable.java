package pl.bilickib.ms.synchronization;

import java.util.concurrent.Semaphore;

public class SemaphoreRunnable  implements Runnable {
    private int counter = 0;

    private final Semaphore semaphore;

    private SemaphoreRunnable(boolean fair) {
        super();

        semaphore = new Semaphore(1, fair);
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            counter++;
        } finally {
            semaphore.release();
        }
    }
}