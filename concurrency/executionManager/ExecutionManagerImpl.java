import java.util.ArrayList;

public class ExecutionManagerImpl implements ExecutionManager {

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ContextImpl context = new ContextImpl(tasks.length);
        ArrayList<Thread> threads = new ArrayList<>();

        Thread startThread = new Thread(() -> {
            for (Runnable task : tasks) {
                if (!context.isInterrupted()) {
                    Thread tmpThread = new Thread(task);
                    threads.add(tmpThread);
                    tmpThread.start();
                } else {
                    context.increaseInterruptCount();
                }
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                    context.increaseCompleteCount();
                } catch (Exception e) {
                    context.increaseFailCount();
                }
            }
            callback.run();
        });

        startThread.start();
        return context;
    }
}
