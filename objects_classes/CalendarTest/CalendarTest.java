import java.time.*;

public class CalendarTest {

    public static void main(String[] args) {
        
        //date: object variable, referring to the newly constructed object of
        //type LocalDate.
        //The object variable must be initialized first,
        //otherwise, invoking methods on it will cause a compile-time error.
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1);
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");

        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }

        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            //plusDays method does not mutate the object on which it is invoked.
            //It yields a new LocalDate object.
            //Accessor methods: methods that only access objects without
            //modifying them. 
            //Otherwise, it is a mutator.
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}
