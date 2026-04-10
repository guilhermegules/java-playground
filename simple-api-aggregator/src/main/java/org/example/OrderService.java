package org.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrderService {

    public CompletableFuture<List<Order>> fetchOrders(Long userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1500); // slower API
            System.out.println("Fetched orders");
            return List.of(
                    new Order(1L, "Notebook"),
                    new Order(2L, "Mouse")
            );
        });
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
