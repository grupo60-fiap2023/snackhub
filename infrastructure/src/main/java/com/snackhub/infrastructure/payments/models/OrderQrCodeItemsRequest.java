package com.snackhub.infrastructure.payments.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record OrderQrCodeItemsRequest(

        @JsonProperty("id") @Schema(description = "id do produto", example = "1")
        String id,
        @JsonProperty("sku_number") @Schema(description = "referencia ao produto", example = "produto id order 1")
        String skuNumber,

        @JsonProperty("category") @Schema(description = "category", example = "outros")
        String category,

        @JsonProperty("title") @NotNull @Schema(description = "title", example = "Product order 1 create QRCODE - test MP")
        String title,

        @JsonProperty("description") @NotNull @Schema(description = "description", example = "Product order create QRCODE - test MP")
        String description,

        @JsonProperty("unit_price") @NotNull @Schema(description = "unitPrice", example = "35.99")
        Double unitPrice,

        @JsonProperty("quantity") @NotNull @Schema(description = "quantity", example = "1")
        Integer quantity,

        @JsonProperty("unit_measure") @NotNull @Schema(description = "unitMeasure", example = "unit")
        String unitMeasure,

        @JsonProperty("total_amount") @NotNull @Schema(description = "totalAmount", example = "35.99")
        Double totalAmount
) {
}
