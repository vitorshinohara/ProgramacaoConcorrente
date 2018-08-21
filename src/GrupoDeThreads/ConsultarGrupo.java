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

        // Thread principal dorme
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConsultarGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("-------------------------------");
        System.out.println("[MAIN] Interrompendo Threads...");
        System.out.println("-------------------------------");

        threadGroup.interrupt();
    }

    public static void adicionarThreadNoGrupo(ThreadGroup grupo, int numberOfThreads) {
        /* Cria as Threads e adiciona no grupo */
        for (int i = 0; i < numberOfThreads; i++) {
            ThreadFactory thread = new ThreadFactory(grupo, Integer.toString(i));
            thread.start();
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

            } catch (InterruptedException ex) {
                System.out.format("[Thread %s] Stopping...\n", currentThread().getName());
                break;
            }
        }
    }
}
