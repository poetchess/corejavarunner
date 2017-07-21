package forkJoin;

import java.util.concurrent.*;
import java.util.function.*;

// The fork-join framework is designed to support computationally intensive tasks.

/*
    Suppose we have a processing task that naturally decomposes into subtasks:

    if (problemSize < threshold) {
        solve problem directly
    }
    else {
        break problem into subproblems
        recursively solve each subproblem
        combine the results
    }
*/

// To put the recursive computation in a form that is usable by the framework,
// supply a class that extends RecursiveTask<T> (if the computation produces a
// result of type T) or RecursiveAction (if it doesn't produce a result).
// Override the 'compute' method to generate and invoke subtasks and to combine
// their results.

public class ForkJoinTest {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }
        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    protected Integer compute() {
        if ((to - from) < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.test(values[i]))
                    count++;
            }
            return count;
        }
        else {
            int mid = (from + to) / 2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);

            // the invokeAll method receives a number of tasks and blocks until
            // all of them have completed. The join method yields a result.
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}
