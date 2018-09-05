/*
    Programa em Java que use Threads para encontrar
    os ńnúmeros primos dentro de um intervalo.  O mé́etodo que
    contabiliza os ńnúmeros primos possui como entrada:
    valor inicial e final do intervalo, n ́umero de threads.
 */
package ThreadSafety;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author a1711199
 */
public class BlockSyncThreadSafety {

    private int maxNum;
    private int minNum;
    private int currentNum;
    private List<Integer> list = new ArrayList();

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

    public BlockSyncThreadSafety() {
    }

    public static void main(String[] args) {
        BlockSyncThreadSafety blockSyncThreadSafety = new BlockSyncThreadSafety();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de Threads a serem criadas:");
        int nThreads = scanner.nextInt();
        System.out.println("Digite o intervalo (número inicial)");
        blockSyncThreadSafety.setMinNum(scanner.nextInt());
        System.out.println("Digite o intervalo (número final)");
        blockSyncThreadSafety.setMaxNum(scanner.nextInt());

        blockSyncThreadSafety.setCurrentNum(blockSyncThreadSafety.getMinNum());
        blockSyncThreadSafety.createNThreads(nThreads);

    }

    private void createNThreads(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            BlockSyncThread thread = new BlockSyncThread(this);
            thread.start();
        }
    }
}

class BlockSyncThread extends Thread {

    BlockSyncThreadSafety blockSyncThreadSafety = null;

    public BlockSyncThread(BlockSyncThreadSafety blockSyncThreadSafety) {
        this.blockSyncThreadSafety = blockSyncThreadSafety;
    }

    @Override
    public void run() {
        System.out.println("Running");
        int currentNumber = 0;

        synchronized (this) {
            currentNumber = blockSyncThreadSafety.getCurrentNum();
            currentNumber = currentNumber + 1;
            blockSyncThreadSafety.setCurrentNum(currentNumber);
        }

        while (currentNumber < blockSyncThreadSafety.getMaxNum()) {

            int divisibleCount = 0;

            for (int i = 1; i <= currentNumber; i++) {
                if (currentNumber % i == 0) {
                    divisibleCount++;
                }
            }

            if (divisibleCount <= 2) {
                System.out.println("[Thread " + Thread.currentThread().getId() + "] Número " + currentNumber + " é primo!");
                blockSyncThreadSafety.getList().add(currentNumber);
            }

            synchronized (this) {
                currentNumber = blockSyncThreadSafety.getCurrentNum();
                currentNumber = currentNumber + 1;
                blockSyncThreadSafety.setCurrentNum(currentNumber);
            }

        }
    }
}
