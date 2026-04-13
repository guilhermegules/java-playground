package com.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    
    private static final int MAX_TOKENS = 5;
    private static final int REFILL_RATE = 2;
    private static final int TOTAL_REQUESTS = 20;
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Rate Limiter\n");
        System.out.println("Max tokens: " + MAX_TOKENS);
        System.out.println("Refill rate: " + REFILL_RATE + " tokens/sec");
        System.out.println("Threads: " + NUM_THREADS);
        System.out.println("Total requests: " + TOTAL_REQUESTS + "\n");

        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(MAX_TOKENS, REFILL_RATE);
        
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(NUM_THREADS);
        
        AtomicInteger allowedRequests = new AtomicInteger(0);
        AtomicInteger rejectedRequests = new AtomicInteger(0);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i + 1;
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < TOTAL_REQUESTS / NUM_THREADS; j++) {
                        if (rateLimiter.tryAcquire()) {
                            allowedRequests.incrementAndGet();
                            System.out.printf("[THREAD-%d] Request #%d ALLOWED | Tokens: %d%n", 
                                threadId, j + 1, rateLimiter.getAvailableTokens());
                        } else {
                            rejectedRequests.incrementAndGet();
                            System.out.printf("[THREAD-%d] Request #%d REJECTED | Tokens: %d%n", 
                                threadId, j + 1, rateLimiter.getAvailableTokens());
                        }
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }
        
        startLatch.countDown();
        endLatch.await();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Allowed requests: " + allowedRequests.get());
        System.out.println("Rejected requests: " + rejectedRequests.get());
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
    }
}
