/*
 * Thread que envia interrupções para outras Threads
 * Foi recriado a Thread para a leitura do arquivo 'quotes.txt'
 * Após sua criação, a mesma é interrompida
 */
package ThreadEnviaInterrupcao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String arvg[]) {
        String filePath = "/home/yudi/quotes.txt";
        ThreadReadFile threadReadFile = new ThreadReadFile(filePath);
        threadReadFile.start();

        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("-------------------------------");
        System.out.println("[MAIN] Mandando sinal de interrupção");
        threadReadFile.interrupt();

    }
}

class ThreadReadFile extends Thread {

    BufferedReader br = null;
    FileReader fr = null;
    String path;

    public ThreadReadFile(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("[THREAD] Thread Running...");
                try {
                    this.fr = new FileReader(path);
                    this.br = new BufferedReader(fr);

                } catch (FileNotFoundException e) {
                    System.out.println("Arquivo não encontrado");
                }
                String sCurrentLine;

                try {
                    while ((sCurrentLine = br.readLine()) != null) {
                        //System.out.println(sCurrentLine);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ThreadReadFile.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (Thread.interrupted()) {
                    throw new InterruptedException();

                }
                Thread.sleep(1000);
                System.out.println("[THREAD] Sleeping...");
            } catch (InterruptedException ex) {
                System.out.println("-------------------------------");
                System.out.println("Tnread interrompida. Parando...");
                System.out.println("-------------------------------");
                break;
            }
        }
    }
}
