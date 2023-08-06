package com.snackhub.application.product.retrieve;

import com.snackhub.application.NullaryUseCase;
import com.snackhub.application.product.ProductOutput;
import com.snackhub.domain.product.ProductGateway;

import java.util.List;
import java.util.Objects;

public class FindAllProductsUseCase extends NullaryUseCase<List<ProductOutput>> {

    private final ProductGateway productGateway;

    public FindAllProductsUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public List<ProductOutput> execute() {
        return this.productGateway.findAllProducts().stream().map(ProductOutput::from).toList();
    }
}
