package org.example;

import java.util.concurrent.CompletableFuture;

public class UserService {

    public CompletableFuture<User> fetchUser(Long userId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000); // simulate API delay
            System.out.println("Fetched user");
            return new User(userId, "Guilherme");
        });
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
