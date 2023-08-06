package com.snackhub.application.product;

import com.snackhub.domain.product.Category;
import com.snackhub.domain.product.Product;

import java.math.BigDecimal;

public record ProductOutput(String id,
                            String name,
                            BigDecimal price,
                            String description,
                            Category category) {

    public static ProductOutput from(final Product product) {
        return new ProductOutput(
                product.getId().getValue(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory());
    }
}
