package com.snackhub.domain.order;


import com.snackhub.domain.Identifier;
import com.snackhub.domain.utils.IdUtils;

public class OrderId extends Identifier {

    private final String value;

    public OrderId(String value) {
        this.value = value;
    }

    public static OrderId from(final String id) {
        return new OrderId(id);
    }

    public static OrderId unique() {
        return OrderId.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }
}
