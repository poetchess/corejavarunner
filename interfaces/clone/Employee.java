package clone;

import java.util.Date;
import java.util.GregorianCalendar;

//The 'Cloneable' interface does not specify the 'clone' method, that method is
//inherited from the 'Object' class.
//'Cloneable' interface is called tagging interface, which has no methods.
public class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
    }

    //'clone' is a protected method of 'Object' class, it can only make a 
    //field-to-field copy (shallow copy). 'Employee' class cannot simply 
    //call this protected method to clone Employee objects.

    //If the subobject shared b/w original and the shallow clone is immutable,
    //then the sharing is safe. This will happen when:
    //  1. The subobject belongs to an immutable class, such as 'String'
    //  2. The subobject remains constant throughout the lifetime of the 
    //     object, with no mutators touching it and no methods yielding a
    //     reference to it.
    //When subobjects are mutable, we must redefine the 'clone' method to make
    //a deep copy that clones the subobjects as well.
    public Employee clone() throws CloneNotSupportedException {
        //call Object.clone()
        Employee cloned = (Employee)super.clone();

        //clone mutable fields
        cloned.hireDay = (Date)hireDay.clone();

        return cloned;
    }

    public void setHireDay(int year, int month, int day) {
        Date newHireDay = new GregorianCalendar(year, month - 1, day)
            .getTime();

        //instance field mutation
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString() {
        return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" 
            + hireDay + "]";
    }
}
