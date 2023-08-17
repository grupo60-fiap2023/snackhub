package com.snackhub.domain.customer;

import com.snackhub.domain.Identifier;

import java.util.Objects;

public class CustomerId extends Identifier {

    private final Long value;

    private CustomerId(Long value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CustomerId from(final Long id) {
        return new CustomerId(id);
    }

    @Override
    public Long getValue() {
        return value;
    }
}