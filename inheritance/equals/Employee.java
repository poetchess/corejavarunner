package equals;

import java.time.*;
import java.util.Objects;

public class Employee {

    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    //Every class in Java extends 'Object'.
    //A variable of type 'Object' can refer to objects of any type.
    //Only values of primitive types are not objects.

    //Writing the perfect 'equals' method:
    //1.  Name the explicit parameter 'otherObject'
    //2.  Test whether 'this' happens to be identical to 'otherObject'
    //3.  Test whether 'otherObject' is null and return false if it is.
    //4.  Compare the classes of 'this' and 'otherObject':
    //      If the sementics of 'equals' can change in subclass, use getClass()
    //      If the same semantics holds for all subclass, can use instanceof()
    //5.  Cast 'otherObject' to a variable of the class type
    //6.  Compare the fields.
    //      Use '==' for primitive type fields
    //      Use Objects.equals for object fields
    //      Use Arrays.equals for fields of array type.
    //      Return true or false.
    //7.  If redefining 'equals' in a subclass, include a call to 
    //      super.equals(other)

    //Make sure to override the method in class Object.
    @Override
    public boolean equals(Object otherObject) {
        //quick test to see if the objects are identical
        if (this == otherObject)
            return true;

        //must return false if the explicit parameter is null
        if (otherObject == null)
            return false;

        //if the classes don't match, they can't be equal
        if (getClass() != otherObject.getClass())
            return false;

        //otherObject is a non-null Employee
        Employee other = (Employee)otherObject;

        return Objects.equals(name, other.name) 
            && salary == other.salary 
            && Objects.equals(hireDay, other.hireDay);

    }

    //If equals method is redefined, hashCode method shoud also be redefined.
    //Definiton of equals and hashCode must be compatible:
    //  If x.equals(y) is true, x.hashCode() and y.hashCode() must be same.
    @Override
    public int hashCode() {
        //Objects.hash method will call Objects.hashCode for each arguments.
        return Objects.hash(name, salary, hireDay);
    }

    //Return a string representing the value of the object.
    //When an object is concatenated with a string by '+', Java compiler will
    //invoke toString method.
    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name + ", salary=" +
            salary + ", hireDay=" + hireDay + "]";
    }
}
