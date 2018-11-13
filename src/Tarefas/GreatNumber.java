/*
 * Faça̧a um programa que localize o maior valor em um vetor. Divida o
 * programa em tarefas que localizam o maior valor em um segmento do
 * vetor. O programa deve possibilitar especificar o número de tarefas e o
 * número de threads para resolver o problema.
 */
package Tarefas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author yudi
 */
public class GreatNumber {

    public static void main(String[] args) {

        List<Future> futureList = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        list = generateArray();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de threads");
        int threadNumber = scanner.nextInt();
        System.out.println("Digite a quantidade de tarefas");
        int tasksNumber = scanner.nextInt();

        ExecutorService executorService = createAndSubmitTasks(threadNumber, tasksNumber, list, futureList);
        
        
        boolean flag = true;

        while (true) {
            for (int i = 0; i < tasksNumber; i++) {
                if (!futureList.get(i).isDone()) {
                    flag = false;
                    break;
                }
                flag = true;
            }

            if (flag) {
                System.out.println("Terminaram");
                executorService.shutdown();
                break;
            }

        }

    }

    private static ExecutorService createAndSubmitTasks(int threadNumber, int tasksNumber, List<Integer> list, List<Future> futureList) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        for (int i = 0; i < tasksNumber; i++) {
            
            NumberThread numberThread = new NumberThread(list, threadNumber, i);
            Future<Integer> future = executorService.submit(numberThread);
            futureList.add(future);
        }
        return executorService;
    }

    private static List<Integer> generateArray() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        return list;
    }
}

class NumberThread implements Callable<Integer> {

    List<Integer> list;
    int threadNumber;
    int indexThread;

    public NumberThread(List<Integer> list, int threadNumber, int indexThread) {
        this.list = list;
        this.threadNumber = threadNumber;
        this.indexThread = indexThread;
    }

    @Override
    public Integer call() {
        
        int startIndex = indexThread * list.size()/threadNumber;
        int endIndex = startIndex + list.size()/threadNumber;
        endIndex = (endIndex > list.size()) ? list.size(): endIndex;
        
        
        List<Integer> subList = list.subList(startIndex, endIndex);
        System.out.println(Collections.max(subList));
        return Collections.max(subList);
    }
}
