/*
    Programa em Java que use Threads para encontrar
    os ńnúmeros primos dentro de um intervalo.  O mé́etodo que
    contabiliza os ńnúmeros primos possui como entrada:
    valor inicial e final do intervalo, n ́umero de threads.
    Abordagem Thread Safety: Sincronização do método
*/
package ThreadSafety;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author a1711199
 */
public class MethodSyncThreadSafety {

    private int maxNum;
    private int minNum;
    private int currentNum;
    private List<Integer> list = new ArrayList();

    public MethodSyncThreadSafety(int maxNum, int minNum, int nThreads) {
        this.maxNum = maxNum;
        this.minNum = minNum;
        this.currentNum = this.minNum;
        this.createNThreads(nThreads);
    }
    
    
    

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }

    public MethodSyncThreadSafety() {
    }

    public static void main(String[] args) {
        System.out.println("Método Syncronized");
        MethodSyncThreadSafety methodSyncThreadSafety = new MethodSyncThreadSafety();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de Threads a serem criadas:");
        int nThreads = scanner.nextInt();
        System.out.println("Digite o intervalo (número inicial)");
        methodSyncThreadSafety.setMinNum(scanner.nextInt());
        System.out.println("Digite o intervalo (número final)");
        methodSyncThreadSafety.setMaxNum(scanner.nextInt());

        methodSyncThreadSafety.setCurrentNum(methodSyncThreadSafety.getMinNum());
        methodSyncThreadSafety.createNThreads(nThreads);

    }

    private void createNThreads(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            MethodSyncThread thread = new MethodSyncThread(this);
            thread.start();
        }
    }
}

class MethodSyncThread extends Thread {

    MethodSyncThreadSafety methodSyncThreadSafety = null;

    public MethodSyncThread(MethodSyncThreadSafety methodSyncThreadSafety) {
        this.methodSyncThreadSafety = methodSyncThreadSafety;
    }

    @Override
    public void run() {
        System.out.println("Running");
        int currentNumber = 0;

        currentNumber = getAndIncCurrentNumber(currentNumber);

        while (currentNumber < methodSyncThreadSafety.getMaxNum()) {

            int divisibleCount = 0;

            for (int i = 1; i <= currentNumber; i++) {
                if (currentNumber % i == 0) {
                    divisibleCount++;
                }
            }

            if (divisibleCount <= 2) {
                System.out.println("[Thread " + Thread.currentThread().getId() + "] Número " + currentNumber + " é primo!");
                methodSyncThreadSafety.getList().add(currentNumber);
            }

            currentNumber = getAndIncCurrentNumber(currentNumber);

        }
    }

    private synchronized int getAndIncCurrentNumber(int currentNumber) {
        currentNumber = methodSyncThreadSafety.getCurrentNum();
        currentNumber = currentNumber + 1;
        methodSyncThreadSafety.setCurrentNum(currentNumber);
        return currentNumber;
    }
}
