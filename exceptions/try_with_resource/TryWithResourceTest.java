package try_with_resource;

import java.io.*;

public class TryWithResourceTest {
    static String readFirstLineFromFile(String path) throws IOException {
        //When the try block exits, resource is released automatically.
        //Resource will be released when the block exits normally or 
        //when there was an exception.

        //A try-with-resource statement can have catch clauses and a finally 
        //clause. These are executed after closing the resources.
        try (BufferedReader br =
                new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("First line of file: " + readFirstLineFromFile(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
