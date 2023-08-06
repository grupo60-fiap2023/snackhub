package com.snackhub.application.product.update;

import com.snackhub.application.UseCase;
import com.snackhub.application.product.ProductOutput;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.product.Product;
import com.snackhub.domain.product.ProductGateway;
import com.snackhub.domain.product.ProductId;
import com.snackhub.domain.validation.Notification;

import java.util.Objects;

public class UpdateProductUseCase extends UseCase<UpdateProductCommand, ProductOutput> {

    private final ProductGateway productGateway;

    public UpdateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public ProductOutput execute(UpdateProductCommand command) {
        ProductId productId = ProductId.from(command.id());
        Product product = Product.with(productId, command.name(), command.price(), command.description(), command.category());
        final var notification = Notification.create();
        product.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        return ProductOutput.from(this.productGateway.update(product));
    }
}
