package com.snackhub.infrastructure.product;

import com.snackhub.domain.product.Category;
import com.snackhub.domain.product.Product;
import com.snackhub.domain.product.ProductGateway;
import com.snackhub.domain.product.ProductId;
import com.snackhub.infrastructure.product.persistence.ProductJpaEntity;
import com.snackhub.infrastructure.product.persistence.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductMySQLGateway implements ProductGateway {

    private final ProductRepository repository;

    public ProductMySQLGateway(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return this.repository.save(ProductJpaEntity.create(product)).toAggregate();
    }

    @Override
    public void deleteById(ProductId productId) {
        if (productId != null && this.repository.existsById(productId.getValue())) {
            this.repository.deleteById(productId.getValue());
        }
    }

    @Override
    public List<Product> findAllProducts() {
        return this.repository.findAll().stream().map(ProductJpaEntity::toAggregate).toList();
    }

    @Override
    public Optional<Product> findProductById(ProductId productId) {
        return this.repository.findById(productId.getValue()).map(ProductJpaEntity::toAggregate);
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        ProductJpaEntity productJpaEntity = ProductJpaEntity.from(category);
        Example<ProductJpaEntity> example = Example.of(productJpaEntity);
        return this.repository.findAll(example).stream().map(ProductJpaEntity::toAggregate).toList();
    }

    @Override
    public Product update(Product product) {
        return this.repository.save(ProductJpaEntity.from(product)).toAggregate();
    }
}
