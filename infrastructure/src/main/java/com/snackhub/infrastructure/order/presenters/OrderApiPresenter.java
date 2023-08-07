package com.snackhub.infrastructure.order.presenters;

import com.snackhub.application.order.OrderOutput;
import com.snackhub.infrastructure.order.models.OrderResponse;

public interface OrderApiPresenter {
    static OrderResponse present(OrderOutput order) {
        var items = order.items().stream().map(OrderItemApiPresenter::present).toList();
        return new OrderResponse(order.id(), items, order.observation(), order.status().getName(), order.paymentStatus().getName());
    }
}
