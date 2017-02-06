package enums;

import java.util.*;

public class EnumTest {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.getAbbreviation());
        if (size == Size.EXTRA_LARGE) {
            System.out.println("Good job--you paid attention to the _.");
        }
    }
}

//The type defined by this declaration is a class.
//All enumerated types are subclasses of 'Enum' class.
enum Size {

    //The class has exactly four instances.
    //Simply use '==' to compare values of enumerated types.
    //The constructor will only be invoked when the enumerated constants are
    //constructed.
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    private String abbreviation;
}
