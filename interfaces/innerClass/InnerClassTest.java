package innerClass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock {

    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }

    //Inner class can be declared as 'private', then only the outer class 
    //methods can construct inner class objects.
    //Only inner class can be private. Regular class always has either package
    //or public visibility.
    public class TimePrinter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + new Date());
            //An inner class method can access both its own data fields and
            //those of the outer object that creating it.
            //An inner class object always gets an implicit reference to the
            //outer object. This reference is set in the inner class
            //constructor.
            if (beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
