package com.snackhub.infrastructure.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record OrderItemRequest(
        @JsonProperty("productId") @NotNull @Schema(description = "identificador do produto", example = "3437524e-1607-11ee-be56-0242ac120002") Long productId,
        @JsonProperty("quantity") @NotNull @Schema(description = "Quantidade do item(ns)", example = "2") Integer quantity
) {
}
