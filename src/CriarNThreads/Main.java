/*
 * Programa que recebe um valor inteiro 'num'.
 * Criar 'num' threads de 2 modos diferentes
 */
package CriarNThreads;

import java.util.Scanner;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String argv[]) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int sleepTime = 500;
        
        System.out.println("Method 1");
        createNThreadsMethod1(num, sleepTime);
        
        System.out.println("Method 2");
        createNThreadsMethod2(num, sleepTime);
    }

    private static void createNThreadsMethod1(int num, int sleepTime) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("Teste");
        };

        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                System.out.println("Thread número " + Thread.currentThread().getId());
            }).start();

            Thread.sleep(sleepTime);
        }
    }

    private static void createNThreadsMethod2(int num, int sleepTime) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            ThreadCreatorMethod2 thread = new ThreadCreatorMethod2();
            thread.start();
            Thread.sleep(sleepTime);
        }
    }
}

class ThreadCreatorMethod2 extends Thread {

    @Override
    public void run() {
        System.out.println("Thread número " + Thread.currentThread().getId());
    }

}
