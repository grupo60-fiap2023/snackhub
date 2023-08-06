package com.snackhub.application.order.create;

public record CreateOrderItemCommand(String productId, Integer quantity) {

    public static CreateOrderItemCommand with(
            final String productId,
            final Integer quantity
    ) {
        return new CreateOrderItemCommand(productId, quantity);
    }
}
