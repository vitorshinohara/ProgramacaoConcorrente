/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefas;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author yudi
 */
public class MatrixProduct {

    public static void main(String[] args) {
        int[][] matrix = populateMatrix(new int[5][5]);
        int[][] matrix2 = populateMatrix(new int[5][5]);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        SharedObject sharedObject = new SharedObject();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                MatrixProductThread matrixProductThread = new MatrixProductThread(row, col, matrix, matrix2, sharedObject);
                executorService.execute(matrixProductThread);
            }

        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(sharedObject.C[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        
        
        executorService.shutdown();
    }

    private static int[][] populateMatrix(int[][] matrix) {
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

class MatrixProductThread implements Runnable{

    int row, col;
    int[][] A, B, C;
    SharedObject sharedObject;

    public MatrixProductThread(int row, int col, int[][] A, int[][] B, SharedObject sharedObject) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        for (int k = 0; k < B.length; k++) {
            sharedObject.C[row][col] += A[row][k] * B[k][col];
            
        }
    }

}


class SharedObject{
    int[][] C = new int[5][5];
}