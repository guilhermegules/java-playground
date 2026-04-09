package org.example;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FileTask implements Runnable {

  private final String filePath;
  private final Counter counter;

  public FileTask(String filePath, Counter counter) {
    this.filePath = filePath;
    this.counter = counter;
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    AtomicInteger localCounter = new AtomicInteger();
    System.out.printf("Processing %s on %s", filePath, threadName);

    try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
      while (reader.readLine() != null) {
        System.out.println(reader.readLine());
        localCounter.incrementAndGet();
      }
    } catch (IOException e) {
      System.out.println("Error reading file " + filePath);
    }

    counter.add(localCounter.get());

    System.out.printf("Finished %s %s - (%d)%n", filePath, threadName, localCounter.get());
  }
}
