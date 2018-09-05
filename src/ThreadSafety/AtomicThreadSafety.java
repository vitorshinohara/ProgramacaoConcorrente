/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    AtomicInteger current;
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
    }
    
    public void createNThreads(int n){
        for (int i = 0; i < n; i++) {
            // create threads
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

class AtomicThread extends Thread{
    AtomicThreadSafety atomicThreadSafety;

    public AtomicThread(AtomicThreadSafety atomicThreadSafety) {
        this.atomicThreadSafety = atomicThreadSafety;
    }
    
    @Override
    public void run(){
        System.out.println("Running");
        AtomicInteger currentNumber = new AtomicInteger(-1);
        
        currentNumber = atomicThreadSafety.current.getAndIncrement();
        

        while (currentNumber < atomicThreadSafety.getMaxRange()){

            int divisibleCount = 0;

            for (int i = 1; i <= currentNumber; i++) {
                if (currentNumber % i == 0) {
                    divisibleCount++;
                }
            }

            if (divisibleCount <= 2) {
                System.out.println("[Thread " + Thread.currentThread().getId() + "] Número " + currentNumber + " é primo!");
                primosThreadSafety.getList().add(currentNumber);
            }

            synchronized (this) {
                currentNumber = primosThreadSafety.getCurrentNum();
                currentNumber = currentNumber + 1;
                primosThreadSafety.setCurrentNum(currentNumber);
            }

        }
    }
    
}
