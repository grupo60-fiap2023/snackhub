package com.snackhub.application.order.retrieve;

import com.snackhub.application.NullaryUseCase;
import com.snackhub.application.order.OrderOutput;
import com.snackhub.domain.order.OrderGateway;
import com.snackhub.domain.order.OrderStatus;

import java.util.*;

public class FindAllOrdersUseCase extends NullaryUseCase<List<OrderOutput>> {

    private final OrderGateway orderGateway;

    public FindAllOrdersUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public List<OrderOutput> execute() {
        Set<OrderStatus> statusList = new HashSet<>(Arrays.asList(OrderStatus.READY, OrderStatus.IN_PREPARATION, OrderStatus.RECEIVED));
        return this.orderGateway.findAllOrdersByStatus(statusList).stream().map(OrderOutput::from).toList();
    }
}
