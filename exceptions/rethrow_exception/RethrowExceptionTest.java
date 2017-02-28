package rethrow_exception;

class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}

class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}

public class RethrowExceptionTest {

    public static void f() throws OneException {
        System.out.println("originating from the exception in f()");
        throw new OneException("throw from f()");
    }

    //We can throw an exception in a catch clause.
    //The original exception can be set as the cause of the new exception
    //using the initCause() method of class Throwable.
    //This wrapping technique is recommended since it allows to throw high
    //level exceptions in subsystems without losing the details of the
    //original failure. It is also useful if a checked exception occurs in
    //a method that is not allowed to throw a checked exception. We can 
    //catch the checked exception and wrap it into a runtime exception.
    public static void main(String[] args) throws TwoException {
        try {
            f();
        } catch(OneException e) {
            System.err.println("Caught in main, e.printStackTrace()");
            e.printStackTrace();
            Throwable se = new TwoException("from main");
            se.initCause(e);
            throw (TwoException)se;
        }
    }
}
