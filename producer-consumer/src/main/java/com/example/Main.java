package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    
    private static final int QUEUE_CAPACITY = 10;
    private static final int TOTAL_TASKS = 20;
    private static final int PRODUCER_DELAY_MS = 100;
    private static final int CONSUMER_DELAY_MS = 150;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Producer Consumer\n");

        BlockingQueue<Task> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
        AtomicInteger tasksProduced = new AtomicInteger(0);
        AtomicInteger tasksConsumed = new AtomicInteger(0);

        Thread producer = new Thread(new Producer(queue, tasksProduced, TOTAL_TASKS, PRODUCER_DELAY_MS));
        Thread consumer = new Thread(new Consumer(queue, tasksConsumed, TOTAL_TASKS, CONSUMER_DELAY_MS));

        long startTime = System.currentTimeMillis();

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        long endTime = System.currentTimeMillis();

        System.out.println("\nRunning");
        System.out.println("Tasks produced: " + tasksProduced.get());
        System.out.println("Tasks consumed: " + tasksConsumed.get());
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
        System.out.println("Queue remaining capacity: " + queue.remainingCapacity());
    }
}
