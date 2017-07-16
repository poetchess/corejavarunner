package sync_Lock_Condition;

import java.util.*;
import java.util.concurrent.locks.*;


// Often, a thread enters a critical section only to discover that it can't
// proceed until a condition is fulfilled. We can use a condition object to
// manage threads that have acquired a lock but cannot do useful work.

// A thread can only call await(), signalAll(), or signal() on a condition if
// it owns the lock of the condition.
public class Bank {

    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) 
                         throws InterruptedException {

        bankLock.lock();
        try {
            // if we busy waiting the condition to be fulfilled rather than
            // using the condition object, we will never proceed since other
            // threads need to acquire the lock first before they can transfer
            // money to the current account.

            // A lock object can have one or more associated condition objects.
            // A condition object can be obtained with newCondition() method.
            // Once we call await(), the current thread is now deactived and
            //   gives up the lock. This lets in another thread that can,
            //   hopefully, increase the account balance.

            // There is an essential difference between a thread that is waiting
            // to acquire a lock and a thread that has called await. Once a
            // thread calls the await method, it enters a wait set for that
            // condition. The thread is not made runnable when the lock is
            // available. Instead, it stays deactived until another thread has
            // called the signalAll method on the same condition.
            while (accounts[from] < amount)
                sufficientFunds.await();

            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

            // This call reactives all threads waiting for the condition. When
            // the threads are removed from the wait set, they are again
            // runnable and the scheduler will eventually active them again. At
            // that time, they will attemp to reenter the object. As soon as the
            // lock is available, one of them will acquire the lock and continue
            // where it left off, returning from the call to await. At this
            // time, the thread should test the condition again. There is no
            // guarantee that the condition is now fulfilled, signalAll() merely
            // signals to the waiting threads that is may be fulfilled at this
            // time and that is worth checking for the condition again.

            // It is crucially important that 'some' other thread calls the
            // signalAll method eventually. When a thread calls await(), it has
            // no way of reactiving itself. If no other threads bother to
            // reactive the waiting thread, it will never run again.

            // Whenever the state of an object changes in a way that might be
            // advantageous to waiting threads, we should call signalAll().
            sufficientFunds.signalAll();
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
