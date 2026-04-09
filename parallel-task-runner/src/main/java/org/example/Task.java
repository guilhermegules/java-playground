package org.example;

public class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.printf("Task %d started on %s%n", taskId, threadName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.printf("Task %d finished on %s%n", taskId, threadName);
    }
}
