/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterromperThread;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class Main {
    
    public static void main(String argv[]){
        ThreadFactory thread1 = new ThreadFactory();
        ThreadFactory thread2 = new ThreadFactory();
        ThreadFactory thread3 = new ThreadFactory();
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
}

class ThreadFactory extends Thread{
    @Override
    public void run(){
        System.out.println("Hello! I'm running");
        Random random = new Random();
        int num = random.nextInt(10000) + 5000;
        System.out.println("Sleeping " + num);
        try {
            Thread.sleep(num);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hello. I woke up");
                
    }
}