package com.snackhub.application.order.update;

import com.snackhub.domain.order.OrderStatus;

public record UpdateOrderStatusCommand(String orderId,
                                       OrderStatus status) {

    public static UpdateOrderStatusCommand with(
            final String orderId,
            final OrderStatus status
    ) {
        return new UpdateOrderStatusCommand(orderId, status);
    }
}
