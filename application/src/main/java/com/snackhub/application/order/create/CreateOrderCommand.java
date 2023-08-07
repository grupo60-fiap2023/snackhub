package com.snackhub.application.order.create;

import java.util.List;

public record CreateOrderCommand(List<CreateOrderItemCommand> items,
                                 Long customerId,
                                 String observation) {

    public static CreateOrderCommand with(
            final List<CreateOrderItemCommand> items,
            final Long customerId,
            final String observation
    ) {
        return new CreateOrderCommand(items, customerId, observation);
    }
}
