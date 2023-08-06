package com.snackhub.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product save(Product product);

    void deleteById(ProductId productId);

    List<Product> findAllProducts();

    Optional<Product> findProductById(ProductId productId);

    List<Product> findProductsByCategory(Category category);

    Product update(Product product);
}
