package com.snackhub.domain.order;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {

    Order save(Order order);

    List<Order> findAllOrders();

    List<Order> findOrdersByStatus(OrderStatus status);

    Order update(Order order);

    Optional<Order> findOrderById(OrderId orderId);
}
