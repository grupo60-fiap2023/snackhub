package com.snackhub.application.product.create;

import com.snackhub.application.UseCase;
import com.snackhub.application.product.ProductOutput;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.product.Product;
import com.snackhub.domain.product.ProductGateway;
import com.snackhub.domain.validation.Notification;

import java.util.Objects;

public class CreateProductUseCase extends UseCase<CreateProductCommand, ProductOutput> {

    private final ProductGateway productGateway;

    public CreateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public ProductOutput execute(CreateProductCommand command) {
        Product newProduct = Product.newProduct(command.name(), command.price(), command.description(), command.category());
        final var notification = Notification.create();
        newProduct.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return ProductOutput.from(this.productGateway.save(newProduct));
    }
}

