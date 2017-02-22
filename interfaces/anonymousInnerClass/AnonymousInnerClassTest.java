package anonymousInnerClass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

//One step further from local inner class:
//If a single object is needed, we don't need to give the class a name,
//such a class is called an anonymous inner class.
class TalkingClock {
    public void start(int interval, boolean beep) {

        //Create a new object of a class that implements ActionListener 
        //interface, where the required method is defined inside {}.
        //The syntax is:
        // new SuperType(construction parameters)
        // {
        //     inner class methods and data
        // }
        //SuperType can be an interface or a class.

        //An anonymous inner class cannot have constructors because the
        //class has no name. The construction parameters are given to the
        //superclass constructor. When an inner class implements an interface,
        //it cannot have any construction parameters.

        //The closing parenthesis of the construction parameter lists is 
        //followed by an opening brace, an anonymous inner class is defined.
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("At the tone, the time is " + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };

        Timer t = new Timer(interval, listener);
        t.start();
    }
}
