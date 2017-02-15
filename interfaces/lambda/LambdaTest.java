package lambda;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

//Lambda expression is simply a block of code, with the specification of any 
//variables that must be passed to the code.
public class LambdaTest {
    public static void main (String[] args) {
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length:");

        //If the parameter types of a lambda expression can be inferred, it 
        //can be omitted. The return type of a lambda expression is never 
        //specified, it is always inferred from context.
        Arrays.sort(planets, (first, second) -> 
            first.length() - second.length());

        System.out.println(Arrays.toString(planets));

        //A single parameter with inferred type, parentheses can be omitted.
        Timer t = new Timer(1000, event -> 
            System.out.println("The time is " + new Date()));
        t.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
