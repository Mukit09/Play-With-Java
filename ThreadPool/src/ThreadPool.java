import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ThreadPool {
    void submit(Runnable job);
    <T> Future<T> submit(Callable<T> job);
    void shutdown();
}
