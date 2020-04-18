import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T res = null;
    private volatile RuntimeException exception = null;
    private volatile boolean started;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        this.started = false;
    }

    public T get() throws Exception {
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
            while (res == null && exception == null) {
                wait();
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
            notifyAll();
            return res;
        } catch (RuntimeException e) {
            exception = e;
            notifyAll();
            throw exception;
        }

    }
}
