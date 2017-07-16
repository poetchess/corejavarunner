package sync_ReentrantLock;

import java.util.*;
import java.util.concurrent.locks.*;

/*
The basic outline for protecting a code block with a ReentrantLock is:

myLock.lock(); //a ReentrantLock object
try {
    critical section
}
finally {
    //make sure the lock is unlocked even if an exception is thrown.
    //but, in this case, the object may be in a damaged state.
    myLock.unlock(); 
}
*/

public class Bank {

    // ReentrantLock implements the Lock interface.
    // Each Bank object has its own ReentrantLock object. So if two threads
    //   access different Bank objects, each thread acquires a different lock
    //   and neither thread is blocked.
    // The lock is called reentrant because a thread can repeatly acquire a lock
    //   that it already owns. The thread has to call unlock() for every call to
    //   lock() in order to relinquish the lock.
    // In general, we should protect blocks of code that update or inspect a
    //   shared object, so we can be assured that these operations run to
    //   completion before another thread can use the same object.
    private Lock bankLock = new ReentrantLock();

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {

        bankLock.lock();

        try {

            if (accounts[from] < amount)
                return;        

            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        }
        finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {

        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts)
                sum += a;

            return sum;
        }
        finally {
            bankLock.unlock();
        }
    }

    public int size() {

        return accounts.length;
    }
}   
