/*
 * Faça um código que possibilite que 2 ou mais threads realizem o incremento de um contador. Faça a
 * exclusão mútua com semáforo
 */
package Semaforos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author  Vítor Yudi Shinohara
 * @ra      1711199
 */

public class Mutex {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        MutexThread t1 = new MutexThread(semaphore, "t1");
        MutexThread t2 = new MutexThread(semaphore, "t2");
        t1.start();t2.start();
    }
}



class MutexThread extends Thread {
    static int counter;
    private Semaphore semaphore;

    public MutexThread(Semaphore semaphore, String threadName) {
        super(threadName);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            incrementCounter();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
        }
    }

    private void incrementCounter() {
        try {
            semaphore.acquire();
            counter++;
            System.out.println("[" + this.getName() + "] Incrementing counter. Current Value: " + counter);
        } catch (InterruptedException ex) {
        } finally {
            semaphore.release();
        }
    }

}
