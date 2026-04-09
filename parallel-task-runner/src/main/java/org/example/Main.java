package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
  static void main() {
    List<Task> tasks = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      tasks.add(new Task(i));
    }

    System.out.println("=== Running Sequential ===");
    TaskRunner.runSequential(tasks);

    System.out.println("\n=== Running Parallel ===");
    TaskRunner.runParallel(tasks);

    List<String> files = List.of(
            "file1.txt",
            "file2.txt",
            "file3.txt",
            "file4.txt"
    );

    FileTaskRunner.runParallel(files);
  }
}
