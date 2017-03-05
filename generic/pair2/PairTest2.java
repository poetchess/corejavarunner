package pair2;

import java.time.*;

public class PairTest2 {
    public static void main(String[] args) {
        LocalDate[] birthdays = {
            LocalDate.of(1906, 12, 9),
            LocalDate.of(1815, 12, 10),
            LocalDate.of(1903, 12, 3),
            LocalDate.of(1910, 6, 22),
    };
    Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
    System.out.println("min = " + mm.getFirst());
    System.out.println("max = " + mm.getSecond());
    }
}

class ArrayAlg {
    //Place restrictions on type variables.
    //Violate the restriction will lead to compile time error.
    //We can have many interface supertypes, but at most one of the bounds can 
    //be a class. If there's a class as a bound, it must be the first one in
    //the bounds list.
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }
        return new Pair<>(min, max);

    }
}
