/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadSafety;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a1711199
 */
public class PrimosThreadSafety {

    private int maxNum;
    private int minNum;
    private int currentNum;
    private List<Integer> list = new ArrayList<Integer>();

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public static void main(String[] args) {

    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }
}

class ThreadFactory extends Thread {

    PrimosThreadSafety primosThreadSafety = null;

    public ThreadFactory(PrimosThreadSafety primosThreadSafety) {
        this.primosThreadSafety = primosThreadSafety;
    }

    @Override
    public void run() {
        int currentNumber = 0;
        while (currentNumber < primosThreadSafety.getMaxNum()) {
            synchronized (this) {
                currentNumber = primosThreadSafety.getCurrentNum();
            }
            System.out.println("Current Number:"+ currentNumber +" running on Thread " + Thread.currentThread().getId());
            int divisibleCount = 0;

            for (int i = 0; i < currentNumber; i++) {
                if (currentNumber % 2 == 0) {
                    divisibleCount++;
                }
            }

            if (divisibleCount <= 2) {
                // É primo
                primosThreadSafety.getList().add(currentNumber);
            }
            primosThreadSafety.setCurrentNum(currentNumber++);

        }
    }
}
