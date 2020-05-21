import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T res = null;
    private volatile RuntimeException exception = null;
    private volatile boolean started;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        this.started = false;
    }

    public T get() throws RuntimeException {
        if (res != null) {
            return res;
        }
        if (exception != null) {
            throw exception;
        }

        boolean firstChanged = false;
        synchronized (this) {
            if (!started) {
                started = true;
                firstChanged = true;
            }
        }
        if (!firstChanged) {
            synchronized (this) {
                while (res == null && exception == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (res != null) {
            return res;
        }
        if (exception != null) {
            throw exception;
        }

        try {
            res = callable.call();
            synchronized (this) {
                notifyAll();
            }
            return res;
        } catch (Exception e) {
            exception = new RuntimeException(e);
            synchronized (this) {
                notifyAll();
            }
            throw exception;
        }
    }
}
