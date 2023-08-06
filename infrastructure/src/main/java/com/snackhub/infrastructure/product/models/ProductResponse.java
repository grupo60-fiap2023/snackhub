package com.snackhub.infrastructure.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackhub.domain.product.Category;

import java.math.BigDecimal;

public record ProductResponse(@JsonProperty("id") String id,
                              @JsonProperty("name") String name,
                              @JsonProperty("price") BigDecimal price,
                              @JsonProperty("description") String description,
                              @JsonProperty Category category) {
}
