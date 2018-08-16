/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorarThreads;

/**
 *
 * @author yudi
 */
public class UnuselessThread extends Thread {

    // Thread que só exibe uma mensagem
    int sleepTime;

    public UnuselessThread(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            try {

                System.out.format("[Thread %d] Running...\n", Thread.currentThread().getId());
                Thread.sleep(this.sleepTime);

            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
