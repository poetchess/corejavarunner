package sync_synchronized;

import java.util.*;

public class Bank {

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    // If a method is declared with the 'synchronized' keyword, the object's lock
    // protects the entire method. To call the method, a thread must acquire the
    // intrinsic object lock. The intrinsic object lock has a single associated
    // condition, which has wait() and notifyAll() methods.
    public synchronized void transfer(int from, int to, double amount) 
                         throws InterruptedException {

        while (accounts[from] < amount)
            wait();

        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

        notifyAll();
    }

    // It is also legal to declare static methods as synchronized. If such a method
    // is called, it acquires the intrinsic lock of the associate class object. As
    // a result, no other thread can call this or any other synchronized static
    // method of the same class.

    // The intrinsic locks and conditions have some limitations. Such as:
    //   We cannot interrupt a thread that is trying to acquire a lock.
    //   We cannot specify a timeout when trying to acquire a lock.
    //   Having a single condition per lock can be inefficient.

    // What should we use in the code ?
    // 1. use the mechanisms of the java.util.concurrent package.
    // 2. use the 'synchronized' keyword.
    // 3. use Lock/Condition

    public synchronized double getTotalBalance() {

        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    public int size() {

        return accounts.length;
    }
}   
