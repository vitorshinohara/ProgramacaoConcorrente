/*
    Programa em Java que use Threads para encontrar
    os ńnúmeros primos dentro de um intervalo.  O mé́etodo que
    contabiliza os ńnúmeros primos possui como entrada:
    valor inicial e final do intervalo, n ́umero de threads.
    Abordagem Thread Safety: Variável atômica
 */
package ThreadSafety;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author a1711199
 */
public class AtomicThreadSafety {

    private int minRange;
    private int maxRange;
    AtomicInteger current = new AtomicInteger(0);
    private List<Integer> list = new ArrayList();

    public AtomicThreadSafety() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a quantidade de Threads a serem criadas:");
        int nThreads = scanner.nextInt();

        System.out.println("Digite o intervalo (número inicial)");
        int min = scanner.nextInt();

        System.out.println("Digite o intervalo (número final)");
        int max = scanner.nextInt();
        
        AtomicThreadSafety atomicThreadSafety = new AtomicThreadSafety();
        
        atomicThreadSafety.setMaxRange(max);
        atomicThreadSafety.setMinRange(min);

        atomicThreadSafety.current.set(min);

        atomicThreadSafety.createNThreads(nThreads);
    }

    public void createNThreads(int n) {
        for (int i = 0; i < n; i++) {
            AtomicThread atomicThread = new AtomicThread(this);
            atomicThread.start();
        }
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

}

class AtomicThread extends Thread {

    AtomicThreadSafety atomicThreadSafety;

    public AtomicThread(AtomicThreadSafety atomicThreadSafety) {
        this.atomicThreadSafety = atomicThreadSafety;
    }

    @Override
    public void run() {
        int currentNumber;

        currentNumber = atomicThreadSafety.current.getAndIncrement();

        while (currentNumber < atomicThreadSafety.getMaxRange()) {

            int divisibleCount = 0;

            for (int i = 1; i <= currentNumber; i++) {
                if (currentNumber % i == 0) {
                    divisibleCount++;
                }
            }

            if (divisibleCount <= 2) {
                System.out.println("[Thread " + Thread.currentThread().getId() + "] Número " + currentNumber + " é primo!");
                atomicThreadSafety.getList().add(currentNumber);
            }

            currentNumber = atomicThreadSafety.current.getAndIncrement();

        }
    }

}
