package exception;

//Java allows every method an alternative exit path if it is unable to complete
//its task in the normal way. In this situation, the method does not return a 
//value, instead it throws an object that encapsulates the error information.
//The method exits immediately and the execution does not resume at the code
//that called the method, instead, the exception handling mechanism begins its
//search for an exception handler that can deal with this particular error.

//Throwable -> Error & Exception
//Exception -> RuntimeException & Others
//Error: internal errors and resource exhausion situations inside Java runtime.
//RuntimeException: a bad cast, an out-of-bounds array access, a null pointer access.
//Others: such as an I/O error

//unchecked exception: exceptions that derive from Error or RuntimeException
//checked exception: all other exceptions. Compiler checks that exception
//  handlers for all checked exceptions are provided.

//An exception is thrown in any of the following four situations:
//  1. Call a method that throws a checked exception
//  2. Detect an error and throw a checked exception with the throw statement
//  3. Make a programming error that gives rise to an unchecked exception
//  4. An internal error occurs in the JVM or runtime library
//Need to tell the method user about the possibility of an exception.
//No need to advertise internal Java errors (exceptions inheriting from Error).
//Should not advertise unchecked exceptions inheriting from RuntimeException,
//  this kind of exception should be fixed in the first place.
//Sum up: A method must declare all the checked exceptions that it might throw,
//  otherwise there will be a compile-time error.

//If we override a method from a subclass, the checked exceptions that the 
//subclass method declares cannot be more general than those of the 
//superclass method. Therefore, if the superclass method throws no checked 
//exception at all, neither can the subclass.


public class ExceptionTest {
    public static void main(String[] args) {
        int num1, num2;
        try {
            num1 = 0;
            num2 = 1 / num1;
            System.out.println("Try block message");
        } catch (ArithmeticException e) {
            System.out.println("ErrorL Don't divide a number by zero");
        }
            
        System.out.println("Out of try-catch block");
    }
}
