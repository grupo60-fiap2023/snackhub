package com.snackhub.infrastructure.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItemResponse(@JsonProperty("id") Long id,
                                @JsonProperty("productId") Long productId,
                                @JsonProperty("productName") String productName,
                                @JsonProperty("quantity") Integer quantity){
}
