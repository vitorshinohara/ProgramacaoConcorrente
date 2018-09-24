/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadSafety;

/**
 *
 * @author yudi
 */
public class PerformanceEvaluator {
    public static void main(String[] args) {

        int maxNum = 100_000;
        int minNum = 1;
        int nThreads = 4;
        
        
        BlockSyncThreadSafety blockSyncThreadSafety = new BlockSyncThreadSafety(maxNum, minNum, nThreads);
        
        
    }
    
    
}
