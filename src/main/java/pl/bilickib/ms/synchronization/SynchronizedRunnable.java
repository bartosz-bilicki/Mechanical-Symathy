package pl.bilickib.ms.synchronization;

public class SynchronizedRunnable implements Runnable {
    public int counter = 0;

    @Override
    public synchronized void run() {
        counter++;
    }
}