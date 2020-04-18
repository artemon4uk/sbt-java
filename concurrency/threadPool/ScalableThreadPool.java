import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final ArrayList<Thread> threads = new ArrayList<Thread>();
    private final int minThreadCount;
    private final int maxThreadCount;


    public ScalableThreadPool(int minThreadCount, int maxThreadCount) {
        this.minThreadCount = minThreadCount;
        this.maxThreadCount = maxThreadCount;
        for (int i = 0; i < minThreadCount; ++i) {
            threads.add(new Thread(this::run));
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (this) {
            tasks.add(runnable);
            if (!tasks.isEmpty() && threads.size() < maxThreadCount) {
                Thread thread = new Thread(this::run);
                threads.add(thread);
                thread.start();
            } else {
                notify();
            }
        }
    }

    private void run() {
        while (true) {
            Runnable task;
            synchronized (tasks) {
                while (tasks.isEmpty()) {
                    if (threads.size() > minThreadCount) {
                        threads.remove(Thread.currentThread());
                        return;
                    }
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                task = tasks.poll();
            }
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
