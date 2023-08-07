package com.snackhub.infrastructure.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderResponse (
        @JsonProperty("id") Long id,
        @JsonProperty("items") List<OrderItemResponse> items,
        @JsonProperty("observation") String observation,
        @JsonProperty("status") String status,
        @JsonProperty("paymentStatus") String paymentStatus
) {
}
