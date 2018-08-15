/*
 * Exercício demonstrando a utilização do Thread.join()
 * Uma thread é responsável por computar valores
 * Quando a thread é finalizada, o fluxo principal soma esses valores
 */
package ThreadJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String argv[]) {
        List<Integer> valoresDigitados = new ArrayList<>();
        ThreadFactory thread = new ThreadFactory();

        thread.run();

        try {
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println("[THREAD] A thread finalizada.");
        }

        valoresDigitados = thread.getValoresDigitados();
        int soma = somaValores(valoresDigitados);
        
        System.out.format("[MAIN] Soma: %d \n", soma);
    }

    public static int somaValores(List<Integer> valores) {
        int soma = 0;

        for (int i = 0; i < valores.size(); i++) {
            soma += valores.get(i);
        }

        return soma;
    }

}

class ThreadFactory extends Thread {

    private List<Integer> valoresDigitados;
    private Scanner scanner;

    public ThreadFactory() {
        this.valoresDigitados = new ArrayList();
        this.scanner = new Scanner(System.in);
    }

    /* Método run irá ler valores digitados pelo usuário e terminará a execução ao receber '0' */
    @Override
    public void run() {
        try {
            int valorDigitado = -1;

            while (valorDigitado != 0) {
                valorDigitado = scanner.nextInt();
                this.valoresDigitados.add(valorDigitado);
            }

            throw new InterruptedException();

        } catch (InterruptedException ex) {
            System.out.println("A thread foi interrompida.");
        }

    }

    public List<Integer> getValoresDigitados() {
        return valoresDigitados;
    }

}
