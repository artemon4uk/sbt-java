import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ContextImpl implements Context {
    private int totalTaskCount;
    private AtomicInteger completedTaskCount = new AtomicInteger(0);
    private AtomicInteger failedTaskCount = new AtomicInteger(0);
    private AtomicInteger interruptedTaskCount = new AtomicInteger(0);
    private AtomicBoolean isInterrupted = new AtomicBoolean(false);

    public ContextImpl(int totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount.get();
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount.get();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount.get();
    }

    @Override
    public void interrupt() {
        isInterrupted.set(true);
    }

    @Override
    public boolean isFinished() {
        return completedTaskCount.get() + failedTaskCount.get() + interruptedTaskCount.get() == totalTaskCount;
    }

    public boolean isInterrupted() {
        return isInterrupted.get();
    }

    public void increaseCompleteCount() {
        completedTaskCount.incrementAndGet();
    }

    public void increaseFailCount() {
        failedTaskCount.incrementAndGet();
    }

    public void increaseInterruptCount() {
        interruptedTaskCount.incrementAndGet();
    }
}
