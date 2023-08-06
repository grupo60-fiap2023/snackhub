package com.snackhub.application.product.retrieve;

import com.snackhub.application.UseCase;
import com.snackhub.application.product.ProductOutput;
import com.snackhub.domain.product.Category;
import com.snackhub.domain.product.ProductGateway;

import java.util.List;
import java.util.Objects;

public class FindProductsByCategoryUseCase extends UseCase<Category, List<ProductOutput>> {

    private final ProductGateway productGateway;

    public FindProductsByCategoryUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public List<ProductOutput> execute(Category category) {
        return this.productGateway.findProductsByCategory(category).stream().map(ProductOutput::from).toList();
    }
}
