package org.example;

import java.util.List;

public record AggregatedResponse(User user, List<Order> orders) {
}
