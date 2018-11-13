/*
 * Faça um programa que calcule a soma dos elementos de uma matriz
 * MxN. Divida o programa em tarefas que somam as linhas. O programa
 * deve possibilitar especificar o número de threads para resolver o problema.
*/

package Tarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class MatrixSum {
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        
        List<Future> list = new ArrayList<>();
        
        MatrixSum matrixSum = new MatrixSum();
        
        matrix = matrixSum.populateMatrix(matrix);

        System.out.println("Digite a quantidade de threads:");
        Scanner scanner = new Scanner(System.in);
        int threadNumber = scanner.nextInt();
         
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        
        for (int i = 0; i < 5; i++) {
            MatrixSumThread matrixSumThread = new MatrixSumThread(matrix[i]);
            Future<Integer> future = executorService.submit(matrixSumThread);
            list.add(future);
        }
        
        for (int i = 0; i < 5; i++) {
            while (!list.get(i).isDone()) {
                
            }
            try {
                System.out.println(list.get(i).get());
            } catch (InterruptedException ex) {
                Logger.getLogger(MatrixSum.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(MatrixSum.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        executorService.shutdown();
        
    }

    private int[][] populateMatrix(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int randomNumber = random.nextInt(10);
                matrix[i][j] = randomNumber;
            }
        }
        return matrix;
    }
}


class MatrixSumThread implements Callable<Integer>{
    
    int[] row;

    public MatrixSumThread(int[] row) {
        this.row = row;
    }
    
    
    
    @Override
    public Integer call(){
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum = sum + row[i];
        }
        
        return sum;
    }

}