package com.snackhub.infrastructure.order.persistence;


import com.snackhub.domain.order.OrderItem;
import com.snackhub.domain.order.OrderItemId;
import com.snackhub.domain.product.Product;
import com.snackhub.domain.product.ProductId;
import com.snackhub.infrastructure.product.persistence.ProductJpaEntity;

import javax.persistence.*;

@Entity(name = "OrderItem")
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderJpaEntity orderJpaEntity;

    public OrderItemJpaEntity() {
    }

    public OrderItemJpaEntity(Long id, ProductJpaEntity product, Integer quantity, OrderJpaEntity orderJpaEntity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.orderJpaEntity = orderJpaEntity;
    }
    public OrderItemJpaEntity(ProductJpaEntity product, Integer quantity, OrderJpaEntity orderJpaEntity) {
        this.product = product;
        this.quantity = quantity;
        this.orderJpaEntity = orderJpaEntity;
    }

    public OrderItemJpaEntity(ProductJpaEntity product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItemJpaEntity create(final OrderJpaEntity orderJpaEntity, final OrderItem orderItem) {
        var productJpaEntity = ProductJpaEntity.from(orderItem.getProduct());
        return new OrderItemJpaEntity(
                productJpaEntity,
                orderItem.getQuantity(),
                orderJpaEntity);

    }
    public static OrderItemJpaEntity from(final OrderJpaEntity orderJpaEntity, final OrderItem orderItem) {
        var productJpaEntity = ProductJpaEntity.from(orderItem.getProduct());
        return new OrderItemJpaEntity(
                orderItem.getId().getValue(),
                productJpaEntity,
                orderItem.getQuantity(),
                orderJpaEntity);
    }

    public OrderItem toAggregate() {
        var product = Product.with(ProductId.from(getProduct().getId()), getProduct().getName(), getProduct().getPrice(), getProduct().getDescription(), getProduct().getCategory());
        return OrderItem.with(OrderItemId.from(getId()), product, getQuantity());
    }

    public Long getId() {
        return id;
    }

    public ProductJpaEntity getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderJpaEntity getOrderJpaEntity() {
        return orderJpaEntity;
    }
}
