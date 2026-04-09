package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileTaskRunner {

    public static void runParallel(List<String> files) {
        long start = System.currentTimeMillis();

        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            for (String file : files) {
                executor.submit(new FileTask(file, counter));
            }

            executor.shutdown();

            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }

        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long end = System.currentTimeMillis();

        System.out.println("📊 Total lines: " + counter.getTotal());
        System.out.println("Time: " + (end - start) + "ms");
    }
}
