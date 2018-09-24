/*
 * Implemente uma solu¸c˜ao com monitor para o problema do
 * Produtor-Consumidor usando um buffer circular..
 */
package Monitor;

/**
 *
 * @author yudi
 */
public class Monitor {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        
        Productor productor = new Productor(monitor);
        Consumer consumer = new Consumer(monitor);
        
        productor.start();consumer.start();
    }
    
    boolean bufferCircular[] = new boolean[5];
    private int end = 0;
    private int first = 0;

    public synchronized void produce() {
        
        while (end != first) { // Buffer cheio
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        
        end = (end + 1) % bufferCircular.length;
        bufferCircular[end] = true;
        notify();
    }
    
    public synchronized void consume(){
        while (end == first) {
            try {
                wait();
            } catch (InterruptedException ex) {}   
        }
        
        first = (first + 1) % bufferCircular.length;
        bufferCircular[first] = false;
        notify();
    }
    
    

}
