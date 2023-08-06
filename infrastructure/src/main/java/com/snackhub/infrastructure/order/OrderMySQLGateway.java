package com.snackhub.infrastructure.order;

import com.snackhub.domain.order.Order;
import com.snackhub.domain.order.OrderGateway;
import com.snackhub.domain.order.OrderId;
import com.snackhub.domain.order.OrderStatus;
import com.snackhub.infrastructure.order.persistence.OrderJpaEntity;
import com.snackhub.infrastructure.order.persistence.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class OrderMySQLGateway implements OrderGateway {

    private final OrderRepository repository;

    public OrderMySQLGateway(final OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        return this.repository.save(OrderJpaEntity.from(order)).toAggregate();
    }

    @Override
    @Transactional
    public List<Order> findAllOrders() {
        return this.repository.findAll().stream().map(OrderJpaEntity::toAggregate).toList();
    }

    @Override
    @Transactional
    public List<Order> findOrdersByStatus(OrderStatus status) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.from(status);
        Example<OrderJpaEntity> example = Example.of(orderJpaEntity);
        return this.repository.findAll(example).stream().map(OrderJpaEntity::toAggregate).toList();
    }

    @Override
    public Order update(Order order) {
        return this.repository.save(OrderJpaEntity.from(order)).toAggregate();
    }

    @Override
    @Transactional
    public Optional<Order> findOrderById(OrderId orderId) {
        return this.repository.findById(orderId.getValue()).map(OrderJpaEntity::toAggregate);
    }
}
