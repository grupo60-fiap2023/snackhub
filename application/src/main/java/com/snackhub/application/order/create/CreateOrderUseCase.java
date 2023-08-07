package com.snackhub.application.order.create;

import com.snackhub.application.UseCase;
import com.snackhub.application.order.OrderOutput;
import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.customer.CustomerGateway;
import com.snackhub.domain.customer.CustomerId;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.exceptions.ErrorName;
import com.snackhub.domain.order.Order;
import com.snackhub.domain.order.OrderGateway;
import com.snackhub.domain.order.OrderItem;
import com.snackhub.domain.order.PaymentStatus;
import com.snackhub.domain.product.ProductGateway;
import com.snackhub.domain.product.ProductId;
import com.snackhub.domain.validation.Error;
import com.snackhub.domain.validation.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CreateOrderUseCase extends UseCase<CreateOrderCommand, OrderOutput> {

    private final OrderGateway orderGateway;

    private final CustomerGateway customerGateway;

    private final ProductGateway productGateway;

    public CreateOrderUseCase(final OrderGateway orderGateway,
                              final CustomerGateway customerGateway,
                              final ProductGateway productGateway) {
        this.orderGateway = Objects.requireNonNull(orderGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public OrderOutput execute(CreateOrderCommand command) {
        Optional<Customer> customer = getCustomer(command);
        Order order = Order.newOrder(getOrderItemsValid(command), customer.get(), command.observation(), PaymentStatus.APPROVED);

        final var notification = Notification.create();
        order.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return OrderOutput.from(this.orderGateway.save(order));
    }

    private Optional<Customer> getCustomer(CreateOrderCommand command) {
        final var customerId = command.customerId();
        var customer = this.customerGateway.findCustomerById(CustomerId.from(customerId));
        if(customer.isEmpty()){
            throw DomainException.with(new Error(ErrorName.CUSTOMER_NOT_EXIST));
        }
        return customer;
    }

    private List<OrderItem> getOrderItemsValid(CreateOrderCommand command) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<CreateOrderItemCommand> itemsCommand = command.items();
        for (CreateOrderItemCommand itemCommand : itemsCommand) {
            var product = this.productGateway.findProductById(ProductId.from(itemCommand.productId()));
            if(product.isEmpty()){
                throw DomainException.with(new Error(ErrorName.PRODUCT_NOT_EXIST));
            }

            orderItems.add(OrderItem.newOrderItem(product.get(), itemCommand.quantity()));
        }

        return orderItems;
    }
}
