/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorarThreads;

import java.util.List;
import java.util.Random;

/**
 *
 * @author yudi
 */
public class InterruptThread extends Thread {
    int minDelay, maxDelay;
    List<Thread> threadList;

    public InterruptThread(int minDelay, int maxDelay, List<Thread> threadList) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
        this.threadList = threadList;
    }
    
    private int generateIndex(){
        int index;
        Random rand = new Random(); 
        index = rand.nextInt(this.threadList.size()); 
        
        return index;
    }
    
    private int generateDelayTime(){
        int delay;
        
        Random rand = new Random(); 
        delay = rand.nextInt(this.maxDelay) + this.minDelay; 
        
        return delay;
    }
    
    @Override
    public void run(){
        int indexToInterrupt;
        int timeToSleep;
        while (true) {
            
            System.out.println("[INTERRUPT THREAD] Running...");
            
            try {
                
                indexToInterrupt = generateIndex();
                this.threadList.get(indexToInterrupt).interrupt();
                
                timeToSleep = generateDelayTime();
                Thread.sleep(timeToSleep);
                
            } catch (InterruptedException ex) {
                System.out.println("[INTERRUPT THREAD] Thread parou.\n");
            }
        }
    }
}