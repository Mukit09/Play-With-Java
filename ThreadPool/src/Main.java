import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws InterruptedException, ExecutionException {

        ThreadPoolImpl pool = new ThreadPoolImpl(5);
        pool.submit(() -> print());
        pool.submit(() -> print());
        pool.submit(() -> print());
        pool.submit(() -> print());
    //    pool.submit(() -> print());

       // TimeUnit.SECONDS.sleep(5);
      //  System.out.println("Active Thread: " + Thread.activeCount());

    //    TimeUnit.SECONDS.sleep(5);
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