package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static void main() throws InterruptedException {
        System.out.println("=== Java Cache PoC ===");

        CacheService<String, String> caffeineCache = new CaffeineCacheService<>();
        CacheService<String, String> redisCache =
                new RedisCacheService<>("redis://localhost:6379", String.class);

        runBasicDemo("Caffeine", caffeineCache);
        runBasicDemo("Redis", redisCache);

        runConcurrencyTest("Caffeine", caffeineCache);
        runConcurrencyTest("Redis", redisCache);
    }

    private static void runBasicDemo(String name, CacheService<String, String> cache) {
        System.out.println("\n=== " + name + " Basic Demo ===");

        cache.clear();

        String value1 = cache.getOrLoad("key", () -> {
            System.out.println(name + " loading value...");
            sleep(100);
            return "value";
        });

        String value2 = cache.getOrLoad("key", () -> {
            System.out.println(name + " SHOULD NOT LOAD AGAIN");
            return "value";
        });

        System.out.println("First call: " + value1);
        System.out.println("Second call: " + value2);
    }

    private static void runConcurrencyTest(String name, CacheService<String, String> cache)
            throws InterruptedException {

        System.out.println("\n=== " + name + " Concurrency Test ===");

        cache.clear();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger counter = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(10);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                cache.getOrLoad("key", () -> {
                    int count = counter.incrementAndGet();
                    System.out.println(name + " loader called: " + count);
                    sleep(100);
                    return "value";
                });
                latch.countDown();
            });
        }

        latch.await();
        long end = System.currentTimeMillis();

        System.out.println(name + " loader executions: " + counter.get());
        System.out.println(name + " total time: " + (end - start) + "ms");

        executor.shutdown();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
