package com.snackhub.application.order.retrieve;

import com.snackhub.application.UseCase;
import com.snackhub.application.order.OrderOutput;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.exceptions.ErrorName;
import com.snackhub.domain.order.*;
import com.snackhub.domain.validation.Error;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FindPaymentStatusByOrderIdUseCase extends UseCase<Long, PaymentStatus> {

    private final OrderGateway orderGateway;

    public FindPaymentStatusByOrderIdUseCase(final OrderGateway orderGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
    }

    @Override
    public PaymentStatus execute(Long orderId) {
        Optional<Order> optionalOrder = this.orderGateway.findOrderById(OrderId.from(orderId));
        if(optionalOrder.isEmpty()){
            throw DomainException.with(new Error(ErrorName.ORDER_NOT_EXIST));
        }

        return optionalOrder.get().getPaymentStatus();
    }
}
