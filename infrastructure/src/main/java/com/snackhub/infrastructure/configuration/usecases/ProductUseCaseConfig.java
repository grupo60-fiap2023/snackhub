package com.snackhub.infrastructure.configuration.usecases;

import com.snackhub.application.product.create.CreateProductUseCase;
import com.snackhub.application.product.delete.DeleteProductUseCase;
import com.snackhub.application.product.retrieve.FindAllProductsUseCase;
import com.snackhub.application.product.retrieve.FindProductsByCategoryUseCase;
import com.snackhub.application.product.update.UpdateProductUseCase;
import com.snackhub.domain.product.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCaseConfig {

    private final ProductGateway productGateway;

    public ProductUseCaseConfig(final ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Bean
    public CreateProductUseCase createProduct() {
        return new CreateProductUseCase(productGateway);
    }

    @Bean
    public DeleteProductUseCase deleteProduct() {
        return new DeleteProductUseCase(productGateway);
    }

    @Bean
    public UpdateProductUseCase updateProduct() {
        return new UpdateProductUseCase(productGateway);
    }

    @Bean
    public FindProductsByCategoryUseCase findProductsByCategory() {
        return new FindProductsByCategoryUseCase(productGateway);
    }

    @Bean
    public FindAllProductsUseCase findAllProducts() {
        return new FindAllProductsUseCase(productGateway);
    }
}
