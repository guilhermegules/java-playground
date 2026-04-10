package org.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AggregatorService {
    private final UserService userService = new UserService();
    private final OrderService orderService = new OrderService();

    public CompletableFuture<AggregatedResponse> getUserAggregatedResponse(Long userId) {
       CompletableFuture<User> user = userService.fetchUser(userId);
       CompletableFuture<List<Order>> orders = orderService.fetchOrders(userId);

       return CompletableFuture.allOf(user, orders)
               .thenApply(v -> new AggregatedResponse(user.join(), orders.join()));
    }
}
