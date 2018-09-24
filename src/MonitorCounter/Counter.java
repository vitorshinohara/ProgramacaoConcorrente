/*
 * Exercício 1
 * Monitor Counter 
 * Possibilita um processo dormir até alcançar um valor.
 * . A classe Counter permite duas operações: 
 * increment() e sleepUntil(int x).
 */
package MonitorCounter;

/**
 *
 * @author yudi
 */
public class Counter {

    int value = 0;
    
    public static void main(String[] args) {
        Counter counter = new Counter();
        
        IncrementThread incrementThread = new IncrementThread(counter);
        SleepThread sleepThread = new SleepThread(counter);
        
        sleepThread.start();
        incrementThread.start();
    
    }

    public synchronized void increment() {
        System.out.println("Incrementing.. Current value: " + value);
        this.value++;
        notify();
        
    }

    public synchronized void sleepUntil(int x) {
        System.out.println("Thread sleeping...");
        boolean mustWakeUp;
        
        mustWakeUp = (this.value >= x) ? true : false; 
        
        while (!mustWakeUp) {
            System.out.println("Running");
            
            try {
                System.out.println("Thread waiting...");
                wait();
            } catch (InterruptedException ex) {}

            mustWakeUp = (this.value >= x) ? true : false; 
            
            System.out.println("Thread woke up!");
        }
    }
}

class IncrementThread extends Thread{
    
    Counter thread;
    public IncrementThread(Counter thread) {
        this.thread = thread;
    }
    
    @Override
    public void run(){
        while (true) {
            thread.increment();
        }
    }
    
    
}
class SleepThread extends Thread{
    
    Counter thread;

    public SleepThread(Counter thread) {
        this.thread = thread;
    }
    
    @Override
    public void run(){
        this.thread.sleepUntil(20);
    }
}