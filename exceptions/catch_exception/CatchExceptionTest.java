package catch_exception;

import java.io.*;

public class CatchExceptionTest {
    public static void main(String[] args) {
        SimpleFileCat sfc = new SimpleFileCat("short_text.txt");

        //If any code inside the try block throws an exception of the class
        //specified in the catch clause:
        //  1. The program skips the remainder of the code in try block.
        //  2. The program executes the handler code inside the catch clause.

        //If any code in a method throws an exception of a type other than
        //the one named in the catch clause, this method exits immediately.

        //If none of the code inside try block throws an exception, the program
        //skips the catch clause.
        try {
            sfc.cat();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SimpleFileCat {
    private String fileName;
    public SimpleFileCat(String name) {
        fileName = name;
    }
    //If an exception occurs, do nothing and simply pass the exception on to
    //the caller. If taking this approach, we have to advertise the exception.
    //The compiler enforces the throws specifiers. If a method called throws
    //a checked exception, we must either handle it or pass it on.
    //As a general rule, we should catch those we know how to handle and
    //propagate those we do not know how to handle.
    //One exception to this rule: A method that overrides a superclass method
    //which throws no exception, then we must catch each checked exception in
    //the method's code.
    public void cat() throws IOException {
        InputStream in = new FileInputStream(fileName);
        int b;
        while ((b = in.read()) != -1) {
            System.out.print((char)b);
        }
    }
}
