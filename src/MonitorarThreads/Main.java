/*
 * Uma thread que monitora se outras threads estão em execução
 * Uma thread que interrompe a execução de outras threads
 * N threads que somemte printam valores e são interrompidas por outra Thread
 */
package MonitorarThreads;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yudi
 */
public class Main {

    public static void main(String argv[]) {
        List<Thread> list = new ArrayList<>();
        
        int delay = 5000;
        int numberOfThreads = 10;
        
        list = createAndStartNUnuselessThreads(numberOfThreads, delay);

        MonitorThread monitorThread = new MonitorThread(2500,5000,list);
        monitorThread.start();
        InterruptThread interruptThread = new InterruptThread(2500,5000,list);
        interruptThread.start();
    }
    
    private static List<Thread> createAndStartNUnuselessThreads(int n, int delay){
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            UnuselessThread thread = new UnuselessThread(delay);
            threadList.add(thread);
            thread.start();
        }
        
        return threadList;
    }

}



