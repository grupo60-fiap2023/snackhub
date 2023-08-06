package com.snackhub.application.product.create;

import com.snackhub.domain.product.Category;

import java.math.BigDecimal;

public record CreateProductCommand(String name,
                                   BigDecimal price,
                                   String description,
                                   Category category) {

    public static CreateProductCommand with(final String name,
                                            final BigDecimal price,
                                            final String description,
                                            final Category category) {
        return new CreateProductCommand(name, price, description, category);
    }
}
