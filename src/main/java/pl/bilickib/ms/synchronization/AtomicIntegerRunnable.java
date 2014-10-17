package pl.bilickib.ms.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerRunnable implements Runnable {
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        counter.incrementAndGet();
    }
}