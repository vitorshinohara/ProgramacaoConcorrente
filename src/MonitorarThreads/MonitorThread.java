/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MonitorarThreads;

import java.util.List;

/**
 *
 * @author yudi
 */

public class MonitorThread extends Thread{
    List<Thread> threadList;
    int minDelay, maxDelay;

    public MonitorThread(int minDelay, int maxDelay, List<Thread> threadList) {
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
        this.threadList = threadList;
    }
    
    @Override
    public void run(){
        int threadListSize = this.threadList.size();
        while (true) {
            try {
                System.out.println("[MONITOR THREAD] Running...");
                
                for (int i = 0; i < threadListSize; i++) {
                    if (!this.threadList.get(i).isAlive()) {
                        System.out.format("[MONITOR THREAD] Thread %d foi interrompida.\n", threadList.get(i).getId());
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("[MONITOR THREAD] Thread parou.");
            }
        }
    }
}

