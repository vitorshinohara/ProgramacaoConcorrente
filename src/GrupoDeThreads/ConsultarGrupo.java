/*
 * Programa Java que consulta periodicamente o estado de um conjunto de threads
 */
package GrupoDeThreads;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vítor Yudi Shinohara
 */
public class ConsultarGrupo {

    public static void main(String[] args) {
        // Criando um grupo de Threads
        ThreadGroup threadGroup = new ThreadGroup("Grupo de Threads");

        // 5 threads com 500 ms de delay
        adicionarThreadNoGrupo(threadGroup, 5);
        
        MonitorThread monitorThread = new MonitorThread(threadGroup);
        monitorThread.start();

    }

    public static void adicionarThreadNoGrupo(ThreadGroup grupo, int numberOfThreads) {
        /* Cria as Threads e adiciona no grupo */
        for (int i = 0; i < numberOfThreads; i++) {
            ThreadFactory thread = new ThreadFactory(grupo, Integer.toString(i));
            thread.start();
        }

    }
}

class MonitorThread extends Thread {

    Random random = new Random();
    int monitorDelay = random.nextInt(5000) + 1000;

    ThreadGroup group;

    public MonitorThread(ThreadGroup group) {
        this.group = group;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.format("[MONITOR THREAD] %d Threads running on group...\n", this.group.activeCount());
                Thread.sleep(this.monitorDelay);
                
            } catch (InterruptedException ex) {
                System.out.println("[MONITOR THREAD] Stopping...");
            }
        }
    }

}

class ThreadFactory extends Thread {

    Random random = new Random();
    // Gera tempo aleatório para Thread dormir
    int sleepTime = random.nextInt(5000) + 500;

    public ThreadFactory(ThreadGroup grupo, String name) {
        // Instancia construtor da classe Thread
        super(grupo, name);
    }

    @Override
    public void run() {
        // Thread inútil que só exibe mensagens
        while (true) {
            try {

                System.out.format("[Thread %s] Running...\n", currentThread().getName());
                Thread.sleep(this.sleepTime);
                
                Random percentageRandom = new Random();
                int percentage = percentageRandom.nextInt(100);
                
                if (percentage <= 15) {
                    throw  new InterruptedException();
                }

            } catch (InterruptedException ex) {
                System.out.format("[Thread %s] Stopping...\n", currentThread().getName());
                break;
            }
        }
    }
}
