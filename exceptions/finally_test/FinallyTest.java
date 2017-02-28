package finally_test;

class myException extends Exception {
}

//code in the finally clause executes whether or not an exception was caught.
//A finally clause can yield unexpected results when it contains return 
//statements. It may masks the original return value.
//If the cleanup method can also throw an exception, the finally clause will
//make some trouble. i.e. The original exception is lost and the exception of
//the cleanup code is thrown instead. This can be fixed using try-with-resource
//statement.
public class FinallyTest {
    static int count = 0;
    public static void main(String [] args) {
        while (true) {
            try {
                if (count++ == 0) {
                    throw new myException();
                }
                System.out.println("No exception");
            } catch(myException e) {
                System.out.println("myException");
            } finally {
                System.out.println("In finally clause: count = " + count);
                if (count == 2) break;
            }
        }
    }
}
