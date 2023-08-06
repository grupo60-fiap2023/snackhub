package com.snackhub.application.order.update;

import com.snackhub.application.UseCase;
import com.snackhub.application.order.OrderOutput;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.exceptions.ErrorName;
import com.snackhub.domain.order.Order;
import com.snackhub.domain.order.OrderGateway;
import com.snackhub.domain.order.OrderId;
import com.snackhub.domain.validation.Error;

import java.util.Objects;
import java.util.Optional;

public class UpdateOrderStatusUseCase extends UseCase<UpdateOrderStatusCommand, OrderOutput> {

    private final OrderGateway orderGateway;

    public UpdateOrderStatusUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public OrderOutput execute(UpdateOrderStatusCommand command) {
        Optional<Order> optionalOrder = this.orderGateway.findOrderById(OrderId.from(command.orderId()));
        if(optionalOrder.isEmpty()){
            throw DomainException.with(new Error(ErrorName.ORDER_NOT_EXIST));
        }

        Order order = optionalOrder.get();
        order.changeStatus(command.status());
        return OrderOutput.from(this.orderGateway.update(order));
    }
}
