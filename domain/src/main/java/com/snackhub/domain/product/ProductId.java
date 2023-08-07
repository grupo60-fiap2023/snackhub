package com.snackhub.domain.product;



import com.snackhub.domain.Identifier;
import com.snackhub.domain.utils.IdUtils;

import java.util.Objects;

public class ProductId extends Identifier {

    private final Long value;

    private ProductId(Long value) {
        this.value = Objects.requireNonNull(value);
    }

    public static ProductId from(final Long id) {
        return new ProductId(id);
    }

    @Override
    public Long getValue() {
        return value;
    }
}
