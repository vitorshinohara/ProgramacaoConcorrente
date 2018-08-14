/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DisputaThreads;

/**
 *
 * @author yudi
 */



class Counter{

    private int value;
    
    public void Counter(){
        this.value = 0;
    }
    
    public int getValue(){
        return this.value + 1;
    }
}

class ThreadFactory extends Thread {

    private final Counter counter;

    public ThreadFactory(Counter c) {
        this.counter = c;
    }

    @Override
    public void run() {
        while (true) {
            counter.getValue();
        }

    }
}
