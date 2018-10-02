/*
 * Faça um código que uma thread t1 e t2 são inicializadas e t1 espera por t2 e vice-versa.
 * Exemplo:
 * t1:
 * * trecho1.1
 * * trecho1.2
 * t2:
 * * trecho2.1
 * * trecho2.2

 * thecho1.1 ocorre antes trecho2.2 e threcho2.1 ocorre antes de trecho1.2.
 */

package Semaforos;

/**
 *
 * @author  Vítor Yudi Shinohara
 * @ra      1711199
 */

import java.util.concurrent.Semaphore;

public class Rendezvous {

    public static void main(String[] args) {
        Controller controller = new Controller();
        Semaphore semaphore = new Semaphore(1);
        
        RendezvousThread t1 = new RendezvousThread(semaphore, controller, "t1");
        RendezvousThread t2 = new RendezvousThread(semaphore, controller, "t2");
        t1.start();
        t2.start();

    }
}

class Controller {

    /* Classe para controlar flags de execução. */
    boolean trecho1_1, trecho1_2, trecho2_1, trecho2_2 = false;

    public void setAllFalse() {
        trecho1_1 = false;
        trecho1_2 = false;
        trecho2_1 = false;
        trecho2_2 = false;
    }
}

class RendezvousThread extends Thread {

    Semaphore semaphore;
    Controller trechosController;

    public RendezvousThread(Semaphore semaphore, Controller trechosController, String threadName) {
        super(threadName);
        this.semaphore = semaphore;
        this.trechosController = trechosController;
    }

    @Override
    public void run() {
        /* Thread T1 */
        if (this.getName().equals("t1")) {
            while (true) {

                /* Executar trecho1_1 */
                if (!trechosController.trecho1_1 && !trechosController.trecho1_2 && !trechosController.trecho2_1 && !trechosController.trecho2_2) {
                    try {
                        semaphore.acquire();
                        System.out.println("[" + this.getName() + "] Executando trecho 1_1");
                        trechosController.trecho1_1 = true;

                    } catch (InterruptedException ex) {
                    } finally {
                        semaphore.release();
                    }
                }

                /* Executar trecho 1_2 */
                if (trechosController.trecho1_1 && !trechosController.trecho1_2 && trechosController.trecho2_1 && !trechosController.trecho2_2) {
                    try {
                        semaphore.acquire();
                        System.out.println("[" + this.getName() + "] Executando trecho 1_2");
                        trechosController.trecho1_2 = true;
                    } catch (Exception e) {
                    } finally {
                        semaphore.release();
                    }

                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                }
            }
        } /* Thread T2 */ else {

            while (true) {
                /* Executar Trecho 2_1 */
                if (trechosController.trecho1_1 && !trechosController.trecho2_1 && !trechosController.trecho1_2 && !trechosController.trecho2_2) {
                    try {
                        semaphore.acquire();
                        System.out.println("[" + this.getName() + "] Executando trecho 2_1");
                        trechosController.trecho2_1 = true;
                    } catch (Exception e) {
                    } finally {
                        semaphore.release();
                    }
                }
                /* Executar Trecho 2_2 */
                if (trechosController.trecho1_1 && trechosController.trecho1_2 && trechosController.trecho2_1 && !trechosController.trecho2_2) {
                    try {
                        semaphore.acquire();
                        System.out.println("[" + this.getName() + "] Executando trecho 2_2");
                        trechosController.setAllFalse();
                    } catch (Exception e) {
                    } finally {
                        semaphore.release();
                    }
                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {}

            }

        }
    }

}
