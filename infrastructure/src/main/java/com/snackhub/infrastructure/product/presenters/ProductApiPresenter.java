package com.snackhub.infrastructure.product.presenters;

import com.snackhub.application.product.ProductOutput;
import com.snackhub.infrastructure.product.models.ProductResponse;

public interface ProductApiPresenter {

    static ProductResponse present(final ProductOutput output) {
        return new ProductResponse(output.id(),
                output.name(), output.price(), output.description(), output.category());
    }
}
