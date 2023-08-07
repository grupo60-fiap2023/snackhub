package com.snackhub.infrastructure.order.persistence;

import com.snackhub.domain.order.Order;
import com.snackhub.domain.order.OrderId;
import com.snackhub.domain.order.OrderStatus;
import com.snackhub.domain.order.PaymentStatus;
import com.snackhub.infrastructure.customer.persistence.CustomerJpaEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "orderJpaEntity", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> items;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJpaEntity customerJpaEntity;

    @Column(name = "observation")
    private String observation;

    @Column(name = "status", nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @Column(name = "payment_status", nullable = false)
    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus paymentStatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    public OrderJpaEntity() {
    }

    public OrderJpaEntity(Long id,
                          List<OrderItemJpaEntity> items,
                          CustomerJpaEntity customerJpaEntity,
                          String observation,
                          OrderStatus orderStatus,
                          PaymentStatus paymentStatus,
                          Instant createdAt) {
        this.id = id;
        this.items = items;
        this.customerJpaEntity = customerJpaEntity;
        this.observation = observation;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    public OrderJpaEntity(List<OrderItemJpaEntity> items,
                          CustomerJpaEntity customerJpaEntity,
                          String observation,
                          OrderStatus orderStatus,
                          PaymentStatus paymentStatus,
                          Instant createdAt) {
        this.items = items;
        this.customerJpaEntity = customerJpaEntity;
        this.observation = observation;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    public static OrderJpaEntity create(final Order order) {
        CustomerJpaEntity customerJpa = CustomerJpaEntity.from(order.getCustomer());

        OrderJpaEntity orderJpaEntity = new OrderJpaEntity(
                new ArrayList<>(),
                customerJpa,
                order.getObservation(),
                order.getStatus(),
                order.getPaymentStatus(),
                order.getCreatedAt());

        order.getItems().stream().map(orderItem -> OrderItemJpaEntity.create(orderJpaEntity, orderItem)).forEach(orderJpaEntity.getItems()::add);

        return orderJpaEntity;
    }

    public static OrderJpaEntity from(final Order order) {
        CustomerJpaEntity customerJpa = CustomerJpaEntity.from(order.getCustomer());

        OrderJpaEntity orderJpaEntity = new OrderJpaEntity(
                order.getId().getValue(),
                new ArrayList<>(),
                customerJpa,
                order.getObservation(),
                order.getStatus(),
                order.getPaymentStatus(),
                order.getCreatedAt());

        order.getItems().stream().map(orderItem -> OrderItemJpaEntity.from(orderJpaEntity, orderItem)).forEach(orderJpaEntity.getItems()::add);

        return orderJpaEntity;
    }

    public static OrderJpaEntity from(final OrderStatus status) {
        return new OrderJpaEntity(
                null,
                null,
                null,
                status,
                null,
                null);
    }

    public Order toAggregate() {
        var orderItems = getItems().stream().map(OrderItemJpaEntity::toAggregate).toList();
        return Order.with(
                OrderId.from(getId()),
                orderItems,
                getCustomerJpaEntity().toAggregate(),
                getObservation(),
                getOrderStatus(),
                getPaymentStatus(),
                getCreatedAt());
    }

    public Long getId() {
        return id;
    }

    public List<OrderItemJpaEntity> getItems() {
        return items;
    }

    public CustomerJpaEntity getCustomerJpaEntity() {
        return customerJpaEntity;
    }

    public String getObservation() {
        return observation;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
