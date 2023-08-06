package com.snackhub.infrastructure.order.persistence;

import com.snackhub.domain.order.OrderStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getName();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String value) {
        return Stream.of(OrderStatus.values()).filter(
                orderStatus -> orderStatus.getName().equalsIgnoreCase(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
