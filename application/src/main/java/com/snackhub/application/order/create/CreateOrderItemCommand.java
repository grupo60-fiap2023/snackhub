package com.snackhub.application.order.create;

public record CreateOrderItemCommand(Long productId, Integer quantity) {

    public static CreateOrderItemCommand with(
            final Long productId,
            final Integer quantity
    ) {
        return new CreateOrderItemCommand(productId, quantity);
    }
}
