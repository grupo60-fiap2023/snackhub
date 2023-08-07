package com.snackhub.domain.order;


import com.snackhub.domain.Identifier;
import com.snackhub.domain.utils.IdUtils;

public class OrderId extends Identifier {

    private final Long value;

    public OrderId(Long value) {
        this.value = value;
    }

    public static OrderId from(final Long id) {
        return new OrderId(id);
    }

    @Override
    public Long getValue() {
        return value;
    }
}
