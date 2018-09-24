/*
 * Exercício 4
 * Problema do Babeiro Dorminhoco
 * Na barbearia há um barbeiro, uma cadeira de barbeiro e n cadeiras para eventuais clientes esperarem a vez. 
 * Quando não há clientes, o barbeiro senta-se na cadeira de barbeiro e cai no sono. ]
 * Quando chega um cliente, ele precisa acordar o barbeiro. 
 * Se outros clientes chegarem enquanto o barbeiro estiver cortando o cabelo de um cliente,
 * eles se sentarão (se houver cadeiras vazias) ou sairão da barbearia (se todas as cadeiras estiverem ocupadas).
 */
package MonitorCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author yudi
 */
public class BarbeiroDorminhoco {
    int qtde_cadeiras = 5;
    boolean cadeiras[] = new boolean[qtde_cadeiras];
    boolean cuttingHair = false;
    List<Integer> arriveOrder = new ArrayList();

    public static void main(String[] args) {
        BarbeiroDorminhoco barbeiroDorminhoco = new BarbeiroDorminhoco();
        Customers customers = new Customers(barbeiroDorminhoco);
        Barbery barbery = new Barbery(barbeiroDorminhoco);
        customers.start();barbery.start();
    }
    
    private synchronized boolean barberyIsEmpty(){
        for (int i = 0; i < cadeiras.length; i++) {
            if (cadeiras[i] == true) {
                return false;
            }
        }
        
        return true;
    }
    
    private synchronized boolean barberyIsFull(){
        /* Verifica se a barbearia estiver cheia
        Retorna true se estiver cheia, false se vazia */
        
        for (int i = 0; i < this.cadeiras.length; i++) {
            if (cadeiras[i] == false) {
                return false;
            }
        }
        return true;
    }
    
    private int getAChair(){
        
        for (int i = 0; i < this.cadeiras.length; i++) {
            if (cadeiras[i] == false) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized void addClient(){
        if (!barberyIsFull()) {
            System.out.println("A client has arrived!");
            int chairToSit = getAChair();
            this.cadeiras[chairToSit] = true;
            arriveOrder.add(chairToSit);
            notify();
        }else{
            System.out.println("Client go out. Barbery is full");
        }
    }
    
    public synchronized void cutHair(){

        while (barberyIsEmpty()) {
            try {
                System.out.println("Barbery sleepeing.. No clients yet");
                wait();
            } catch (InterruptedException ex) {}
        }
        System.out.println("Barbery woke up. ");
        int nextClient = arriveOrder.get(0);
        arriveOrder.remove(0);
        System.out.println("Barbery now cutting hair of costumer id" + nextClient);
        cadeiras[nextClient] = false;
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {}
    }
    
}

class Customers extends Thread{
    BarbeiroDorminhoco thread;
    Random random = new Random();
    public Customers(BarbeiroDorminhoco thread) {
        this.thread = thread;
    }
    
    @Override
    public void run(){
        while (true) {
            thread.addClient();
            int arriveTime = random.nextInt(10000) + 500;
        }
    }
}

class Barbery extends Thread{
    BarbeiroDorminhoco thread;
    Random random = new Random();
    public Barbery(BarbeiroDorminhoco thread) {
        this.thread = thread;
    }
    
    public void run(){
        while (true) {
            thread.cutHair();
            int sleepTime = random.nextInt(5000) + 500;
        }
    }
    
}

