package com.snackhub.domain.order;


import com.snackhub.domain.Identifier;
import com.snackhub.domain.utils.IdUtils;

public class OrderItemId extends Identifier {

    private final Long value;

    public OrderItemId(Long value) {
        this.value = value;
    }

    public static OrderItemId from(final Long id) {
        return new OrderItemId(id);
    }

    @Override
    public Long getValue() {
        return value;
    }
}
