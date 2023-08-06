package com.snackhub.application.product.update;

import com.snackhub.domain.product.Category;

import java.math.BigDecimal;

public record UpdateProductCommand (String id,
                                   String name,
                                   BigDecimal price,
                                   String description,
                                   Category category) {

    public static UpdateProductCommand with(final String id,
                                            final String name,
                                            final BigDecimal price,
                                            final String description,
                                            final Category category) {
        return new UpdateProductCommand(id, name, price, description, category);
    }
}