//Topics covered:
//  1. Overloaded constructors
//  2. A call to another constructor with this(...) 
//  3. A no-argument constructor
//  4. An object initialization block
//  5. An instance field initializer
//  6. A static initialization block
//  7. A static field initializer

//What happens when a constructor is called:
//  1. All data fields are initialized to their default values. (0, false, null)
//  2. All field initializers and initialization blocks are executed, in the 
//     order they occur in the class declaration.
//  3. If the first line of the constrctor calls a second constructor, then the
//     body of the second constructor is executed.
//  4. The body of the constructor is executed.

import java.util.*;

public class ConstructorTest {
    
    public static void main(String[] args) {
        
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry", 40000);
        staff[1] = new Employee(60000);
        staff[2] = new Employee();

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + 
                    ",salary=" + e.getSalary());
    }
}

class Employee {

    //Like instance fields, static fields are 0, false or null by default.
    private static int nextId;

    private int id;

    //instance field initialization
    //Explicit field initializaton is carried out before constructor executes.
    //The initialization value can come from a method call. (Static method ?)
    private String name = "";

    private double salary;

    //Static initialization block
    //Static initialization occurs when the class is first loaded.
    static {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }

    //Object initialization block, runs before the constructor.
    //Recommend to place initialization blocks after field definitions.
    {
        id = nextId;
        nextId++;
    }

    //Signature of the method: method name + parameter types
    //Compiler must sort out which method to call, overloading resolution.
    public Employee(String n, double s) {
        name = n;
        salary = s;
    }

    //If a field is not explicitly set in the constructor, it will be set to
    //a default value: Numbers -> 0, boolean -> false, object variable -> null.
    //If the first statement of a constuctor has the form this(...), the 
    //constructor calls another constructor of the same class.
    public Employee(double s) {
        this("Employee #" + nextId, s);
    }

    //A no-argument constructor will be provided only when the class has no
    //other constructors. If we define a no-argument constructor ourselves,
    //compiler will not provide it.
    public Employee() {
        //name initialized to ""
        //salary not explicitly set, initialized to 0
        //id initialized in initialization block    
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public int getId()
    {
        return id;
    }
}
