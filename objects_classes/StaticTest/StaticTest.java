public class StaticTest {

    public static void main(String[] args) {

        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Tom", 40000);
        staff[1] = new Employee("Dick", 60000);
        staff[2] = new Employee("Harry", 65000);

        for (Employee e : staff) {
            e.setId();
            System.out.println("name=" + e.getName() + ",id=" + 
                    e.getId() + ",salary=" + e.getSalary    ());
        }

        int n = Employee.getNextId();   // calls static method
        System.out.println("Next available id=" + n);
    }
}

class Employee {

    //static field: There is only one such field per class, it belongs to the
    //              class, not to any individual object.
    private static int nextId = 1;

    private final String name;
    private double salary;
    private int id;

    public Employee(String n, double s) {
        name = n;
        salary = s;
        id = 0;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = nextId;
        nextId++;
    }

    //static method: Methods that do not operate on objects, have no implicit
    //               parameter. They can access static fields.
    //Recommend using class names, not objects, to invoke static methods.
    public static int getNextId() {
        return nextId;
    }

    //unit test
    public static void main(String[] args) {
        Employee e = new Employee("Harry", 50000);
        System.out.println(e.getName() + " " + e.getSalary());
    }
}
