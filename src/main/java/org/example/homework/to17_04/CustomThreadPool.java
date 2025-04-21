package org.example.homework.to17_04;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CustomThreadPool.
 *
 * @author Lina_Dautova
 */
public class CustomThreadPool {
    private final LinkedList<Runnable> tasks;
    private final List<Thread> threads;
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public CustomThreadPool(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Pool size must be greater than 0");
        }
        this.tasks = new LinkedList<>();
        this.threads = new LinkedList<>();
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(new CustomTask());
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        if (isShutdown.get()) {
            throw new IllegalArgumentException("Pool is shutdown");
        }
        synchronized (tasks) {
            tasks.addFirst(task);
            tasks.notifyAll();
        }
    }

    public void shutdown() {
        isShutdown.set(true);
        synchronized (tasks) {
            tasks.notifyAll();
        }
    }

    public void awaitTermination() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class CustomTask implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() || !tasks.isEmpty()) {
                Runnable task = null;
                synchronized (tasks) {
                    while (tasks.isEmpty() && !isShutdown.get()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (!tasks.isEmpty()) {
                        task = tasks.removeLast();
                    } else if (isShutdown.get()) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}