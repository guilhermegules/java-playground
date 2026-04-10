package org.example;

public class Main {
    static void main() {
        AggregatorService service = new AggregatorService();

        long start = System.currentTimeMillis();

        AggregatedResponse response = service.getUserAggregatedResponse(1L).join();

        long end = System.currentTimeMillis();

        System.out.println("Result: " + response);
        System.out.println("Time: " + (end - start) + "ms");
    }
}
