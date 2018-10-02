/*
 * Garantir acesso à seção crítica para no máximo N threads.
 * Faça um código que possibilite que N threads estejam na seção crítica simultaneamente.
 */
package Semaforos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor Yudi Shinohara
 * @ra 1711199
 */
public class Multiplex {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            MultiplexThread multiplexThread = new MultiplexThread(semaphore, "t" + i);
            multiplexThread.start();
        }

    }
}

class MultiplexThread extends Thread {

    Semaphore semaphore;

    public MultiplexThread(Semaphore semaphore, String threadName) {
        super(threadName);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("[" + this.getName() + "] Preparando para entrar na zona crítica");
            try {
                semaphore.acquire();
                process();
            } catch (Exception e) {
            } finally {
                semaphore.release();
                System.out.println("[" + this.getName() + "] Saindo da zona crítica");
            }

            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
            }

        }
    }

    private void process() {
        try {
            System.out.println("[" + this.getName() + "] Processando na zona crítica");
            Thread.sleep((long) Math.random() * 5000);
        } catch (InterruptedException ex) {
        }
    }

}
