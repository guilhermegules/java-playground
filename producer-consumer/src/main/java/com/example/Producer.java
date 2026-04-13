package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private final BlockingQueue<Task> queue;
    private final AtomicInteger counter;
    private final int totalTasks;
    private final int delayMs;

    public Producer(BlockingQueue<Task> queue, AtomicInteger counter, int totalTasks, int delayMs) {
        this.queue = queue;
        this.counter = counter;
        this.totalTasks = totalTasks;
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        try {
            while (counter.get() < totalTasks) {
                Task task = new Task(counter.incrementAndGet());
                queue.put(task);
                System.out.println("[PRODUCER] Added: " + task + " | Queue size: " + queue.size());
                Thread.sleep(delayMs);
            }
            System.out.println("[PRODUCER] Finished producing " + totalTasks + " tasks");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
