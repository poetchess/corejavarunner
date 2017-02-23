package staticInnerClass;

public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++) {
            d[i] = 100 * Math.random();
        }

        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayAlg {

    //Suppress the generation of the reference to outer class object by 
    //declaring the inner class 'static'
    //Only inner class can be declared static.
    //Use a static inner class whenever the inner class does not need to 
    //access an outer class object.
    public static class Pair {

        private double first;
        private double second;

        public Pair (double f, double s) {
            first = f;
            second = s;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    //Since this is a static method, inner class 'Pair' must be declared static.
    //Otherwise, when constructing the inner class object,  the compiler will
    //complain that there is no implicit object of type 'ArrayAlg' available to
    //initialize the inner class object.
    public static Pair minmax(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
