package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger total = new AtomicInteger(0);

    public void add(int value) {
        total.addAndGet(value);
    }

    public int getTotal() {
        return total.get();
    }
}
