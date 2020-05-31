import java.util.concurrent.*;

public class ThreadPoolImpl implements ThreadPool {

    private static final int MAX_ALLOWED_THREAD = 20;

    private BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    private ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");
    private volatile boolean running = true;

    public ThreadPoolImpl(int threadCount) {
        if(threadCount < 0 || threadCount > MAX_ALLOWED_THREAD) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < threadCount; i++) {
            String threadName = "Worker " + i;
            Worker worker = new Worker(threadGroup, threadName);
            worker.start();
        }
    }

    @Override
    public void submit(Runnable job) {
        blockingQueue.add(job);
    }

    @Override
    public <T> Future<T> submit(Callable<T> job) {
        final FutureTask<T> futureTask = new FutureTask<>(job);
        blockingQueue.add(futureTask);
        return futureTask;
    }

    private class FutureTask<T> implements Runnable, Future<T> {

        private Callable<T> callable;

        private volatile boolean isCancelled;
        private volatile boolean isDone;
        private volatile T t;

        public FutureTask(Callable<T> callable) {
            System.out.println("FutureTask constructor");
            this.callable = callable;
        }

        @Override
        public void run() {
            System.out.println("Run called");
            try {
                t = this.callable.call();
            } catch (Exception e) {
                this.isCancelled = true;
                e.printStackTrace();
            }
            isDone = true;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            if(isDone) return false;
            if(!mayInterruptIfRunning) {
                while (!isDone);
            }
            Thread.currentThread().interrupt();
            return true;
        }

        @Override
        public boolean isCancelled() {
            return isCancelled;
        }

        @Override
        public boolean isDone() {
            return isDone;
        }

        @Override
        public T get() throws InterruptedException, ExecutionException {
            while (!isDone);
            return t;
        }

        @Override
        public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }

    @Override
    public void shutdown() {
        this.running = false;
        this.threadGroup.interrupt();
    }

    private Runnable take() throws InterruptedException {
        return blockingQueue.take();
    }

    private class Worker extends Thread {

        public Worker(ThreadGroup threadGroup, String threadName) {
            super(threadGroup, threadName);
        }

        @Override
        public void run() {
            while (running && !interrupted()) {
                try {
                    Runnable job = take();
                    job.run();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }
}
