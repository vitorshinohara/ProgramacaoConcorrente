/*
 * Faça um código que uma thread t1 e t2 são inicializadas simultaneamente, mas a t2 pode somente
 * continuar a execução após a sinalização de t1.
 */
package Semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author  Vítor Yudi Shinohara
 * @ra      1711199
 */

public class SinalizacaoSemaforo {

    public static void main(String[] args) {
        Signal signal = new Signal();
        Semaphore semaphore = new Semaphore(1);
        ThreadSignalizer t1 = new ThreadSignalizer(semaphore, signal, "t1");
        ThreadSignalizer t2 = new ThreadSignalizer(semaphore, signal, "t2");

        t1.start();
        t2.start();
    }
}

class Signal {

    public boolean wasSignaled = true;
}

class ThreadSignalizer extends Thread {

    Semaphore semaphore;
    static Signal signal;

    public ThreadSignalizer(Semaphore semaphore, Signal signal, String threadName) {
        super(threadName);
        this.semaphore = semaphore;
        this.signal = signal;
    }

    @Override
    public void run() {
        /* Thread T1 */
        if (this.getName().equals("t1")) {
            Random random = new Random();
            while (true) {

                int randomNumber;
                randomNumber = random.nextInt(101);

                if (randomNumber < 75 && !signal.wasSignaled) {
                    try {
                        semaphore.acquire();
                        System.out.println("[" + this.getName() + "] Sending signal.");
                        signal.wasSignaled = true;
                    } catch (InterruptedException ex) {
                    } finally {
                        process(false);
                        semaphore.release();
                    }
                } else {
                    try {
                        semaphore.acquire();
                        System.out.println("[" + this.getName() + "] Sending stop signal.");
                        signal.wasSignaled = false;
                    } catch (Exception e) {
                    } finally {
                        semaphore.release();
                    }
                }
                try {
                    Thread.sleep((long) (Math.random() * 5000));
                } catch (InterruptedException ex) {
                }

            }
        } /* Thread T1 end*/ /* Thread T2 */ else {
            while (true) {
                try {
                    semaphore.acquire();
                    if (signal.wasSignaled) {
                        process(true);
                    }
                    
                } catch (InterruptedException ex) {}
                finally{
                    semaphore.release();
                }
                
                try {
                    Thread.sleep((long) Math.random() * 1000);
                } catch (InterruptedException ex) {}
                
            }
        }
        /* Thread T2 end */
    }

    private void process(boolean shouldPrint) {
        try {
            if (shouldPrint) {
                System.out.println("[" + this.getName() + "] Processing...");
            }
            Thread.sleep((long) (Math.random() * 2500));
        } catch (InterruptedException ex) {
        }
    }

}
