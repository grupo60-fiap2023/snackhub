package com.snackhub.domain.order;



import com.snackhub.domain.Entity;
import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.utils.InstantUtils;
import com.snackhub.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.List;

public class Order extends Entity<OrderId> {

    private final List<OrderItem> items;
    private final Customer customer;
    private final String observation;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private final Instant createdAt;

    private Order(final OrderId orderId,
                  final List<OrderItem> items,
                  final Customer customer,
                  final String observation,
                  final OrderStatus status,
                  final PaymentStatus paymentStatus,
                  final Instant creationDate) {
        super(orderId);
        this.items = items;
        this.customer = customer;
        this.observation = observation;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.createdAt = creationDate;
    }

    public static Order newOrder(final List<OrderItem> items, final Customer customer, final String observation, final PaymentStatus paymentStatus) {
        final var now = InstantUtils.now();
        return new Order(null, items, customer, observation, OrderStatus.RECEIVED, paymentStatus, now);
    }

    public static Order with(final OrderId orderId,
                             final List<OrderItem> items,
                             final Customer customer,
                             final String observation,
                             final OrderStatus status,
                             final PaymentStatus paymentStatus,
                             final Instant createdAt) {
        return new Order(orderId, items, customer, observation, status, paymentStatus, createdAt);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getObservation() {
        return observation;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void changeStatus(OrderStatus newStatus){

        this.status = newStatus;
    }
}
