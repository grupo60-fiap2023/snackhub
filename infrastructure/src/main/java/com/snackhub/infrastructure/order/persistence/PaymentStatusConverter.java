package com.snackhub.infrastructure.order.persistence;

import com.snackhub.domain.order.PaymentStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PaymentStatus paymentStatus) {
        return paymentStatus.getId();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Integer id) {
        return Stream.of(PaymentStatus.values()).filter(
                        paymentStatus -> paymentStatus.getId().equals(id))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
