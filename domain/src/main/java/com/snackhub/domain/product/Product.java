package com.snackhub.domain.product;



import com.snackhub.domain.Entity;
import com.snackhub.domain.validation.ValidationHandler;

import java.math.BigDecimal;

public class Product extends Entity<ProductId> {

    private final String name;
    private final BigDecimal price;
    private final String description;
    private final Category category;

    private Product(final ProductId id, final String name, final BigDecimal price, final String description, final Category category) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public static Product newProduct(final String name, final BigDecimal price, final String description, final Category category) {
        return new Product(null, name, price, description, category);
    }

    public static Product with(final ProductId productId, final String name,  final BigDecimal price, final String description, final Category category) {
        return new Product(productId, name, price, description, category);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
