package com.snackhub.domain.order;


import com.snackhub.domain.Entity;
import com.snackhub.domain.product.Product;
import com.snackhub.domain.validation.ValidationHandler;

public class OrderItem  extends Entity<OrderItemId> {

    private final Product product;
    private final Integer quantity;

    private OrderItem(final OrderItemId itemId, final Product product, final Integer quantity) {
        super(itemId);
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItem newOrderItem(final Product product, final Integer quantity) {
        return new OrderItem(null, product, quantity);
    }

    public static OrderItem with(final OrderItemId orderItemId, final Product product, final Integer quantity) {
        return new OrderItem(orderItemId, product, quantity);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderItemValidator(this, handler).validate();
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }


}
