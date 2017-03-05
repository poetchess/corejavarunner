package pair2;

//A generic class is a class with one or more type variables.
//'T' here is called a type variable.
//Type variables are used through the class definition to specify method return
//types and the types of fields and local variables.
//By substituting types for the type variables, we instantiate the generic type
//Generic class acts as a factory for ordinary classes.
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }
}
