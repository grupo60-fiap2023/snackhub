package com.snackhub.infrastructure.payments.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateOrderQrCodeRequest(
        @JsonProperty("external_reference") @Schema(description = "Id da Ordem", example = "1")
        String externalReference,
        @JsonProperty("title") @Schema(description = "Titulo da Ordem", example = "Product order 1 create QRCODE - test MP")
        String title,
        @JsonProperty("notification_url") @Schema(description = "URL para notificação com os dados do merchant_order", example = "https://webhook.site/2e9b0370-b95e-467b-8fdc-a42bd6a087e2")
        String notificationUrl,
        @JsonProperty("total_amount") @NotNull @Schema(description = "Valor total da Ordem", example = "35.99")
        Double totalAmount,
        @JsonProperty("items") @NotNull @Schema(description = "Lista com os items da Ordem")
        List<OrderQrCodeItemsRequest> items,
        @JsonProperty("cash_out") @NotNull @Schema(description = "Valor troco")
        OrderQrCodeCashOutRequest cashOut,
        @JsonProperty("description") @NotNull @Schema(description = "Descrição da Ordem", example = "Product order create QRCODE - test MP")
        String description
) {
}
