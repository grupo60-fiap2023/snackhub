package com.snackhub.application.order.retrieve;

import com.snackhub.application.NullaryUseCase;
import com.snackhub.application.order.OrderOutput;
import com.snackhub.domain.order.OrderGateway;

import java.util.List;
import java.util.Objects;

public class FindAllOrdersUseCase extends NullaryUseCase<List<OrderOutput>> {

    private final OrderGateway orderGateway;

    public FindAllOrdersUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public List<OrderOutput> execute() {
        return this.orderGateway.findAllOrders().stream().map(OrderOutput::from).toList();
    }
}
