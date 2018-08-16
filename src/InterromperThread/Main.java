/*
 * Tópico 3 - Exercício 1
 * Criar 3 threads e esperar um tempo aleatório para terminar
 */
package InterromperThread;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String argv[]) {
        ThreadFactory thread1 = new ThreadFactory();
        ThreadFactory thread2 = new ThreadFactory();
        ThreadFactory thread3 = new ThreadFactory();

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class ThreadFactory extends Thread {

    @Override
    public void run() {
        try {
            System.out.format("[Thread %d] Running...\n", Thread.currentThread().getId());
            Random random = new Random();
            int num = random.nextInt(10000) + 5000;
            System.out.format("[Thread %d] Sleeping...\n", Thread.currentThread().getId());

            Thread.sleep(num);
            throw new InterruptedException();
            
        } catch (InterruptedException ex) {
            System.out.format("[Thread %d] Thread parou.\n", Thread.currentThread().getId());
        }

    }
}
