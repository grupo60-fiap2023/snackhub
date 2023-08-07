package com.snackhub.application.order;

import com.snackhub.domain.order.OrderItem;
import com.snackhub.domain.product.Product;

public record OrderItemOutput(Long id,
                              Long productId,
                              String productName,
                              Integer quantity) {

    public static OrderItemOutput from(final OrderItem orderItem) {
        Product product = orderItem.getProduct();
        return new OrderItemOutput(orderItem.getId().getValue(),
                product.getId().getValue(), product.getName(), orderItem.getQuantity());
    }
}
