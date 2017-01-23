import java.time.*;

//Name of the source file must match the name of the public class.
//There is only one public class in a source file.

public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        for (Employee e : staff) {
            e.raiseSalary(5);
        }

        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ",salary=" + 
                    e.getSalary() + ",hireDay=" + e.getHireDay());
        }
    }
}

class Employee {

    //private: Only methods of this class can access these instance fields.
    //final: Must be initialized when the object is constructed, and may not
    //       be modified again. This key word is useful for primitive or 
    //       immutable class type. For mutable class type, it is confuing
    //       since the object variable is immutable (declared as final) but the
    //       object being referenced to can be modified.
    //immutable class: None of the methods ever mutate its objects.
    private final String name;          //reference to String object
    private double salary;
    private LocalDate hireDay;          //reference to LocalDate object

    //public: any method in any class can call the method
    //A constructor can only be called with 'new' operator.
    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    //All methods are defined inside the class itself, does not mean inline.
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    //Since 'hireDay' is declared as private, and we have no hireDay mutator
    //methods, this instance field will never be corrupted.
    public LocalDate getHireDay() {
        return hireDay;
    }

    //There is an implicit parameter: 'this'
    //It refers to the object that appears before the method name.
    //'byPercent' is called explicit parameter.
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    //Providing accessors and mutators has some benifits:
    //  Internal changes will not break related external parts.
    //  Mutators have the chance to perform error checking while single 
    //      assignment cannot.
    //Accessor returning a reference to a mutable object should be avoided
    //since code outside the class now has direct access to the instance 
    //field which violates the encapsulation rule.
    //Instead, clone() first and return the reference to the cloned object.


    //Class-based access privileges:
    //  A method can access the private fields of any object of its class,
    //  not just of the implicit parameter.
    public boolean equals(Employee other) {
        return name.equals(other.name);
    }
}
