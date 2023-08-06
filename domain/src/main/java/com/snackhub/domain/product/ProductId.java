package com.snackhub.domain.product;



import com.snackhub.domain.Identifier;
import com.snackhub.domain.utils.IdUtils;

import java.util.Objects;

public class ProductId extends Identifier {

    private final String value;

    private ProductId(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static ProductId from(final String id) {
        return new ProductId(id);
    }

    public static ProductId unique() {
        return ProductId.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return value;
    }
}
