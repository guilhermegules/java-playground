package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private final BlockingQueue<Task> queue;
    private final AtomicInteger counter;
    private final int totalTasks;
    private final int delayMs;

    public Consumer(BlockingQueue<Task> queue, AtomicInteger counter, int totalTasks, int delayMs) {
        this.queue = queue;
        this.counter = counter;
        this.totalTasks = totalTasks;
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = queue.take();
                processTask(task);
                counter.incrementAndGet();
                System.out.println("[CONSUMER] Processed: " + task + " | Queue size: " + queue.size());
                if (task.getId() == totalTasks) {
                    System.out.println("[CONSUMER] Finished consuming " + totalTasks + " tasks");
                    break;
                }
                Thread.sleep(delayMs);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(Task task) {
        System.out.println("[CONSUMER] Processing: " + task.getData());
    }
}
