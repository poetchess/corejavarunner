package interfaces;

//An interface is a set of requirements for the classes.
//Interfaces never have instance fields.

//Interface are not classes, cannot use 'new' operator to instantiate 
//an interface. Comparable x; x = new Employee(...); is OK if Employee
//implements Comparable.

//Static methods and default methods are allowed in interfaces.
//Default methods must be tagged as 'default', and are useful in
//interface evolution.

//Resolving deafult method confilicts:
//  1. Superclasses win. Redefining one of the methods in 
//     'Object' class as a default method never succeeds.
//  2. Interfaces clash. 

//Declaring the class to implement the given interface
//Supplying definitions for all methods in the interface
//Classes can implement multiple interfaces.
public class Employee implements Comparable<Employee> {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    //All methods in an interface are automatically public.
    //All fields are always public static final.
    //When implementing the interface, we must declare the method as public.
    public int compareTo(Employee other) {
        return Double.compare(salary, other.salary);
    }
}
