/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class Productor extends Thread {
    
    Monitor monitor;

    public Productor(Monitor monitor) {
        this.monitor = monitor;
    }
    
    @Override
    public void run(){
        while (true) {
            
            System.out.println("[Productor] Producing...");
            monitor.produce();
            
            try {
                sleep(300);
            } catch (InterruptedException ex) {}
        }
    }
    
}
