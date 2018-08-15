/*
 * Criação de uma thread que a cada 10 segundos mostra todo
 * o conteúdo do arquivo quotes.txt
 */
package ThreadLeituraArquivo;

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

    public static void main(String argv[]) {
        String FILEPATH = "/home/yudi/quotes.txt";
        ThreadFactory thread = new ThreadFactory(FILEPATH);
        thread.start();
    }

}

class ThreadFactory extends Thread {

    BufferedReader br = null;
    FileReader fr = null;
    String path;

    public ThreadFactory(String path){
        this.path = path;
    }

    @Override
    public void run(){
        while (true) {
            try {
                this.fr = new FileReader(path);
                this.br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                }

            } catch (FileNotFoundException ex) {
                System.out.println("Arquivo não encontrado");

            } catch (IOException ex) {
                Logger.getLogger(ThreadFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }

    }
}
