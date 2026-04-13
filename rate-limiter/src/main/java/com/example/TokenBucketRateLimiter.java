package com.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucketRateLimiter {
    private final int maxTokens;
    private final Semaphore semaphore;
    private final AtomicInteger availableTokens;
    private final long refillIntervalNanos;

    public TokenBucketRateLimiter(int maxTokens, int refillRatePerSecond) {
        this.maxTokens = maxTokens;
        this.availableTokens = new AtomicInteger(maxTokens);
        this.semaphore = new Semaphore(maxTokens, true);
        this.refillIntervalNanos = 1_000_000_000L / refillRatePerSecond;
        
        startRefillTask();
    }

    private void startRefillTask() {
        Thread refillThread = new Thread(() -> {
            long lastRefillTime = System.nanoTime();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    long now = System.nanoTime();
                    long elapsed = now - lastRefillTime;
                    
                    if (elapsed >= refillIntervalNanos) {
                        int current = availableTokens.get();
                        if (current < maxTokens) {
                            int tokensToAdd = (int) (elapsed / refillIntervalNanos);
                            tokensToAdd = Math.min(tokensToAdd, maxTokens - current);
                            
                            if (tokensToAdd > 0) {
                                availableTokens.addAndGet(tokensToAdd);
                                semaphore.release(tokensToAdd);
                            }
                        }
                        lastRefillTime = now;
                    }
                    
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        refillThread.setDaemon(true);
        refillThread.start();
    }

    public boolean tryAcquire() {
        int current = availableTokens.get();
        if (current <= 0) {
            return false;
        }
        if (!semaphore.tryAcquire()) {
            return false;
        }
        availableTokens.decrementAndGet();
        return true;
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException {
        if (availableTokens.get() <= 0) {
            return false;
        }
        if (!semaphore.tryAcquire(timeout, unit)) {
            return false;
        }
        availableTokens.decrementAndGet();
        return true;
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
        availableTokens.decrementAndGet();
    }

    public int getAvailableTokens() {
        return availableTokens.get();
    }

    public int getMaxTokens() {
        return maxTokens;
    }
}
