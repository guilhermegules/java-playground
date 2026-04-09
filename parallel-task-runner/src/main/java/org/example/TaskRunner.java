package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskRunner {

  public static void runSequential(List<Task> taskList) {
    long start = System.currentTimeMillis();

    for (Task task : taskList) {
      task.run();
    }

    long end = System.currentTimeMillis();

    System.out.println("Total execution time (sequential): " + (end - start) + "ms");
  }

  public static void runParallel(List<Task> taskList) {
    long status = System.currentTimeMillis();

    ExecutorService executor = Executors.newFixedThreadPool(4);

    try {
      for (Task task : taskList) {
        executor.submit(task);
      }

      executor.shutdown(); // Stop accept tasks

      boolean finished = executor.awaitTermination(10, TimeUnit.SECONDS);

      if (!finished) {
        System.out.println("Timeout reached.");
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      System.out.println("❌ Interrupted while waiting");
      executor.shutdownNow();
      Thread.currentThread().interrupt();
    }

    long end = System.currentTimeMillis();
    System.out.println("Total execution time (parallel): " + (end - status) + "ms");
  }
}
