/*
 * Exercício 3
 * Monitor BoundedCounter 
 * Possui um valor mínimo e máximo. 
 * A classe possui dois métodos: increment() e decrement(). 
 * Ao alcançara os limites mínimo ou máximo
 * a thread que alcan¸cou deve ser bloqueada.
 */
package MonitorCounter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class BoundedCounter {
    
    int maxValue = 1_000;
    int minValue = -1_000;
    int boundedCounter = 0;
    
    public static void main(String[] args) {
        BoundedCounter boundedCounter = new BoundedCounter();
        PlusThread plusThread = new PlusThread(boundedCounter);
        MinusThread minusThread = new MinusThread(boundedCounter);
        
        plusThread.start();minusThread.start();
    }
    
    public synchronized void increment(){
        while (this.boundedCounter < this.maxValue) {
            this.boundedCounter++;
            
            System.out.println("Incrementing.. Current value: " + this.boundedCounter);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {}
            if (this.boundedCounter >= this.maxValue) {
                try {
                    notify();
                    wait();
                } catch (InterruptedException ex) {}
            }
        }
    }
    
    public synchronized void decrement(){
        
        while (this.boundedCounter > this.minValue) {
            this.boundedCounter--;
            System.out.println("Decrementing.. Current value: " + this.boundedCounter);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {}
            if (this.boundedCounter <= this.minValue) {
                try {
                    notify();
                    wait();
                } catch (InterruptedException ex) {}
            }
        }

    }
 
}


class PlusThread extends Thread{

    BoundedCounter thread;

    public PlusThread(BoundedCounter thread) {
        this.thread = thread;
    }
    
    @Override
    public void run(){
        this.thread.increment();
    }
}

class MinusThread extends Thread{
    BoundedCounter thread;

    public MinusThread(BoundedCounter thread) {
        this.thread = thread;
    }
    
    @Override
    public void run(){
        this.thread.decrement();
    }
}
