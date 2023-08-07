package com.snackhub.application.product.delete;


import com.snackhub.application.UnitUseCase;
import com.snackhub.domain.product.ProductGateway;
import com.snackhub.domain.product.ProductId;

import java.util.Objects;

public class DeleteProductUseCase extends UnitUseCase<Long> {

    private final ProductGateway productGateway;

    public DeleteProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public void execute(Long productId) {
        this.productGateway.deleteById(ProductId.from(productId));
    }
}
