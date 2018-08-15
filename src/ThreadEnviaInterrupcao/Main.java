/*
 * Thread que envia interrupções para outras Threads
 */
package ThreadEnviaInterrupcao;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String arvg[]) {

        ThreadFactory thread = new ThreadFactory();
        thread.start();
        
    }
}

class ThreadFactory extends Thread {

    @Override
    public void run() {
        System.out.println("Hello World");
    }
}
