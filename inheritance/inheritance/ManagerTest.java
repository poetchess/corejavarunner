package inheritance;

public class ManagerTest {

    public static void main(String[] args) {

        Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        //We can cast only within an inheritance hierarchy.
        //Use 'instanceof' before casting from a superclass to a subclass.

        //Array of subclass references can be converted to array of superclass
        //references without a cast. But array will remember the element type 
        //and ensure only compatible references are stored into it, if violated
        //ArrayStoreExecption will be thrown.

        Employee[] staff = new Employee[3];

        staff[0] = boss;
        //staff[0].setBonus is an error, compiler considers staff[0] only
        //as a reference to an Employee object, which has no mehtod called
        //setBonus.

        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        //Polymorphism: an object variable can refer to multiple actual types.
        //Dynamic binding: automatically selecting the appropriate method at runtime

        //Declared type of e: Employee
        //Actual type of the object: either Employee or Manager.

        //Method calls process: x.f(args), declared type of x is Class C
        //  1. Compiler looks at the declared type of the object and the 
        //     method name. It will enumerate all methods called 'f' in class 
        //     C and all accessible methods called 'f' in superclass of C. Now
        //     it knows all possible candidates for the method to be called.
        //  2. Compiler determines the type of the arguments that are supplied
        //     in the method call and perform overloading resolution. If it 
        //     cannot find any or it finds multiple matchingmethods, it reports
        //     an error. Now it knows the name and parameter type of the method
        //     that needs to be called.
        //  3. If the method is private, static, final, or a constructor, then
        //     the compiler knows exactly which method to call: Static binding.
        //     Otherwise, the method to be called depends on the actual type of
        //     the implicit parameter, and dynamic binding must be used at 
        //     runtime.
        //  4. When the program runs and uses dynamic binding to call a method,
        //     the VM must call the version of the method that is appropriate
        //     for the actual type of the object to which x refers.
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
        }
    }
}
