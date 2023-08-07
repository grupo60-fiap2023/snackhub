package com.snackhub.application.order;

import com.snackhub.domain.order.Order;
import com.snackhub.domain.order.OrderStatus;
import com.snackhub.domain.order.PaymentStatus;

import java.time.Instant;
import java.util.List;

public record OrderOutput(Long id,
                          List<OrderItemOutput> items,
                          String observation,
                          OrderStatus status,
                          PaymentStatus paymentStatus,
                          Instant creationDate) {

    public static OrderOutput from(final Order order) {
        List<OrderItemOutput> items = order.getItems().stream().map(OrderItemOutput::from).toList();
        return new OrderOutput(order.getId().getValue(), items, order.getObservation(), order.getStatus(), order.getPaymentStatus(), order.getCreatedAt());
    }
}
