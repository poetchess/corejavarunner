package sync_ReadWriteLock;

import java.util.*;
import java.util.concurrent.locks.*;

public class Bank {

    private final double[] accounts;
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock readLock = rwl.readLock();
    private Lock writeLock = rwl.writeLock();
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        sufficientFunds = writeLock.newCondition();
    }

    public void transfer(int from, int to, double amount) 
                         throws InterruptedException {

        writeLock.lock();
        try {
            while (accounts[from] < amount)
                sufficientFunds.await();

            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

            sufficientFunds.signalAll();
        }
        finally {
            writeLock.unlock();
        }
    }

    public double getTotalBalance() {

        readLock.lock();
        try {
            double sum = 0;
            for (double a : accounts)
                sum += a;

            return sum;
        }
        finally {
            readLock.unlock();
        }
    }

    public int size() {

        return accounts.length;
    }
}   
