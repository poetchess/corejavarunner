package inheritance;


//keyword 'extends' denotes inheritance
//All inheritance in Java is public inheritance.
public class Manager extends Employee {

    private double bonus;

    //Every Manager object has four fields:
    //'name', 'salary' and 'hireDay' are taken from superclass.

    //Call to super() must be the 1st statement in the constructor.
    //If subclass does not call a superclass constructor explicitly, the
    //no-argument constructor of the superclass is invoked, in this case,
    //if the no-argument constructor is missing in the superclass, it will be
    //a compile time error.
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    //getName() and getHireDay() are automatically inherited from superclass.
    
    //override the superclass method
    //Use keyword 'super' to direct the compiler to call superclass method.
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }
}
