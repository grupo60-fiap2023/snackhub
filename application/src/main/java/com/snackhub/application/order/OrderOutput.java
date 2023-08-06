package com.snackhub.application.order;

import com.snackhub.domain.order.Order;
import com.snackhub.domain.order.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderOutput(String id,
                          String ticket,
                          List<OrderItemOutput> items,
                          String observation,
                          OrderStatus status,
                          Instant creationDate) {

    public static OrderOutput from(final Order order) {
        List<OrderItemOutput> items = order.getItems().stream().map(OrderItemOutput::from).toList();
        return new OrderOutput(order.getId().getValue(),
                order.getTicket(), items, order.getObservation(), order.getStatus(), order.getCreatedAt());
    }
}
