
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String args[]) throws InterruptedException, ExecutionException {

        ThreadPoolImpl pool = new ThreadPoolImpl(10);
        pool.submit(() -> print());
        pool.submit(() -> print());
        pool.submit(() -> print());
        pool.submit(() -> print());
        pool.submit(() -> print());

        System.out.println("Active Thread: " + Thread.activeCount());

        final Future<Integer> submit = pool.submit(() -> expensiveMethod());
        final Integer integer = submit.get();
        System.out.println("integer found: " + integer);
        pool.shutdown();

    }

    private static int expensiveMethod() {
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        return 5;
    }

    private static void print() {
        System.out.println(Thread.currentThread().getName() + ": Hello World!");
    }
}